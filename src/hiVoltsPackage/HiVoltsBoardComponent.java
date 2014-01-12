package hiVoltsPackage;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JComponent;

public class HiVoltsBoardComponent extends JComponent
{
	public HiVoltsBoardComponent()
	{}
	
	public HiVoltsBoardComponent(int size, int mhonum, int fencenum, HiVoltsFrame tehFrame)
	{
		cellSize = size;
		theFont = new Font("theFont", 1, (cellSize / 2));
		frame = tehFrame;
		
		//Creates background.
		bckgrnd = new Background(cellSize, this);
		
		//Creates lists of inner fences & mhos.
		mhoList = new ArrayList<Mho>();
		fenceList = new ArrayList<Fence>();
		
		//Saves values for number of mhos and number of fences.
		originalMhos = mhonum;
		fences = fencenum;
		
		resetBoard();
		
		
	}
	
	public void resetBoard()
	{
		bckgrnd.reset.setVisible(false);
		bckgrnd.quit.setVisible(false);
		this.remove(bckgrnd.reset);
		this.remove(bckgrnd.quit);
		frame.resetPlayerMover();
		
		mhos = originalMhos;
		
		mhoList.clear();
		fenceList.clear();
		borderFenceList.clear();
		
		//Sets cells to unoccupied.
		setAllUnoccupied();
		
		
		//Gives mhos locations & sets locations to occupied.
		for (int mhoDex = 0; mhoDex <= (mhos - 1); mhoDex++)
		{
			Point mhoPoint = nextEmptyRandom();
			Mho aMho = new Mho(mhoPoint, cellSize);
			mhoList.add(aMho);
			occupied[aMho.getX()][aMho.getY()] = true;
		}
		
		//Gives inner fences locations & sets locations to occupied.
		for (int fenceDex = 0; fenceDex <= (fences - 1); fenceDex++)
		{
			Point fencePoint = nextEmptyRandom();
			Fence aFence = new Fence(fencePoint, cellSize);
			fenceList.add(aFence);
			occupied[aFence.getX()][aFence.getY()] = true;
		}
		
		//Gives border fences locations and sets locations to occupied.
		int borderFenceDex = 0;
		Point borderFencePoint;
		
		//Top & Bottom Fences
		for (int xDex = 0; xDex <= 11; xDex++)
		{
			borderFencePoint = new Point(xDex, 0);
			Fence aFence = new Fence(borderFencePoint, cellSize);
			borderFenceList.add(borderFenceDex, aFence);
			occupied[aFence.getX()][aFence.getY()] = true;
			borderFenceDex++;
			
			borderFencePoint = new Point(xDex, 11);
			aFence = new Fence(borderFencePoint, cellSize);
			borderFenceList.add(aFence);
			occupied[aFence.getX()][aFence.getY()] = true;
			borderFenceDex++;
		}
		
		
		//Left & Right Fences
		for (int yDex = 1; yDex <= 10; yDex++)
		{
			borderFencePoint = new Point(0, yDex);
			Fence aFence = new Fence(borderFencePoint, cellSize);
			borderFenceList.add(borderFenceDex, aFence);
			occupied[aFence.getX()][aFence.getY()] = true;
			borderFenceDex++;
			
			borderFencePoint = new Point(11, yDex);
			aFence = new Fence(borderFencePoint, cellSize);
			borderFenceList.add(borderFenceDex, aFence);
			occupied[aFence.getX()][aFence.getY()] = true;
			borderFenceDex++;
		}
		
		//Creates player and gives player a location.
		Point playerPoint = nextEmptyRandom();
		you = new Player(playerPoint, cellSize);
		
		userCanMove = true;
		
		repaint();
		
	}
	
	private void setAllUnoccupied()
	{
		for (int i = 0; i < 12; i++)
		{
			for (int j = 0; j < 12; j++)
			{
				occupied[i][j] = false;
			}
		}
	}
	
	private void setInternalUnoccupied()
	{
		for (int i = 1; i < 11; i++)
		{
			for (int j = 1; j < 11; j++)
			{
				occupied[i][j] = false;
			}
		}
	}
	
