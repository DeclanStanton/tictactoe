import java.awt.*;
import javax.swing.*;
import java.awt.event.*;	
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class TicTacToe extends JFrame
implements MouseListener, KeyListener
{
	final static int MAX_WIDTH = 1000;
	final static int MAX_HEIGHT = 800;

	final static int SQUARE_HEIGHT = 70;
	final static int SQUARE_WIDTH = 70;

	final static int startX = 200;
	final static int startY = 200;

	final static int O = 1;
	final static int X = 2;
	final static int EMPTY = 3;
	static int turn = O;

	final static int PLAYING = 0;
	final static int OVER = 1;
	final static int OVERDRAW = 2;
	static int gameMode;

	Board board = new Board(startX, startY, SQUARE_HEIGHT, SQUARE_WIDTH);

	public static void main(String[] args)
	{		
		// Create the window.
		TicTacToe mb = new TicTacToe();	
		mb.addMouseListener(mb);
		mb.addKeyListener(mb);	
	}

	public TicTacToe()
	{
		setSize(MAX_WIDTH, MAX_HEIGHT);
		setVisible(true); 
		repaint();
	}

	
	public void keyPressed(KeyEvent e)
	{
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_SPACE)
		{
			board.restart();
			turn = O;
			gameMode = PLAYING;
			repaint();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void mouseClicked(MouseEvent e)
	{
		if (gameMode == PLAYING)
		{
			//State: 1 = won, 2 = succesful, 3 = failure, 4 = draw
			int state = board.selectSquare(e.getX(), e.getY(), turn);
			repaint();
			if (state == 1)
			{
				//Game over, won
				gameMode = OVER;
				repaint();
			}
			else if (state == 4)
			{
				//Game over, draw
				gameMode = OVERDRAW;
				repaint();
			}
			else if (state == 2)
			{
				if (turn == 1)
				{
					turn = 0;
				}
				else
				{
					turn = 1;
				}
			}
		}
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
		g.setColor(Color.white);
		g.fillRect(0, 0, MAX_WIDTH, MAX_HEIGHT);
		board.display(g);
		if (gameMode == OVER)
		{
			winScreen(g);
		}
		else if(gameMode == OVERDRAW)
		{
			drawScreen(g);
		}
	}
	
	public void winScreen(Graphics g)
	{
		g.setColor(Color.green);
		Font myFont = new Font("Arial", 50, 50);
		g.setFont(myFont);
		if(turn == 1)
		{
			g.drawString("X Won!", 100, 500);
		}
		else if(turn == 0)
		{
			g.drawString("O Won!", 100, 500);
		}
	}
	
	public void drawScreen(Graphics g)
	{
		g.setColor(Color.green);
		Font myFont = new Font("Arial", 50, 50);
		g.setFont(myFont);
		g.drawString("Draw", 100, 500);
	}

}

class Board
{
	final int ROWS = 3;
	final int COLS = 3;

	private int startX;
	private int startY;

	private int height;
	private int width;

	final static int O = 1;
	final static int X = 2;
	final static int EMPTY = 3;

	private Square [][] array;

	public Board(int startXIn, int startYIn, int heightIn, int widthIn)
	{
		array = new Square[ROWS][COLS];
		startX = startXIn;
		startY = startYIn;
		height = heightIn;
		width = widthIn;
		fillArray();
	}


	private void fillArray()
	{
		int x = startX;
		int y = startY;

		for (int row = 0; row < ROWS; row++)
		{
			x = startX + row * height;
			y = startY;
			for (int col = 0; col < COLS; col++)
			{
				y = startY + col * width;
				array[row][col] = new Square(x, y);
			}
		}
		Square.setWidth(width);
		Square.setHeight(height);
	}

	public int selectSquare(int mouseX, int mouseY, int turn)
	{	
		//State: 1 = won, 2 = succesful, 3 = failure
		for (int row = 0; row < ROWS; row++)
		{
			for (int col = 0; col < COLS; col++)
			{
				int x = array[row][col].getX();
				int y = array[row][col].getY();

				if (mouseX > x && mouseX < (x + width) && mouseY > y && mouseY < (y + height))
				{
					if (array[row][col].getValue() != EMPTY)
					{
						return 3;
					}
					else
					{
						array[row][col].setValue(turn);
						if (checkWon(turn, row, col))
						{
							return 1;
						}
						else if(isFilled())
						{
							return 4;
						}
						else
						{
							return 2;
						}
					}
				}
			}
		}
		return 3;
	}

	public boolean checkWon(int turn, int currentRow, int currentCol)
	{
		return (array[currentRow][0].getValue() == turn         // 3-in-the-currentRow
				&& array[currentRow][1].getValue() == turn
				&& array[currentRow][2].getValue() == turn
				|| array[0][currentCol].getValue() == turn      // 3-in-the-column
				&& array[1][currentCol].getValue() == turn
				&& array[2][currentCol].getValue() == turn
				|| currentRow == currentCol            // 3-in-the-diagonal \
				&& array[0][0].getValue() == turn
				&& array[1][1].getValue() == turn
				&& array[2][2].getValue() == turn
				|| currentRow + currentCol == 2  // 3-in-the-opposite-diagonal /
				&& array[0][2].getValue() == turn
				&& array[1][1].getValue() == turn
				&& array[2][0].getValue() == turn);
	}

	public void restart()
	{
		for (int row = 0; row < ROWS; row++)
		{
			for (int col = 0; col < COLS; col++)
			{
				array[row][col].setValue(EMPTY);
			}
		}
	}

	public void display(Graphics g)
	{
		for (int row = 0; row < ROWS; row++)
		{
			for (int col = 0; col < COLS; col++)
			{
				array[row][col].display(g);
			}
		}
	}
	
	public boolean isFilled()
	{
		int count = 0;
		for(int row = 0; row < ROWS; row++)
		{
			for(int col = 0; col < COLS; col++)
			{
				count += array[row][col].getValue();
			}
		}
		return count == 9 * EMPTY;
	}
}

class Square
{
	//type of box + num will make type X
	//- num will make type 0
	public int value = 3;
	public final int O = 0;
	public final int X = 1;
	public final int EMPTY = 3;

	//neighboring squares
	private Square myLeft;
	private Square myRight;
	private Square myUp;
	private Square myDown;

	//size variables
	public static int myWidth;
	public static int myHeight;

	//location variables
	private int myX;
	private int myY;

	//personalization colors
	private Color boxColor = Color.black;
	private Color boxFillColor = Color.white;
	private Color valColor = Color.black;

	public Square(int xCordIn, int yCordIn)//Square leftIn, Square rightIn, Square upIn, Square downIn)
	{
		myX = xCordIn;
		myY = yCordIn;

		/*
		myLeft = leftIn;
		myRight = rightIn;
		myUp = upIn;
		myDown = downIn;
		 */
	}

	//set width and height
	public static void setWidth(int widthIn)
	{
		myWidth = widthIn;
	}
	public static void setHeight(int heightIn)
	{
		myHeight = heightIn;
	}

	//get X and Y cords
	public int getX()
	{
		return myX;
	}

	public int getY()
	{
		return myY;
	}

	//set colors of the square
	public void setBoxColor(Color colorIn)
	{
		boxColor = colorIn;
	}
	public void setBoxFill(Color colorIn)
	{
		boxFillColor = colorIn;
	}
	public void setValueFill(Color colorIn)
	{
		valColor = colorIn;
	}

	public int getValue()
	{
		return value;
	}

	public void setValue(int newValue)
	{
		value = newValue;
	}

	//square draws itself
	public void display(Graphics g)
	{
		g.setColor(boxColor);
		g.drawRect(myX, myY, myWidth, myHeight);
		if(value == O)//0
		{
			g.drawOval(myX + 3, myY + 3, myWidth - 3, myHeight - 3);
		}
		else if (value == X)
		{
			g.drawLine(myX, myY, myX + myWidth, myY + myHeight);
			g.drawLine(myX, myY + myHeight, myX + myWidth, myY);
		}
	}

	/*
	//return the neighboring squares
	public Square getLeft()
	{
		return myLeft;
	}
	public Square getRight()
	{
		return myRight;
	}
	public Square getUp()
	{
		return myUp;
	}
	public Square getDown()
	{
		return myDown;
	}
	 */

	//set the neighboring square
	//	public void setLeft(Square squareIn)
	//	{
	//		myLeft = squareIn;
	//	}
	//	public void setRight(Square squareIn)
	//	{
	//		myRight = squareIn;
	//	}
	//	public void setUp(Square squareIn)
	//	{
	//		myUp = squareIn;
	//	}
	//	public void setDown(Square squareIn)
	//	{
	//		myDown = squareIn;
	//	}

}