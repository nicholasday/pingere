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
		JFrame parent = new JFrame ("Pingere");
		JPanel content = new JPanel ();
		JPanel content1 = new JPanel ();
		parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		parent.setExtendedState(JFrame.MAXIMIZED_BOTH);
		Draw draw = new Draw();
		content.add(new ToolbarPanel());
		content1.add(new Oval2());
		parent.add(content);
		parent.pack();
		parent.setVisible(true);
	
	}

	
}