	private void updateOccupied()
	{
		setInternalUnoccupied();
		
		for (Mho m : mhoList)
		{
			occupied[m.getX()][m.getY()] = true;
		}
		
		for (Fence f : fenceList)
		{
			occupied[f.getX()][f.getY()] = true;
		}
		
		occupied[you.getX()][you.getY()] = true;
		
	}
	
	private boolean isOccupied(Point p)
	{
		return isOccupied((int) p.getX(), (int) p.getY());
	}
	
	private boolean isOccupied(int x, int y)
	{
		return occupied[x][y];
	}
	
	private boolean isNonPlayerGamePiece(Point w)
	{
		return isNonPlayerGamePiece((int) w.getX(), (int) w.getY());
	}
	
	private boolean isNonPlayerGamePiece(int x, int y)
	{
		boolean toReturn = false;
		
		Point p = new Point(x,y);
		
		if (isOccupied(p) && (!you.getLocation().equals(p)))
		{
			toReturn = true;
		}
		
		return toReturn;
	}
	
	public Point randomPoint()
	{
		int nextX = gen.nextInt(10) + 1;
		int nextY = gen.nextInt(10) + 1;
		
		Point nextOcc = new Point(nextX, nextY);
		return nextOcc;
	}
	
	public Point nextEmptyRandom()
	{
		Point p = randomPoint();
		
		int nextX = (int) p.getX();
		int nextY = (int) p.getY();
		
		if(occupied[nextX][nextY])
		{
			return nextEmptyRandom();
		}
		else
		{
			return p;
		}
	}
	
	public Point randomJump()
	{
		Point p = randomPoint();
		
		int nextX = (int) p.getX();
		int nextY = (int) p.getY();
		
		if(notFence(nextX, nextY))
		{
			return p;
		}
		else
		{
			return randomJump();
		}
	}
	
	public boolean fence(Point p)
	{
		return (!notFence(p));
	}

	public boolean fence(int x, int y)
	{
		return (!notFence(x, y));
	}
	
	public boolean notFence(Point p)
	{
		return notFence((int )p.getX(), (int) p.getY());
	}
	
	public boolean notFence(int x, int y)
	{
		boolean toReturn = true;
		
		for(Fence f : fenceList)
		{
			if ((f.getX() == x) && (f.getY() == y))
			{
				toReturn = false;
				break;
			}
		}
		return toReturn;
	}
	
	public void paintComponent(Graphics g)
	{	
		Graphics2D g2 = (Graphics2D) g;
		
		bckgrnd.draw(g2);
		
		for (int i = 0; i <= (mhos - 1); i++)
		{
			Mho mo = mhoList.get(i);
			mo.draw(g2);
		}
		
		
		for (int j = 0; j <= (fences - 1); j++)
		{
			Fence phence = fenceList.get(j);
			phence.draw(g2);
		}
		
		for (int k = 0; k <= 43; k++)
		{
			Fence phence = borderFenceList.get(k);
			phence.draw(g2);
		}
		
		you.draw(g2);
		
		if (userCanMove)
		{
			g2.setFont(theFont);
			g2.drawString(yourTurn, (int) (12.5 * cellSize), (2 * cellSize));
		}
		
		if (bckgrnd.getGameOver())
		{
			add(bckgrnd.reset);
			add(bckgrnd.quit);
			bckgrnd.draw(g2);
		}
		
	}
	
	public void keyInput(Movement move)
	{
		if (userCanMove)
		{
			if (move != Movement.Jump)
			{
				userCanMove = false;
			}
			doKeyInput(move);
		}
		
	}
	
