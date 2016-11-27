package pingere;

import java.awt.Color;

public class State
{
	private static Color color = Color.blue;
	private static Tool tool = Tool.Brush;

	public enum Tool {
		Brush, Eraser
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

}
