package pingere;

// Used to save the line width
import java.awt.BasicStroke;

// Used to save the color of the shape
import java.awt.Color;

public class Shape {
	// x and y store the left corner position that the shape draws
	// from and the start of the line for a line shape
	private int x = 0;
	private int y = 0;
	
	// x2 and y2 are the end points for the line shape
	private int x2 = 0;
	private int y2 = 0;
	
	// width and height are for the width and height of an ellipse 
	// which would be passed to their draw functions
	private int width = 0;
	private int height = 0;
	
	// Type stores the type of the shape based on the enum defined
	// below
	private Type type;
	
	// Stores the color of the shape
	private Color color;
	
	// Stores the line width of the shape
	private BasicStroke stroke;

	// This enum serves as a list of shapes we can store
	public enum Type {
		Rectangle, Ellipse, Brush, Eraser, Clear, Chafic
	}
	
	// We have multiple constructors because we have many different types
	// of shapes and want to support them all without passing in null values

	// This is the constructor for rectangle and ellipse shapes
	public Shape(int x, int y, int width, int height, Type type, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.type = type;
		this.color = color;
	}

	// This is the constructor for lines that width's are default
	public Shape(int x, int y, int x2, int y2, Color color) {
		this.x = x;
		this.y = y;
		this.x2 = x2;
		this.y2 = y2;
		this.type = Type.Brush;
		this.color = color;
		this.stroke = new BasicStroke(1);
	}

	// This is the constructor for lines that width's that change
	public Shape(int x, int y, int x2, int y2, Color color, BasicStroke stroke) {
		this.x = x;
		this.y = y;
		this.x2 = x2;
		this.y2 = y2;
		this.type = Type.Eraser;
		this.color = color;
		this.stroke = stroke;
	}

	// This is the constructor for the special image
	public Shape(int x, int y) {
		this.x = x;
		this.y = y;
		this.type = Type.Chafic;
	}

	//----------------------------------//
	// 			Arcessors 				//
	//----------------------------------//
	//									// 
	// These access the values of the   //
	// shape so that we can draw them   //
	// 									//
	//----------------------------------//

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getX2() {
		return x2;
	}

	public int getY2() {
		return y2;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Type getType() {
		return type;
	}

	public Color getColor() {
		return color;
	}

	public BasicStroke getStroke() {
		return stroke;
	}
}