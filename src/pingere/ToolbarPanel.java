package pingere;

import java.awt.BasicStroke;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ToolbarPanel extends JPanel {

	JButton brush, eraser, clear, rectangle, ellipse, save, chooseColor, chafic, undo; // making
																						// buttons
																						// for
																						// each
																						// of
																						// the
																						// tools
	JLabel statuslabel; // helps the user know which tool is selected

	public ToolbarPanel() {
		// creating all of the buttons
		// createImageIcon is what allows the buttons to be displayed as an
		// image, rather than text
		chooseColor = new JButton("Choose Color");
		chafic = new JButton("???");// hidden surprise
		brush = new JButton("", createImageIcon("paint_brush.png", "brush"));
		clear = new JButton("Clear");
		eraser = new JButton("", createImageIcon("eraser.png", "eraser"));
		rectangle = new JButton("", createImageIcon("rectangle.png", "rectangle"));
		ellipse = new JButton("", createImageIcon("ellipse.png", "ellipse"));
		undo = new JButton("Undo");
		statuslabel = new JLabel("");

		// Now adding ActionListeners for all of the buttons
		brush.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent selected) {
				State.setTool(State.Tool.Brush); // Uses the function in State
													// to set the tool
				statuslabel.setText("Brush selected");
			}
		});
		eraser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent selected) {
				State.setStroke(new BasicStroke(20)); // Makes the stroke size
														// bigger for the eraser
				State.setTool(State.Tool.Eraser); // Uses the function in State
													// to set the tool
				statuslabel.setText("Eraser selected");
			}
		});
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent selected) {
				State.Tool prevTool = State.getTool();
				State.setTool(State.Tool.Clear);
				State.clear();
				statuslabel.setText("Image cleared");
				State.setTool(prevTool);
			}
		});
		rectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent selected) {
				State.setTool(State.Tool.Rectangle); //// Uses the function in
														//// State to set the
														//// tool
				statuslabel.setText("Rectangle selected");
			}
		});
		ellipse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent selected) {
				State.setTool(State.Tool.Ellipse); //// Uses the function in
													//// State to set the tool
				statuslabel.setText("Ellipse selected");
			}
		});
		chooseColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				State.colorChooser();
			}
		});
		chafic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				State.setTool(State.Tool.Chafic);
			}
		});
		undo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				State.Tool prevTool = State.getTool();
				State.setTool(State.Tool.Undo);
				State.undo();
				statuslabel.setText("Undone");
				State.setTool(prevTool);
			}
		});
		// adding all of the buttons onto the frame
		add(clear);
		add(brush);
		add(eraser);
		add(rectangle);
		add(ellipse);
		add(chooseColor);
		add(undo);
		add(chafic);
		add(statuslabel);
	}

	// resizes the images by scaling them down to 20 by 20, so they look like a
	// button
	private ImageIcon createImageIcon(String url, String description) {
		java.net.URL imgURL = getClass().getResource(url);
		return new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
	}
}