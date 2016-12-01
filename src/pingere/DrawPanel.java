package pingere;

// BasicStroke is used to increase the size of the line
import java.awt.BasicStroke;

// Color is used to change the color of the line and shapes and JColorChooser
import java.awt.Color;

// Graphics and Graphics2D are used for their functions in drawing with JPanels
import java.awt.Graphics;
import java.awt.Graphics2D;

// These are used to capture the position and status of the Mouse (dragging, clicking, etc)
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

// Ellipse2D, Line2D, and Rectangle2D are used to draw ellipses, lines, and rectangles respectively
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

// BufferedImage and ImageIO are used to draw the special image we have saved in our project
// File and IOException are used to load that special image
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

// ArrayList and Shape.Type are used to create a list of shapes that we draw to the this panel
import java.util.ArrayList;
import pingere.Shape.Type;

// JPanel is the basis for all of the above functions used. Without it we wouldn't be able to 
// draw anything at all.
import javax.swing.JPanel;

public class DrawPanel extends JPanel {
	// These are used to record the mouse position when the user has clicked
	// and when the user has released the mouse click or is in the process
	// of dragging the mouse
	private int startX;
	private int startY;
	private int endX;
	private int endY;
	
	// x and y are used for drawing resizable shapes like ellipses and rectangles
	private int x, y;
	
	// width and height are used as the 
	private int height;
	private int width;

	// We create an ArrayList of Shapes to conserve the shapes we've already drawn 
	// when paintComponent redraws the panel completely
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	
	// This is so that we can store the special image we have and draw it 
	// to the screen.
	private BufferedImage chafic;

	// This is the constructor for DrawPanel
	public DrawPanel() {
		// Tries to load the special imge and throw an exception if it
		// doesn't load
		try {
			chafic = ImageIO.read(new File("Chafic.png"));
		} catch (IOException ie) {
			ie.printStackTrace();
		}
		
		// Sets the background white
		setBackground(Color.white);
		
		// Adds mouse and mouse motion listeners to capture mouse info
		addMouseListener(new MouseAdapter());
		addMouseMotionListener(new MouseMotionAdapter());
	}

	// We override JPanel's default paintComponent and add our own logic 
	// and methods to draw to the screen
	@Override
	public void paintComponent(Graphics g) {
		// Calls the original paintComponent that we overrided to make
		// sure that we don't remove any functionality that we didn't intend to
		super.paintComponent(g);
		
		// We cast g to Graphics2D because it is more featureful and has 
		// functions we need
		Graphics2D g2d = (Graphics2D) g;

		// This for loop loops through all of the shapes in our shapes array
		// and draws them to the screen
		for (Shape shape : shapes) {
			// First we set the color that we saved for the shape originally 
			// (so that we don't draw with the current color which can be different
			g2d.setColor(shape.getColor());
			
			// We have to check if the shape is eraser and if so change the color to white
			// so that it will blend in with the background
			if (shape.getType() == Type.Eraser) {
				g2d.setColor(Color.white);
			}

			// We use switch over shape.getType() so as to not have repetitive
			// else ifs. The types of shape could be any of the drawable items on
			// this panel. So we can draw lines, white eraser lines, ellipses, 
			// clear (which is a white rectangle the dimensions of the panel), 
			// rectangles, and that special image
			switch (shape.getType()) {
				case Brush:
					// Sets the p
					g2d.setStroke(new BasicStroke(shape.getStroke()));
					g2d.draw(new Line2D.Double(shape.getX2(), shape.getY2(), shape.getX(), shape.getY()));
					break;
				case Eraser:
					g2d.setStroke(new BasicStroke(shape.getStroke()));
					g2d.draw(new Line2D.Double(shape.getX2(), shape.getY2(), shape.getX(), shape.getY()));
					break;
				case Ellipse:
					g2d.setStroke(new BasicStroke(1));
					g2d.draw(new Ellipse2D.Double(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight()));
					break;
				case Rectangle:
					g2d.setStroke(new BasicStroke(1));
					g2d.draw(new Rectangle2D.Double(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight()));
					break;
				case Clear:
					g2d.fill(new Rectangle2D.Double(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight()));
					break;
				case Chafic:
					g2d.drawImage(chafic, shape.getX(), shape.getY(), null);
				default:
					break;
			}
		}

		// This is where we draw our "in-progress" shapes or shapes that are 
		// being resized by the user when the mouse click hasn't been 
		// released
		g2d.setColor(State.getColor());

		// The if statement is to make sure to not do the redraw shapes 
		// right after the image has been cleared. Otherwise when you hit 
		// clear it will clear all but the last shape
		if (!State.isClear()) {
			switch (State.getTool()) {
				case Brush:
					break;
				case Ellipse:
					g2d.setStroke(new BasicStroke(State.getStroke()));
					g2d.draw(new Ellipse2D.Double(x, y, width, height));
					break;
				case Eraser:
					break;
				case Rectangle:
					g2d.setStroke(new BasicStroke(State.getStroke()));
					g2d.draw(new Rectangle2D.Double(x, y, width, height));
					break;
				default:
					break;
			}
		}
	}

