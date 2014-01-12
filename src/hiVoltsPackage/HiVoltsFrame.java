package hiVoltsPackage;
import javax.swing.JFrame;



public class HiVoltsFrame extends JFrame 
{
	/**
	 * Stupid.
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	public void setUp(int mhoNum, int fenceNum)
	{	
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		compo = new HiVoltsBoardComponent(cellSize, mhoNum, fenceNum, this);
		
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setTitle("HiVolts!");
		
		this.setLayout(null);
		
		compo.setLocation(0,0);
		compo.setSize(cellSize * 16, cellSize * 14);
		this.add(compo);
		
		playerMover.addHiVoltsBoardComponent(compo);
		this.addKeyListener(playerMover); //!!
	}
	
	public void resetPlayerMover()
	{
		this.setVisible(false);
		this.setVisible(true);
		this.addKeyListener(playerMover);
	}
	

	
	
	public MoveListener playerMover = new MoveListener();
	public HiVoltsBoardComponent compo;
	public static int FRAME_WIDTH = 1024;
	public static int FRAME_HEIGHT = 720;
	public int cellSize = 56;
	
	
	
}
