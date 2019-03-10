//
//  CustomSwipeToBottomSegue.swift
//  swap-it
//
//  Created by Declan Stanton on 3/9/19.
//  Copyright © 2019 Declan Stanton. All rights reserved.
//

import UIKit
class CustomSwipeToBottomSegue: UIStoryboardSegue {
    override func perform()
    {
        let initialView = self.source.view as UIView!
        let destinationView = self.destination.view as UIView!
        
        let screenHeight = UIScreen.main.bounds.height
        let screenWidth = UIScreen.main.bounds.width
        
        initialView?.frame = CGRect(x: 0, y: 0, width: screenWidth, height: screenHeight)
        destinationView?.frame = CGRect(x: 0, y: screenHeight, width: screenWidth, height: screenHeight)
        
        let appWindow = UIApplication.shared.keyWindow
        appWindow?.insertSubview(destinationView!, aboveSubview: initialView!)
        
        UIView.animate(withDuration: 0.4, animations:
            {
                initialView?.frame = (initialView?.frame.offsetBy(dx: 0, dy: -screenHeight))!
                destinationView?.frame = (destinationView?.frame.offsetBy(dx: 0, dy: -screenHeight))!
        }) { (Bool) in self.source.present(self.destination, animated: false, completion:nil)}
        
    }
}
