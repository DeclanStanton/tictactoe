import java.awt.Graphics;

public class Square
{
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
	
	public Square(Square leftIn, Square rightIn, Square upIn, Square downIn)
	{
		myLeft = leftIn;
		myRight = rightIn;
		myUp = upIn;
		myDown = downIn;
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
	
	//set location of the Square
	public void setX(int xPosIn)
	{
		
	}
	public void setY(int yPosIn)
	{
		
	}
	
	//square draws itself
	public void drawSquare(Graphics g)
	{
		g.drawRect(myX, myY, myWidth, myHeight);
	}

}
