package hiVoltsPackage;

import javax.swing.JOptionPane;

public class Main
{
	public static void main(String[] args)
	{
		Integer fences = new Integer(JOptionPane.showInputDialog("How many fences would you like? (Usually 20)"));
		Integer mhos = new Integer(JOptionPane.showInputDialog("How many mhos would you like? (Usually 12)"));

		
		HiVoltsFrame hivolts = new HiVoltsFrame();
		hivolts.setUp(mhos, fences);
		hivolts.setVisible(true);
	}
}