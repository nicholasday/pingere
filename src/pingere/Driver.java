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
		parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		parent.setExtendedState(JFrame.MAXIMIZED_BOTH);
		content.add(new Draw(), BorderLayout.CENTER);
		content.add(new ToolbarPanel(), BorderLayout.PAGE_START);
		parent.pack();
		parent.setVisible(true);
	
	}

	
}
