package pingere;

import java.awt.AWTException;

// Used to set the color of the shapes drawn
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
// Used for a pop up dialog that lets the user
// choose any color they want
import javax.swing.JColorChooser;
// This is so that we can store the parent JFrame
// and pass it to JColorChooser
import javax.swing.JFrame;


import javax.swing.JFileChooser;

// The reason we use a state class is so that we
// can communicate between ToolbarPanel and DrawPanel easily
// and not have too much overlap or too many methods
public class State {
	// We use static so that we can access the variables from
	// any class and so that they don't change class to class
	private static Color color = Color.black;
	private static int strokeSize;
	private static Tool tool = Tool.Brush;
	private static File file;
	private static boolean fileOpened = false;

	// DrawPanel variable to call DrawPanel.clear() from ToolbarPanel
	private static DrawPanel DrawPanel;

	// parentFrame variable to pass parent to JColorChooser
	private static JFrame parentFrame;

	// This is for a special function to determine the clear state
	private static boolean clear = false;

	// A list of tools as a convenience for setting them from
	// ToolbarPanel and accessing from DrawPanel
	public enum Tool {
		Brush, Eraser, Ellipse, Rectangle, Clear, Chafic, Save
	}

	// Getters and setters for the color variable which
	// is used to change the color of the shapes
	public static void setColor(Color colorInput) {
		color = colorInput;
	}

	public static Color getColor() {
		return color;
	}

	// Getters and setters for the stroke size
	public static void setStroke(int strokeInput) {
		strokeSize = strokeInput;
	}

	public static int getStroke() {
		return strokeSize;
	}

	// Convenience functions for the increase/decreaseStroke
	// ToolbarPanel buttons so we can easily increase/decrease
	// the stroke by a certain amount every time
	public static void increaseStroke() {
		strokeSize = strokeSize + 5;
	}

	public static void decreaseStroke() {
		if (strokeSize > 1)
			strokeSize = strokeSize - 5;
	}

	// Getter and setter for tool so that ToolbarPanel
	// can set the Tool for DrawPanel to use
	public static void setTool(Tool toolInput) {
		tool = toolInput;
	}

	public static Tool getTool() {
		return tool;
	}

	// Made so that ToolbarPanel can force a redraw on
	// DrawPanel after making a clear shape
	public static void clear() {
		DrawPanel.clear();
		clear = true;
	}

	// Tests the state of the clear for DrawPanel
	// so that it doesn't draw the latest drawn shape
	// before the clear
	public static boolean isClear() {
		boolean prevClear = clear;
		if (clear) {
			clear = false;
		}
		return prevClear;
	}

	// Setter for Driver to set the DrawPanel so that we can access
	// DrawPanel.clear() from ToolbarPanel
	public static void setDrawPanel(DrawPanel drawPanelInput) {
		DrawPanel = drawPanelInput;
	}

	// Getter for DrawPanel
	public static DrawPanel getDrawPanel() {
		return DrawPanel;
	}

	// Launches the color chooser and passes the parent JFrame and sets the
	// color
	// based on JColorChooser's return value
	public static void colorChooser() {
		color = JColorChooser.showDialog(parentFrame, "Choose color", Color.white);
	}

	// Setter for Driver to set the parent JFrame so that we
	// can pass that as an argument to JColorChooser
	public static void setParent(JFrame parent) {
		parentFrame = parent;
	}

	public static void save() {
		try {

			Robot robot = new Robot();
			// Capture the screen shot of the area of the screen defined by the
			// rectangle
			BufferedImage bi = robot.createScreenCapture(new Rectangle((int) DrawPanel.getLocationOnScreen().getX(),
					(int) DrawPanel.getLocationOnScreen().getY(), (int) DrawPanel.getBounds().getWidth(),
					(int) DrawPanel.getBounds().getHeight()));
			ImageIO.write(bi, "png", new File("image.png"));

		} catch (AWTException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Opens JFileChooser and checks to see if the user has
	// selected a file and then sets `file` to be the value returned
	// from the user's selection
	public static void openFileChooser() {
		JFileChooser fileChooser = new JFileChooser();
		int result = fileChooser.showOpenDialog(parentFrame);
		if (result == JFileChooser.APPROVE_OPTION) {
			fileOpened = true;
		    file = fileChooser.getSelectedFile();
		    System.out.println("Selected file: " + file.getAbsolutePath());
		    DrawPanel.drawImage();
		}
	}
	
	// Getter for the file that has been selected
	public static File getFile() {
		return file;
	}
	
	// Getter for the parent JFrame
	public static JFrame getParent() {
		return parentFrame;
	}
}
