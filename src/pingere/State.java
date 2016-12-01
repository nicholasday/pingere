package pingere;

// Used to set the size of the line drawn
import java.awt.BasicStroke;
// Used to set the color of the shapes drawn
import java.awt.Color;

// Used for a pop up dialog that lets the user
// choose any color they want
import javax.swing.JColorChooser;
// This is so that we can store the parent JFrame
// and pass it to JColorChooser
import javax.swing.JFrame;

// The reason we use a state class is so that we
// can communicate between ToolbarPanel and DrawPanel easily
// and not have too much overlap or too many methods
public class State {
	// We use static so that
	private static Color color = Color.black;
	private static BasicStroke stroke;
	private static Tool tool = Tool.Brush;
	private static DrawPanel DrawPanel;
	private static boolean clear = false;
	private static JFrame parentFrame;

	public enum Tool {
		Brush, Eraser, Ellipse, Rectangle, Clear, Chafic, Undo
	}

	public static void setColor(Color colorInput) {
		color = colorInput;
	}

	public static Color getColor() {
		return color;
	}

	public static void setStroke(BasicStroke strokeInput) {
		stroke = strokeInput;
	}

	public static BasicStroke getStroke() {
		return stroke;
	}

	public static void setTool(Tool toolInput) {
		tool = toolInput;
	}

	public static Tool getTool() {
		return tool;
	}

	public static void clear() {
		DrawPanel.clear();
		clear = true;
	}

	public static void undo() {
		DrawPanel.undo();
	}

	public static boolean isClear() {
		boolean prevClear = clear;
		if (clear) {
			clear = false;
		}
		return prevClear;
	}

	public static void setDrawPanel(DrawPanel drawPanelInput) {
		DrawPanel = drawPanelInput;
	}

	public static DrawPanel getDrawPanel() {
		return DrawPanel;
	}

	public static void colorChooser() {
		color = JColorChooser.showDialog(parentFrame, "Choose color", Color.white);
	}

	public static void setParent(JFrame parent) {
		parentFrame = parent;
	}

	public static JFrame getParent() {
		return parentFrame;
	}
}
