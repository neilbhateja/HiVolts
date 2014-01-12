package hiVoltsPackage;

import java.awt.Graphics2D;
import java.awt.Point;

public class GamePiece extends Face
{
	public GamePiece()
	{
	}
	
	public void setType()
	{
		if (pType == PieceType.Mho)
		{
			fType = FaceType.Sad;
		}
		
		if (pType == PieceType.Player)
		{
			fType = FaceType.Happy;
		}
		if (pType == PieceType.Fence)
		{
			fType = FaceType.Fence;
		}
	}
	
	public void setLocation(Point p)
	{
		xGrid = (int) p.getX();
		yGrid = (int) p.getY();
		
		xCoord = xGrid * cellSize;
		yCoord = yGrid * cellSize;
	}
	
	public void setLocation(int x, int y)
	{
		Point p = new Point(x, y);
		setLocation(p);
	}
	
	public Point getLocation()
	{
		Point p = new Point(xGrid, yGrid);
		return p;
	}
	
	public int getX()
	{
		return xGrid;
	}
	
	public int getY()
	{
		return yGrid;
	}
	
	public void setSize(int size)
	{
		cellSize = size;
		diameter = cellSize;
		radius = cellSize / 2;
	}
	
	public void draw(Graphics2D g2)
	{
		paint(g2);
	}
	
	public enum PieceType {Player, Mho, Fence};
	public PieceType pType;
	public int xGrid; //x coordinate of cell on grid.
	public int yGrid; // y coordinate of etc.

}
