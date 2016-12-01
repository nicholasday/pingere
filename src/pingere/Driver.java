package pingere;

// BorderLayout places our panels in certain places
import java.awt.BorderLayout;

// Container is used to allow us to add the panels easily
import java.awt.Container;

// JFrame is the what we use to put all of our panels into and
// create the window
import javax.swing.JFrame;

public class Driver {
	public static void main(String[] args) {
		// Set up the parent JFrame on which we layer all our panels
		JFrame parent = new JFrame("Pingere");
		parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		parent.setExtendedState(JFrame.MAXIMIZED_BOTH);

		// We pass parent to the static State class so that we can pass
		// parent to JColorChooser in it's arguments
		State.setParent(parent);

		// Sets up the container to add items to
		Container content = parent.getContentPane();

		// setDrawPanel is used for calling the clear function from the DrawPanel
		// from ToolbarPanel
		DrawPanel draw = new DrawPanel();
		State.setDrawPanel(draw);

		// We use BorderLayout to ensure that ToolbarPanel is at top and the 
		// Draw panel is at front and center
		content.setLayout(new BorderLayout());
		content.add(draw, BorderLayout.CENTER);
		content.add(new ToolbarPanel(), BorderLayout.PAGE_START);

		// The parent frame is now finished and we display it on the application
		parent.pack();
		parent.setVisible(true);
	}

}
