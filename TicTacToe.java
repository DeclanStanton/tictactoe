
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;	
import java.util.Scanner;

public class TicTacToe extends JFrame
	implements MouseListener
{
	final static int MAX_WIDTH = 1000;
	final static int MAX_HEIGHT = 800;
	
	final static int startX = 200;
	final static int startY = 200;
	
	final static int O = 1;
	final static int X = 2;
	
	final static int turn = O;
	
	Square currentSquare;
	
	Grid grid = new Grid(startX, startY);
	
	public static void main(String[] args)
	{
		// Create the window.
		TicTacToe mb = new TicTacToe();	
		mb.addMouseListener(mb);	
	}

	public TicTacToe()
	{
		setSize(MAX_WIDTH, MAX_HEIGHT);
		setVisible(true);
	}
	

	private void mouseClicked(TicTacToe mb) 
	{
	}
	
	public void mouseClicked(MouseEvent e)
	{
		selectSquare(e.getX(), e.getY()); 
	}
	
	public void selectSquare(int x, int y)
	{
		grid.selectSquare(x, y);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void paint(Graphics g)
	{
		
	}
}

class Grid
{
	final int WIDTH = 3;
	int LENGTH = ;
	
	public Grid(int x, int y)
	{
		
	}
	
	public void selectSquare()
	{
		
	}
}
