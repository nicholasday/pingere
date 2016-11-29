package pingere;

import java.awt.BasicStroke;
import java.awt.Color;

public class Shape
{
	private int x = 0;
	private int y = 0;
	private int x2 = 0;
	private int y2 = 0;
	private int width = 0;
	private int height = 0;
	private Type type;
	private Color color;
	private BasicStroke stroke;
	
	enum Type {
		Rectangle, Ellipse, Brush, Eraser
	}
	public Shape(int x, int y, int width, int height, Type type, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.type = type;
		this.color = color;
	}
	
	public Shape(int x, int y, int x2, int y2, Color color) {
		this.x = x;
		this.y = y;
		this.x2 = x2;
		this.y2 = y2;
		this.type = Type.Brush;
		this.color = color;
		this.stroke = new BasicStroke(1);
	}

	public Shape(int x, int y, int x2, int y2, Color color, BasicStroke stroke) {
		this.x = x;
		this.y = y;
		this.x2 = x2;
		this.y2 = y2;
		this.type = Type.Eraser;
		this.color = color;
		this.stroke = stroke;
	}
	public int getX() { return x; }
	public int getY() { return y; }
	public int getX2() { return x2; }
	public int getY2() { return y2; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public Type getType() { return type; }
	public Color getColor() { return color; }
	public BasicStroke getStroke() { return stroke; }
}