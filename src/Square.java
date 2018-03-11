import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Square
{
	//type of box + num will make type X
	//- num will make type 0
	public int value = 0;
	public final int O = 0;
	public final int X = 1;
	public final int EMPTY = 3;
	
	//neighboring squares
	private Square myLeft;
	private Square myRight;
	private Square myUp;
	private Square myDown;
	
	//size variables
	private static int myWidth;
	private static int myHeight;
	
	//location variables
	private int myX;
	private int myY;
	
	//personalization colors
	private Color boxColor;
	private Color boxFillColor;
	private Color valColor;
	
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
	
	//set the dimensions of the square
	public void setWidth(int widthIn)
	{
		myWidth = widthIn;
	}
	public void setHeight(int heightIn)
	{
		myHeight = heightIn;
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
	
	//set value
	
	//square draws itself
	public void drawSquare(Graphics g)
	{
		g.setColor(boxColor);
		g.drawRect(myX, myY, myWidth, myHeight);
		g.fillRect(myX, myY, myWidth, myHeight);
		if(value == O)//0
		{
			g.drawOval(myX + 3, myY + 3, myWidth - 3, myHeight - 3);
		}
		else if(value == X)
		{
			Font myFont = new Font("Arial", myHeight,myWidth);
			g.setFont(myFont);
			g.drawString("X", myX + 3, myY + 3);
		}
		else
		{
			
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
	
	/*
	//set location of the Square
	public void setX(int xPosIn)
	{
		myX = xPosIn;
	}
	public void setY(int yPosIn)
	{
		myY = yPosIn;
	}
	*/

}
