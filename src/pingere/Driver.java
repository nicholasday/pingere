package pingere;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.event.*;

public class Driver
{

	public enum State {
		DRAW, OVAL
	}
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		JFrame frame = new JFrame ("Pingere");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		Draw draw = new Draw();
		frame.getContentPane().add(new Oval2());
		frame.pack();
		frame.setVisible(true);

	}

	
}
