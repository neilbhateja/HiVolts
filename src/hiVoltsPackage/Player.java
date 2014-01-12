package hiVoltsPackage;

import java.awt.*;

public class Player extends GamePiece
{

	public Player()
	{
		pType = PieceType.Player;
		setType();
		
	}
	
	public Player(Point p, int size)
	{
		pType = PieceType.Player;
		setType();
		
		cellSize = size;
		diameter = cellSize;
		radius = cellSize / 2;
		
		xGrid = (int) p.getX();
		yGrid = (int) p.getY();
		
		xCoord = xGrid * cellSize;
		yCoord = yGrid * cellSize;
		
	}
	
}
