package pingere;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Container;
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
		JFrame parent = new JFrame("Pingere");
		Container content = parent.getContentPane();
		content.setLayout(new BorderLayout());
		JPanel mainPanel = new JPanel();
		parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		parent.setExtendedState(JFrame.MAXIMIZED_BOTH);
		Draw draw = new Draw();
		content.add(draw, BorderLayout.SOUTH);
		content.add(new ToolbarPanel(), BorderLayout.NORTH);
		parent.pack();
		parent.setVisible(true);
	
	}

	
}
