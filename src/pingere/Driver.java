package pingere;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

public class Driver {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame parent = new JFrame("Pingere");
		Container content = parent.getContentPane();
		content.setLayout(new BorderLayout());
		parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		parent.setExtendedState(JFrame.MAXIMIZED_BOTH);
		ShapeDraw draw = new ShapeDraw();
		// State.setDrawPanel(draw);
		State.setShapeDrawPanel(draw);
		content.add(draw, BorderLayout.CENTER);
		content.add(new ToolbarPanel(), BorderLayout.PAGE_START);
		parent.pack();
		parent.setVisible(true);

	}

}
