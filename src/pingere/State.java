package pingere;

import java.awt.BasicStroke;
import java.awt.Color;

import javax.swing.JColorChooser;
import javax.swing.JFrame;

public class State {
	private static Color color = Color.black;
	private static BasicStroke stroke;
	private static Tool tool = Tool.Brush;
	private static DrawPanel DrawPanel;
	private static boolean clear = false;
	private static JFrame parentFrame;

	public enum Tool {
		Brush, Eraser, Ellipse, Rectangle, Clear, Chafic
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
