package hiVoltsPackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;


public class Background 
{
	public Background()
	{}
	
	public Background(int diam, HiVoltsBoardComponent tehCompo)
	{
		cellSize = diam;
		
		loseMessageX = (int) (1.4 * cellSize);
		loseMessageY = (int)(cellSize * 7);
		winMessageX = (int) (3 * cellSize);
		winMessageY = loseMessageY;
		resetButtonX = (int)(cellSize * 5);
		resetButtonY = (int) (cellSize * 7.5);
		quitButtonX = (int)(cellSize * 9);
		quitButtonY = resetButtonY;
		
		rect = new Rectangle(0, 0, 16 * cellSize, 14 * cellSize);
		compo = tehCompo;
		
		reset = new JButton("New Game");
		reset.setLocation(resetButtonX, resetButtonY);
		reset.setSize(cellSize * 2, cellSize);
		reset.addActionListener(resetListener);
		
		quit = new JButton("Quit");
		quit.setLocation(quitButtonX, quitButtonY);
		quit.setSize(cellSize * 2, cellSize);
		quit.addActionListener(quitListener);
		
		
		theFont = new Font("theFont", 1, cellSize * 2);
		
	}
	
	public void setGameOver(boolean setTo)
	{
		gameOver = setTo;
	}
	
	public void setWin(boolean setTo)
	{
		won = true;
	}
	
	public boolean getGameOver()
	{
		return gameOver;
	}
	
	public void draw(Graphics2D g2)
	{	
		g2.setColor(Color.BLACK);
		g2.fill(rect);
		
		if (gameOver)
		{
			g2.setColor(Color.RED);
			
			g2.setFont(theFont);
			
			if (won)
			{
				g2.drawString(winMessage, winMessageX, winMessageY);
			}
			else
			{
				g2.drawString(loseMessage, loseMessageX, loseMessageY);
			}
			reset.setVisible(true);
			quit.setVisible(true);
		}
		
	}
	
	class resetListenerClass implements ActionListener
	{	
		public void actionPerformed(ActionEvent event)
		{
			gameOver = false;
			won = false;
			compo.resetBoard();
		}
	}
	
	class quitListenerClass implements ActionListener
	{	
		public void actionPerformed(ActionEvent event)
		{
			compo.quit();
		}
	}
	
	public int cellSize;
	public Rectangle rect;
	public boolean gameOver;
	public boolean won;
	public String loseMessage = "GAME OVER!";
	public String winMessage = "YOU WON!";
	public int winMessageX;
	public int winMessageY;
	public int loseMessageX;
	public int loseMessageY;
	public int resetButtonX;
	public int resetButtonY;
	public int quitButtonX;
	public int quitButtonY;
	
	//public HiVoltsFrame frame;
	public HiVoltsBoardComponent compo;
	public JButton reset;
	public JButton quit;
	public resetListenerClass resetListener = new resetListenerClass();
	public quitListenerClass quitListener = new quitListenerClass();
	public Font theFont;

}
