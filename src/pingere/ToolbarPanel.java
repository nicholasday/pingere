package pingere;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ToolbarPanel extends JPanel {

	// JButtons for all of the tools
	JButton brush, eraser, clear, rectangle, ellipse, chooseColor, chafic;
	
	// These buttons are for the save and open features
	JButton save, open;

	// JButtons for the stroke size options
	JButton increaseStroke, decreaseStroke;

	// Shows the user which tool has been selected
	JLabel statuslabel;

	// Sets up the ToolbarPanel
	public ToolbarPanel() {
		// -----------------------------//
		// Button Initialization //
		// -----------------------------//
		// Creates all of the buttons and sets their functions
		// createImageIcon is what allows the buttons to be displayed as an
		// image, rather than text

		chooseColor = new JButton("Choose Color");
		chafic = new JButton("???");// hidden surprise
		brush = new JButton("", createImageIcon("paint_brush.png", "brush"));
		clear = new JButton("Clear");
		eraser = new JButton("", createImageIcon("eraser.png", "eraser"));
		rectangle = new JButton("", createImageIcon("rectangle.png", "rectangle"));
		ellipse = new JButton("", createImageIcon("ellipse.png", "ellipse"));
		increaseStroke = new JButton("+");
		decreaseStroke = new JButton("-");
		save = new JButton("Save");
		open = new JButton("Open");
		statuslabel = new JLabel("");

		// ----------------------------------//
		// Action Listeners //
		// ----------------------------------//

		// Now adding ActionListeners for all of the buttons to listen for
		// button clicks

		// Increases the stroke by 5
		increaseStroke.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent selected) {
				State.increaseStroke();
				statuslabel.setText("Brush size increased");
			}
		});

		// Decreases the stroke by 5
		decreaseStroke.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent selected) {
				State.decreaseStroke();
				statuslabel.setText("Brush size decreased");
			}
		});

		// Sets the eraser tool with a larger stroke size
		eraser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent selected) {
				State.setStroke(20); // Makes the stroke size bigger for the
										// eraser
				State.setTool(State.Tool.Eraser); // Uses the function in State
													// to set the tool
				statuslabel.setText("Eraser selected");
			}
		});

		// Clears the image
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent selected) {
				// We have to get the previous tool so that we can
				// set it back after we set clear as the tool
				State.Tool prevTool = State.getTool();
				State.setTool(State.Tool.Clear);
				State.clear();
				statuslabel.setText("Image cleared");
				State.setTool(prevTool);
			}
		});

		// Selects the rectangle tool
		rectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent selected) {
				State.setTool(State.Tool.Rectangle); //// Uses the function in
														//// State to set the
														//// tool
				statuslabel.setText("Rectangle selected");
			}
		});

		// Selects the ellipse tool
		ellipse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent selected) {
				State.setTool(State.Tool.Ellipse); //// Uses the function in
													//// State to set the tool
				statuslabel.setText("Ellipse selected");
			}
		});

		// Calls State.colorChooser() which launches JColorChooser
		// and sets the return value as State.color
		chooseColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				State.colorChooser();
			}
		});

		// Calls State.openFileChooser() which launches JFileChooser
		// and sets the return value as State.file
		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				State.openFileChooser();
			}
		});

		// Adds that special image button to the ToolbarPanel
		chafic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				State.setTool(State.Tool.Chafic);
			}
		});

		// Saves the image and stores the previous tool and reselects
		// it after the image has been saved
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				State.Tool prevTool = State.getTool();
				State.setTool(State.Tool.Save);
				State.save();
				statuslabel.setText("Image Saved");
				State.setTool(prevTool);
			}
		});
		// Sets the brush tool
		brush.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				State.setTool(State.Tool.Brush);
				statuslabel.setText("Brush selected");
			}
		});

		// Adds all of the above buttons to the program
		add(clear);
		add(brush);
		add(eraser);
		add(rectangle);
		add(ellipse);
		add(chooseColor);
		add(chafic);
		add(increaseStroke);
		add(decreaseStroke);
		add(save);
		add(open);
		add(statuslabel);
	}

	// Resizes the images by scaling them down to 20 by 20, so they look like a
	// button
	// Also loads the image for use as the corresponding JButton image
	private ImageIcon createImageIcon(String url, String description) {
		java.net.URL imgURL = getClass().getResource(url);
		return new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
	}
}