package hiVoltsPackage;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class Face 
{
	public Face()
	{}
	
	public Face(FaceType tehType, int x, int y, int diam)
	{
		xCoord = x;
		yCoord = y;
		radius = diameter / 2;
		fType = tehType;
	}
	
	public void paint(Graphics2D g2)
	{
		if (fType == FaceType.Fence)
		{
			fenceDraw(g2);
		}
		
		else
		{
			otherDraw(g2);
		}
	}
	
	public void otherDraw(Graphics2D g2)
	{
		// Makes circle for head.
		double xCircle = xCoord + 2;
		double yCircle = yCoord + 2;
		
		Ellipse2D.Double head = new Ellipse2D.Double(xCircle, yCircle, (diameter - 4), (diameter - 4));
		
		double xEye1 = xCircle + (diameter / 4);
		double xEye2 = xCircle + (3 * diameter / 4);
		double yEye = yCircle + (diameter / 4);
		
		Rectangle2D.Double eye1 = new Rectangle2D.Double((xEye1 - 1), (yEye - 1), 2, 2);
		Rectangle2D.Double eye2 = new Rectangle2D.Double((xEye2 - 1), (yEye - 1), 2, 2);
		Rectangle2D.Double nose = new Rectangle2D.Double((xCoord + radius - 1), (yCoord + radius - 1), 2, 2);

		g2.setColor(myOrange);
		
		if (fType == FaceType.Happy)
		{
			mouth = new Arc2D.Double(
					(xCoord + (diameter / 4)),
					(yCoord + (3 * diameter / 8)),
					(diameter / 2),
					(diameter / 2), 180, 180, 2);
			g2.draw(head);
		}
		else
		{
			mouth = new Arc2D.Double(
					(xCoord + (diameter / 4)),
					(yCoord + (9 * diameter / 16)),
					(diameter / 2),
					(diameter / 2), 0, 180, 2);
			g2.fill(head);
			g2.setColor(Color.BLACK);
		}
		g2.fill(eye1);
		g2.fill(eye2);
		g2.fill(nose);
		g2.fill(mouth);
	}
	
	public void fenceDraw(Graphics2D g2)
	{
		int leftBarX = xCoord + 2;
		int rightBarX = xCoord + cellSize - 6;
		int barY = yCoord + 2;
		
		Rectangle leftBar = new Rectangle(leftBarX, barY, 4, cellSize - 4);
		Rectangle rightBar = new Rectangle(rightBarX, barY, 4, cellSize - 4);
		
		double[] lineYCoords = new double[4];
		Line2D.Double[] horizlines = new Line2D.Double[4];
		
		Color myOrange = new Color(255, 140, 0);
		g2.setColor(myOrange);
		
		for(int lineCoord = 0; lineCoord <= 3; lineCoord++)
		{
			lineYCoords[lineCoord] = yCoord + (0.2 * cellSize) + (0.2 * lineCoord * cellSize);
			horizlines[lineCoord] = new Line2D.Double(leftBarX, lineYCoords[lineCoord], rightBarX, lineYCoords[lineCoord]);
			g2.draw(horizlines[lineCoord]);
		}
				
		g2.fill(leftBar);
		g2.fill(rightBar);
	}
	
	public enum FaceType {Happy, Sad, Fence};
	public FaceType fType;
	Color myOrange = new Color(255, 140, 0);
	public Arc2D.Double mouth;
	public int xCoord; //Coordinates of top left corner of cell.
	public int yCoord;
	public int cellSize;
	public double diameter;
	public double radius;
}