	private void doKeyInput(Movement move)
	{
		Point oldLoc = you.getLocation();
		int oldX = (int) oldLoc.getX();
		int oldY = (int) oldLoc.getY();
		int newX = oldX;
		int newY = oldY;
		Point newLoc = oldLoc;
		
		
		if (move == Movement.Left)
		{
			newX = (oldX - 1);
			newY = oldY;
			newLoc.setLocation(newX, newY);
		}
		
		else if (move == Movement.Right)
		{
			newX = (oldX + 1);
			newY = oldY;
			newLoc.setLocation(newX, newY);
		}
		
		else if (move == Movement.Up)
		{
			newX = oldX;
			newY = (oldY - 1);
			newLoc.setLocation(newX, newY);
		}
		
		else if (move == Movement.Down)
		{
			newX = oldX;
			newY = (oldY + 1);
			newLoc.setLocation(newX, newY);
		}

		else if (move == Movement.UpLeft)
		{
			newX = oldX - 1;
			newY = oldY - 1;
			newLoc.setLocation(newX, newY);
		}
		
		else if (move == Movement.UpRight)
		{
			newX = oldX + 1;
			newY = oldY - 1;
			newLoc.setLocation(newX, newY);
		}
		
		else if (move == Movement.DownLeft)
		{
			newX = oldX - 1;
			newY = oldY + 1;
			newLoc.setLocation(newX, newY);
		}
		
		else if (move == Movement.DownRight)
		{
			newX = oldX + 1;
			newY = oldY + 1;
			newLoc.setLocation(newX, newY);
		}
		
		else if (move == Movement.Jump)
		{
			newLoc = randomJump();
			newX = (int) newLoc.getX();
			newY = (int) newLoc.getY();
		}
		
		if (move != Movement.Nowhere)
		{
			occupied[oldX][oldY] = false;
		}
		
		you.setLocation(newLoc);
		
		repaint();
		
		if (occupied[newX][newY] && (move != Movement.Nowhere))
		{
			bckgrnd.setGameOver(true);
			//DO COOL THING!
		}
		
		else
		{
			occupied[newX][newY] = true;
			repaint();
			
			if (move != Movement.Jump)
			{
				nextTurn();
			}
		}
		
	}
	
	private void nextTurn()
	{	
		ArrayList<Mho> newMhoList = new ArrayList<Mho>(); 
		
		if (!bckgrnd.getGameOver())
		{
			for (Mho y : mhoList)
			{
				Mho newMho = y;
				
				moveMho(newMho);
				
				newMhoList.add(newMho);
				
				if (fence(newMho.getLocation()))
				{
					newMhoList.remove(newMho);
				}
			}
		}
		mhoList = newMhoList;
		mhos = mhoList.size();
		userCanMove = true;
		updateOccupied();
		
		if(mhos == 0)
		{
			bckgrnd.setWin(true);
			bckgrnd.setGameOver(true);
			repaint();
		}
	}
	
	private void moveMho(Mho w)
	{
		//Same row or column move.
		boolean sameRow = sameRow(w, you);
		boolean sameColumn = sameColumn(w, you);
	
		if (sameRow || sameColumn)
		{
			directMove(w, you, sameRow, sameColumn);
		}
		
		//Diagonal move.
		else if (diagonal(w, you) && (!isNonPlayerGamePiece(diagonalMoveLocation(w, you))))
		{
			w.setLocation(diagonalMoveLocation(w, you));
			
		}
		
		//Horizontal move.
		else if ((horizGreaterThanVert(w, you)) && (!isNonPlayerGamePiece(horizMoveLocation(w, you))))
		{
			w.setLocation(horizMoveLocation(w, you));
		}
		
		//Vertical move.
		else if ((!(horizGreaterThanVert(w, you))) && (!isNonPlayerGamePiece(horizMoveLocation(w, you))))
		{
			w.setLocation(vertMoveLocation(w, you));
		}
		
		//Diagonal move to fence.
		else if (diagonal(w, you) && (!notFence(diagonalMoveLocation(w, you))))
		{
			w.setLocation(diagonalMoveLocation(w, you));
			
		}
		
		//Horizontal move to fence.
		else if ((horizGreaterThanVert(w, you)) && (!notFence(horizMoveLocation(w, you))))
		{
			w.setLocation(horizMoveLocation(w, you));
		}
		
		//Vertical move to fence.
		else if ((!(horizGreaterThanVert(w, you))) && (!notFence(horizMoveLocation(w, you))))
		{
			w.setLocation(vertMoveLocation(w, you));
		}
		
		
		Point newLocation = w.getLocation();
		
		if (newLocation.equals(you.getLocation()))
		{
			bckgrnd.setGameOver(true);
			repaint();
		}
	}
	
