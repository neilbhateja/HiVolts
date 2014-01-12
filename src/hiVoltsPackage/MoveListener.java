package hiVoltsPackage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//Huge class.
//Listener for keyboard input.
class MoveListener implements KeyListener
{
	
	public void addHiVoltsBoardComponent(HiVoltsBoardComponent tehCompo)
	{
		compo = tehCompo;
	}
	
	public void keyPressed(KeyEvent event)
	{
		if (released)
		{
			code = event.getKeyCode();
			compo.keyInput(direction[code]);
			released = false;
		}
	}
	
	public void keyReleased(KeyEvent event)
	{	
		if ((event.getKeyCode() == code) && (code < direction.length))
		{	
			released = true;
		}
	}
	
	
	public void keyTyped(KeyEvent event)
	{
		
	}

	public int code;
	public boolean released = true;
	public HiVoltsBoardComponent compo;
	public HiVoltsBoardComponent.Movement[] direction = new HiVoltsBoardComponent.Movement[156];
	
	// Sets up array with movements, etc.
	{	
		//Arrow keys.
		direction[37] = HiVoltsBoardComponent.Movement.Left;
		direction[39] = HiVoltsBoardComponent.Movement.Right;
		direction[38] = HiVoltsBoardComponent.Movement.Up;
		direction[40] = HiVoltsBoardComponent.Movement.Down;
		//Letters.
		direction[81] = HiVoltsBoardComponent.Movement.UpLeft;
		direction[87] = HiVoltsBoardComponent.Movement.Up;
		direction[69] = HiVoltsBoardComponent.Movement.UpRight;
		direction[65] = HiVoltsBoardComponent.Movement.Left;
		direction[83] = HiVoltsBoardComponent.Movement.Nowhere;
		direction[68] = HiVoltsBoardComponent.Movement.Right;
		direction[90] = HiVoltsBoardComponent.Movement.DownLeft;
		direction[88] = HiVoltsBoardComponent.Movement.Down;
		direction[67] = HiVoltsBoardComponent.Movement.DownRight;
		direction[74] = HiVoltsBoardComponent.Movement.Jump;
		//Number pad without NumLock.
		direction[36] = HiVoltsBoardComponent.Movement.UpLeft;
		direction[38] = HiVoltsBoardComponent.Movement.Up;
		direction[33] = HiVoltsBoardComponent.Movement.UpRight;
		direction[37] = HiVoltsBoardComponent.Movement.Left;
		direction[12] = HiVoltsBoardComponent.Movement.Nowhere;
		direction[39] = HiVoltsBoardComponent.Movement.Right;
		direction[35] = HiVoltsBoardComponent.Movement.DownLeft;
		direction[40] = HiVoltsBoardComponent.Movement.Down;
		direction[34] = HiVoltsBoardComponent.Movement.DownRight;
		direction[155] = HiVoltsBoardComponent.Movement.Jump;
		//Number pad with NumLock.
		direction[103] = HiVoltsBoardComponent.Movement.UpLeft;
		direction[104] = HiVoltsBoardComponent.Movement.Up;
		direction[105] = HiVoltsBoardComponent.Movement.UpRight;
		direction[100] = HiVoltsBoardComponent.Movement.Left;
		direction[101] = HiVoltsBoardComponent.Movement.Nowhere;
		direction[102] = HiVoltsBoardComponent.Movement.Right;
		direction[97] = HiVoltsBoardComponent.Movement.DownLeft;
		direction[98] = HiVoltsBoardComponent.Movement.Down;
		direction[99] = HiVoltsBoardComponent.Movement.DownRight;
		direction[96] = HiVoltsBoardComponent.Movement.Jump;

	}
	
}