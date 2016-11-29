package pingere;

import java.awt.BasicStroke;
import java.awt.Color;

public class State {
	private static Color color = Color.blue;
	private static BasicStroke stroke;
	private static Tool tool = Tool.Brush;
	private static DrawState state = DrawState.Draw;
	private static Draw DrawPanel;
	private static ShapeDraw ShapeDrawPanel;

	public enum Tool {
		Brush, Eraser, Ellipse, Rectangle, Clear
	}

	public enum DrawState {
		Clear, Draw
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
		ShapeDrawPanel.clear();
	}

	public static void saveImage() {
		DrawPanel.save();
	}

	public static DrawState getDrawState() {
		return state;
	}

	public static void setDrawState(DrawState drawStateInput) {
		state = drawStateInput;
	}

	public static void setDrawPanel(Draw drawPanelInput) {
		DrawPanel = drawPanelInput;
	}

	public static Draw getDrawPanel() {
		return DrawPanel;
	}

	public static void setShapeDrawPanel(ShapeDraw shapeDrawPanelInput) {
		ShapeDrawPanel = shapeDrawPanelInput;
	}

	public static ShapeDraw getShapeDrawPanel() {
		return ShapeDrawPanel;
	}
}
