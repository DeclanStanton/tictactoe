
public class GridBackup
{
	Square origin = new Square(null, null, null, null);

	public GridBackup()
	{
		//connects left, right, up, down boxes
		origin.setLeft(new Square(null, origin, null, null));
		origin.setRight(new Square(origin, null, null, null));
		origin.setUp(new Square(null, null, null, origin));
		origin.setDown(new Square(null, null, origin, null));
		
		//connects the diagonals
		//upper left
		origin.getLeft().setUp(new Square(null,
										origin.getUp(),
										null, 
										origin.getLeft()));
		//lower left
		origin.getLeft().setDown(new Square(null,
										origin.getDown(), 
										origin.getLeft(),
										null));
		//upper right
		origin.getRight().setUp(new Square(origin.getUp(),
											null,
											null,
											origin.getRight()));
		//lower right
		origin.getRight().setDown(new Square(origin.getDown(),
											null,
											origin.getRight(),
											null));
		
		//connect the upper and lower boxes to their adjacents
		//upper
		origin.getUp().setLeft(origin.getLeft().getUp());
		origin.getUp().setRight(origin.getLeft().getUp());
		//lower
		origin.getDown().setLeft(origin.getLeft().getDown());
		origin.getDown().setRight(origin.getRight().getDown());
	}

}
