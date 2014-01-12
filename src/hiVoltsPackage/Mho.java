package hiVoltsPackage;

import java.awt.*;

public class Mho extends GamePiece
{

	public Mho()
	{
		pType = PieceType.Mho;
		setType();
	}
	
	public Mho(Point p, int size)
	{
		pType = PieceType.Mho;
		setType();
		
		cellSize = size;
		diameter = cellSize;
		radius = diameter / 2;
		
		xGrid = (int) p.getX();
		yGrid = (int) p.getY();
		
		xCoord = xGrid * cellSize;
		yCoord = yGrid * cellSize;
		
	}
}
