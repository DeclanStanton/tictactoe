import java.awt.*;
import javax.swing.*;
import java.awt.event.*;	
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;


public class TicTacToe extends JFrame
implements MouseListener, KeyListener
{
	final static int MAX_WIDTH = 1000;
	final static int MAX_HEIGHT = 800;

	final static int SQUARE_HEIGHT = 120;
	final static int SQUARE_WIDTH = 120;

	final static int NUM_ROWS = 3;
	final static int NUM_COLS = 3;

	final static int startX = MAX_WIDTH/2 - SQUARE_WIDTH/2 * NUM_COLS;
	final static int startY = MAX_HEIGHT/2 - SQUARE_HEIGHT/2 * NUM_ROWS;

	final static int O = 1;
	final static int X = 2;
	final static int EMPTY = 3;
	static int turn = O;

	final static int MENU = -1;
	final static int PLAYING = 0;
	final static int OVER = 1;
	final static int DRAW = 2;
	static int gameMode = MENU;
	final static int TWO_PLAYERS = 2;
	final static int ONE_PLAYER = 1;
	static int players = 1;

	Board board = new Board(startX, startY, SQUARE_HEIGHT, SQUARE_WIDTH, NUM_ROWS, NUM_COLS);

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
			if (gameMode != MENU)
			{
				board.restart();
				turn = O;
				gameMode = PLAYING;
				repaint();
			}
		}
		else if (keyCode == KeyEvent.VK_1)
		{
			if (gameMode == MENU)
			{
				players = ONE_PLAYER;
				gameMode = PLAYING;
			}
		}
		else if (keyCode == KeyEvent.VK_2)
		{
			if (gameMode == MENU)
			{
				players = TWO_PLAYERS;
				gameMode = PLAYING;
			}
		}
		repaint();
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
			//State: 1 = won, 2 = successful, 3 = failure, 4 = draw
			int state = board.selectSquare(e.getX(), e.getY(), turn);
			repaint();
			results(state); //What happens next
		} 

		if (players == 1 && gameMode == PLAYING && turn == X)
		{
			//AI
			int state = board.AISelect(turn);
			repaint();
			results(state); //What happens next
		}

	}

	public void results(int state)
	{
		if (state == 1)
		{
			//Game over, won
			gameMode = OVER;
			repaint();
		}
		else if (state == 4)
		{
			//Game over, draw
			gameMode = DRAW;
			repaint();
		}
		else if (state == 2)
		{
			//switch turn, normal selection
			if (turn == X)
			{
				turn = O;
			}
			else
			{
				turn = X;
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
		if (gameMode == MENU)
		{
			menu(g);
		}
		else
		{
			board.display(g);
			if (gameMode == OVER)
			{
				winScreen(g);
			}
			else if (gameMode == DRAW)
			{
				drawScreen(g);
			}
		}
	}

	public void winScreen(Graphics g)
	{
		g.setColor(new Color(179, 204, 230));
		g.fill3DRect(90, 450, 190, 80, true);
		g.setColor(new Color(0, 153, 51));
		Font myFont = new Font("Courier", 50, 50);
		g.setFont(myFont);

		if (turn == X)
		{
			g.drawString("X Won!", 100, 500);
		}
		else if (turn == O)
		{
			g.drawString("O Won!", 100, 500);
		}
	}

	public void drawScreen(Graphics g)
	{
		g.setColor(new Color(179, 204, 230));
		g.fill3DRect(90, 450, 190, 80, true);
		g.setColor(Color.orange);
		Font myFont = new Font("Courier", 50, 50);
		g.setFont(myFont); 

		g.drawString("Draw", 100, 500);
	}

	public void menu(Graphics g)
	{
		g.setColor(new Color(179, 204, 230));
		g.fill3DRect(275, 615, 400, 50, true);
		g.fill3DRect(275, 565, 400, 50, true);
		g.setColor(Color.black);
		Font myFont = new Font("Courier", 45, 45);
		g.setFont(myFont); 
		g.drawString("Welcome to the incredibely ", 60, 200);
		g.drawString("complex game of tic-tac-toe, ", 60, 300);
		g.drawString("can you beat the randomized AI?", 60, 400);
		myFont = new Font("Courier", 30, 30);
		g.setFont(myFont); 
		g.drawString("Press 1 for player 1", 300, 650);
		g.drawString("Press 2 for player 2", 300, 600);
	}
}

class Board
{
	private int rows;
	private int cols;

	private int startX;
	private int startY;

	private int height;
	private int width;

	final static int O = 1;
	final static int X = 2;
	final static int EMPTY = 3;

	private Square [][] array;

	public Board(int startXIn, int startYIn, int heightIn, int widthIn, int rowsIn, int colsIn)
	{
		startX = startXIn;
		startY = startYIn;
		height = heightIn;
		width = widthIn;
		rows = rowsIn;
		cols = colsIn;
		array = new Square[rows][cols];

		fillArray();
	}


	private void fillArray()
	{
		int x = startX;
		int y = startY;

		for (int row = 0; row < rows; row++)
		{
			x = startX + row * height;
			y = startY;
			for (int col = 0; col < cols; col++)
			{
				y = startY + col * width;
				array[row][col] = new Square(x, y);
			}
		}
		Square.setWidth(width);
		Square.setHeight(height);
	}

	//	public void connectArray()
	//	{
	//		for (int row = 0; row < rows - 1; row++)
	//		{
	//			//leftmost edge, cannot include in general code
	//			array[row][cols - 1].setDown(array[row - 1][cols - 1]);
	//			array[row - 1][cols - 1].setUp(array[row][cols - 1]);
	//
	//			for (int col = 0; col < cols - 1; col++)
	//			{
	//				array[row][col].setRight(array[row][col + 1]);
	//				array[row][col + 1].setLeft(array[row][col]);
	//				array[row][col].setDown(array[row - 1][col]);
	//				array[row - 1][col].setUp(array[row][col]);
	//
	//				//bottom edge, cannot include in general code
	//				array[rows - 1][col].setRight(array[rows - 1][col + 1]);
	//				array[rows - 1][col + 1].setLeft(array[rows - 1][col]);
	//			}
	//		}
	//	}

	public int selectSquare(int mouseX, int mouseY, int turn)
	{	
		//State: 1 = won, 2 = succesful, 3 = failure
		for (int row = 0; row < rows; row++)
		{
			for (int col = 0; col < cols; col++)
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
							return 1; //turn won
						}
						else if(isFilled())
						{
							return 4; //Tied
						}
						else
						{
							return 2; //success
						}
					}
				}
			}
		}
		return 3; //fail	
	}

	public int AISelect(int turn)
	{
		Random rand = new Random();
		int sqr = rand.nextInt(width * height);
		int count = 0;

		while (true)
		{
			for (int row = 0; row < rows; row++)
			{
				for (int col = 0; col < cols; col++)
				{
					if (count == sqr && array[row][col].getValue() == EMPTY)
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
					count ++;
				}
			}
			sqr = rand.nextInt(width * height);
			count = 0;
		}

	}

	//	

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
		for (int row = 0; row < rows; row++)
		{
			for (int col = 0; col < cols; col++)
			{
				array[row][col].setValue(EMPTY);
			}
		}
	}

	public void display(Graphics g)
	{
		for (int row = 0; row < rows; row++)
		{
			for (int col = 0; col < cols; col++)
			{
				array[row][col].display(g);
			}
		}
	}

	public boolean isFilled()
	{
		for(int row = 0; row < rows; row++)
		{
			for(int col = 0; col < cols; col++)
			{
				if (array[row][col].getValue() == EMPTY)
				{
					return false;
				}
			}
		}
		return true;
	}
}

class Square
{
	//type of box + num will make type X
	//- num will make type 0
	public int value = 3;
	public final int O = 1;
	public final int X = 2;
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

	public Square(int xCordIn, int yCordIn)
	{
		myX = xCordIn;
		myY = yCordIn;
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
		if (value == O)//0
		{
			g.drawOval(myX + 3, myY + 3, myWidth - 3, myHeight - 3);
		}
		else if (value == X)
		{
			g.drawLine(myX, myY, myX + myWidth, myY + myHeight);
			g.drawLine(myX, myY + myHeight, myX + myWidth, myY);
		}
	}


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


	//set the neighboring square
	public void setLeft(Square squareIn)
	{
		myLeft = squareIn;
	}
	public void setRight(Square squareIn)
	{
		myRight = squareIn;
	}
	public void setUp(Square squareIn)
	{
		myUp = squareIn;
	}
	public void setDown(Square squareIn)
	{
		myDown = squareIn;
	}
}


