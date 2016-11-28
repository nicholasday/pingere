package pingere;

import java.awt.Color;

import javax.swing.JPanel;

public class State
{
	private static Color color = Color.blue;
	private static Tool tool = Tool.Brush;
	private static DrawState state = DrawState.Draw;
	private static Draw DrawPanel;

	public enum Tool {
		Brush, Eraser
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
	
	public static void setTool(Tool toolInput) {
		tool = toolInput;
	}
	
	public static Tool getTool()
	{
		return tool;
	}
	
	public static DrawState getDrawState() {
		return state;
	}
	
	public static void setDrawState(DrawState drawStateInput) {
		state = drawStateInput;
		if (drawStateInput == DrawState.Clear) {
			DrawPanel.clear();
		}
	}
	
	public static void setDrawPanel(Draw drawPanelInput) {
		DrawPanel = drawPanelInput;
	}
	
	public static Draw getDrawPanel() {
		return DrawPanel;
	}

}