	private boolean sameRow(GamePiece a, GamePiece b)
	{
		return (a.getY() == b.getY());
	}
	
	private boolean sameColumn(GamePiece a, GamePiece b)
	{
		return (a.getX() == b.getX());
	}
	
	private void directMove(GamePiece pieceThatWillMove, GamePiece referencePiece, boolean row, boolean column)
	{
		//If same row, change x & change column number.
		//If same column, change y & change row number.
		
		Point r = referencePiece.getLocation();
		
		Point p = pieceThatWillMove.getLocation();
		
		int newCol = (int) p.getX();
		int newRow = (int) p.getY();
		
		if (row)
		{
			newCol = moveNumCloser(newCol, (int) r.getX());
		}
		
		if (column)
		{
			newRow = moveNumCloser(newRow, (int) r.getY());
		}
		
		p = new Point(newCol, newRow);
		
		pieceThatWillMove.setLocation(p);
	}
	
	private boolean diagonal(GamePiece a, GamePiece b)
	{
		int dist1 = Math.abs((a.getX() - b.getX()));
		int dist2 = Math.abs((a.getY() - b.getY()));
		return (dist1 == dist2);
	}

	private Point diagonalMoveLocation(GamePiece thatMoves, GamePiece reference)
	{
		int oldX = thatMoves.getX();
		int oldY = thatMoves.getY();
		
		int refX = reference.getX();
		int refY = reference.getY();
		
		int newX = moveNumCloser(oldX, refX);
		int newY = moveNumCloser(oldY, refY);
		
		Point igger = new Point(newX, newY);
		return igger;
	}
	
	private boolean horizGreaterThanVert(GamePiece a, GamePiece b)
	{
		boolean toReturn = false;
		int horiz = Math.abs((a.getX() - b.getX()));
		int vert = Math.abs((a.getY() - b.getY()));
		
		if (horiz > vert)
		{
			toReturn = true;
		}
		
		return toReturn;
	}

	private Point horizMoveLocation(GamePiece thatMoves, GamePiece reference)
	{
		int moveX = thatMoves.getX();
		int refX = reference.getX();
		int newX = moveNumCloser(moveX, refX);
		
		Point p = new Point(newX, thatMoves.getY());
		
		return p;
		
	}
	
	private Point vertMoveLocation(GamePiece thatMoves, GamePiece reference)
	{
		int moveY = thatMoves.getY();
		int refY = reference.getY();
		int newY = moveNumCloser(moveY, refY);
		
		Point p = new Point(thatMoves.getX(), newY);
		
		return p;
		
	}
	
	private int moveNumCloser(int numThatWillMove, int otherNum)
	{
		if (otherNum > numThatWillMove)
		{
			return (numThatWillMove + 1);
		}
		if (otherNum < numThatWillMove)
		{
			return (numThatWillMove - 1);
		}
		else 
		{
			return otherNum;
		}
	}
	
	public void quit()
	{
		frame.dispose();
	}
	
	public int cellSize;
	public HiVoltsFrame frame;
	
	//Number of mhos and fences.
	public int originalMhos;
	public int mhos;
	public int fences;
	public int borderFences;
	
	public boolean[][] occupied = new boolean[12][12];
	public Random gen = new Random();
	public boolean userCanMove;
	public Font theFont;
	public String yourTurn = "Your turn.";
	
	public Background bckgrnd;
	public ArrayList<Mho> mhoList;
	public ArrayList<Fence> fenceList;
	public ArrayList<Fence> borderFenceList = new ArrayList<Fence>();
	public Player you;
	
	public enum Movement {Left, Right, Up, Down, UpLeft, UpRight, DownLeft, DownRight, Nowhere, Jump};
	
	public static final long serialVersionUID = 823;
	
}
