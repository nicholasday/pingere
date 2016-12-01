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

	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	BufferedImage chafic;

	public DrawPanel() {
		try {
			chafic = ImageIO.read(new File("Chafic.png"));
		} catch (IOException ie) {
			ie.printStackTrace();
		}
		setBackground(Color.white);
		addMouseListener(new MouseAdapter());
		addMouseMotionListener(new MouseMotionAdapter());
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		for (Shape shape : shapes) {
			g2d.setColor(shape.getColor());
			if (shape.getType() == Type.Eraser)
				g2d.setColor(Color.white);
			switch (shape.getType()) {
			case Brush:
				g2d.setStroke(new BasicStroke(1));
				g2d.draw(new Line2D.Double(shape.getX2(), shape.getY2(), shape.getX(), shape.getY()));
				break;
			case Eraser:
				g2d.setStroke(shape.getStroke());
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

		g2d.setColor(State.getColor());

		if (!State.isClear()) {
			switch (State.getTool()) {
			case Brush:
				break;
			case Ellipse:
				g2d.setStroke(new BasicStroke(1));
				g2d.draw(new Ellipse2D.Double(x, y, width, height));
				break;
			case Eraser:
				break;
			case Rectangle:
				g2d.setStroke(new BasicStroke(1));
				g2d.draw(new Rectangle2D.Double(x, y, width, height));
				break;
			default:
				break;
			}
		}
	}

	private class MouseMotionAdapter implements MouseMotionListener {
		public void mouseMoved(MouseEvent event) {
		}

		public void mouseDragged(MouseEvent event) {
			endX = event.getX();
			endY = event.getY();

			if (State.getTool() == State.Tool.Brush) {
				Shape line = new Shape(startX, startY, endX, endY, State.getColor());
				shapes.add(line);

				startX = endX;
				startY = endY;
			} else if (State.getTool() == State.Tool.Eraser) {
				Shape erased = new Shape(startX, startY, endX, endY, State.getColor(), State.getStroke());
				shapes.add(erased);

				startX = endX;
				startY = endY;
			} else {
				x = Math.min(startX, endX);
				y = Math.min(startY, endY);

				height = Math.abs(startY - endY);
				width = Math.abs(startX - endX);
			}

			repaint();
		}
	}

	private class MouseAdapter implements MouseListener {
		public void mouseClicked(MouseEvent event) {
		}

		public void mouseEntered(MouseEvent event) {
		}

		public void mouseExited(MouseEvent event) {
		}

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

	public void clear() {
		Shape clear = new Shape(0, 0, getSize().width, getSize().height, Shape.Type.Clear, Color.white);
		shapes.add(clear);
		repaint();
	}
}
