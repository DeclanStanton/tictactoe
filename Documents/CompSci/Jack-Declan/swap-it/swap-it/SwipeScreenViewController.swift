//
//  SwipeScreenViewController.swift
//  swap-it
//
//  Created by Declan Stanton on 3/9/19.
//  Copyright © 2019 Declan Stanton. All rights reserved.
//

import UIKit

class SwipeScreenViewController: UIViewController {
    
    var itemImages: [UIImage] = []

    override func viewDidLoad() {
        super.viewDidLoad()
        itemImages.append(UIImage(named: "rhettberg")!)
        itemImages.append(UIImage(named: "triggeredMeme.jpg")!)
        itemImages.append(UIImage(named: "allahuMeme.jpg")!)
        presentedImage.image = itemImages[0]
    }
    
    @IBOutlet weak var cardItem: UIView!
    @IBOutlet weak var presentedImage: UIImageView!
    
    
    @IBAction func swipeEngaged(_ sender: UIPanGestureRecognizer)
    {
        let cardView = sender.view!
        let translationPoint = sender.translation(in: view)
        cardView.center = CGPoint(x: view.center.x+translationPoint.x,y: view.center.y+translationPoint.y)
        
        if sender.state == UIGestureRecognizer.State.ended {
            if cardView.center.x < 20 { // Moved to left
                UIView.animate(withDuration: 0.3, animations: {
                    cardView.center = CGPoint(x: cardView.center.x-400, y: cardView.center.y)
                    cardView.transform = CGAffineTransform(rotationAngle: -0.61)
                })
                loadNextItem(cardView: cardView, panReco: sender)
                return
            }
            else if (cardView.center.x > (view.frame.size.width-20)) { // Moved to right
                UIView.animate(withDuration: 0.3, animations: {
                    cardView.center = CGPoint(x: cardView.center.x+400, y: cardView.center.y)
                    cardView.transform = CGAffineTransform(rotationAngle: 0.61)
                })
                loadNextItem(cardView: cardView, panReco: sender)
                return
            }
            
            UIView.animate(withDuration: 0.2, animations: {
                cardView.center = self.view.center
                cardView.transform = .identity
            })
        }
    }
    
    func loadNextItem(cardView: UIView, panReco: UIPanGestureRecognizer)
    {
        let translationPoint = panReco.translation(in: view)
        itemImages.removeFirst()
        presentedImage.image = itemImages[0]
        
        cardView.center = CGPoint(x: view.center.x+translationPoint.x,y: view.center.y+translationPoint.y)
    }
    
}


