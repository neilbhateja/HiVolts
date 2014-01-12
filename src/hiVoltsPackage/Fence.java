package hiVoltsPackage;
import java.awt.Point;


public class Fence extends GamePiece
{
	public Fence()
	{
		pType = PieceType.Mho;
		setType();
	}
	
	public Fence(Point p, int diam)
	{
		pType = PieceType.Fence;
		setType();
		
		cellSize = diam;
		xGrid = (int) p.getX();
		yGrid = (int) p.getY();
		xCoord = cellSize * xGrid;
		yCoord = cellSize * yGrid;
			
	}
	
}