	// Here we create a private MotionMouseListener class based
	// on MouseMotionListener interface to get when the mouse has been
	// dragged to get resizable shapes
	private class MouseMotionAdapter implements MouseMotionListener {
		// Required for the MouseMotionListener interface
		public void mouseMoved(MouseEvent event) { }

		// Here's where the magic happens. We get the current end point of the mouse
		// to redraw and draw lines
		public void mouseDragged(MouseEvent event) {
			// Gets the x and y pos from mouse
			endX = event.getX();
			endY = event.getY();

			// If we are using the brush we draw a line from start to finish then 
			// set the start of the next line to be the end of the current one. 
			// This way we draw an almost infinite number of lines as the user drags
			// their mouse but it comes together to be seen as one.
			if (State.getTool() == State.Tool.Brush) {
				Shape line = new Shape(startX, startY, endX, endY, State.getColor(), Shape.Type.Brush, State.getStroke());
				shapes.add(line);

				startX = endX;
				startY = endY;

			// The same logic applies for Eraser, but the size is bigger and it's white 
			// to blend into the background and supersede the previous shapes it covered
			} else if (State.getTool() == State.Tool.Eraser) {
				Shape erased = new Shape(startX, startY, endX, endY, State.getColor(), Shape.Type.Eraser, State.getStroke());
				shapes.add(erased);

				startX = endX;
				startY = endY;
				
			// Here we find the coordinates of the redrawable shapes (ellipse and rectangle)
			} else {
				// We use Math.min for startX and endX (and Y) because we want to support
				// the user drawing the shape in a direction before the startX/Y which 
				// would mean that the place where we start drawing (the top left corner
				// of the shape) is actually endX/Y not startX/Y
				x = Math.min(startX, endX);
				y = Math.min(startY, endY);

				// We use Math.abs because we want to support the user drawing in any
				// direction. The height and width will always be the same regardless
				// of direction and orientation
				height = Math.abs(startY - endY);
				width = Math.abs(startX - endX);
			}

			// We repaint at the end to ensure that the user's dragging changes are updated
			repaint();
		}
	}

	// We create MouseAdapter which implements MouseListener so that we can
	// get the start point of when the user started dragging and the release point
	// so that we can draw the final shape
	private class MouseAdapter implements MouseListener {
		// Required for the MouseListener interface
		public void mouseClicked(MouseEvent event) { }
		public void mouseEntered(MouseEvent event) { }
		public void mouseExited(MouseEvent event) { }

		// Code that creates the shape and adds it to the shapes array
		// upon mouse release
		public void mouseReleased(MouseEvent event) {
			switch (State.getTool()) {
				case Brush:
					break;
				case Ellipse:
					Shape ellipse = new Shape(x, y, width, height, Shape.Type.Ellipse, State.getColor());
					shapes.add(ellipse);
					break;
				case Eraser:
					break;
				case Rectangle:
					Shape rectangle = new Shape(x, y, width, height, Shape.Type.Rectangle, State.getColor());
					shapes.add(rectangle);
					break;
				default:
					break;
			}
		}

		// Here we get the startX/Y for dragging, and if we have the 
		// special image selected, we draw the image immediately
		public void mousePressed(MouseEvent event) {
			startX = event.getX();
			startY = event.getY();
			if (State.getTool() == State.Tool.Chafic) {
				Shape chafic = new Shape(event.getX(), event.getY());
				shapes.add(chafic);
				repaint();
			}
		}
	}

	// This is a public function so that ToolbarPanel can have a button
	// that clears the image immediately and trigger a repaint
	public void clear() {
		// Creates a white shape the size of the window to blank the screen
		Shape clear = new Shape(0, 0, getSize().width, getSize().height, Shape.Type.Clear, Color.white);
		shapes.add(clear);
		
		// Forces a repaint
		repaint();
	}
}
