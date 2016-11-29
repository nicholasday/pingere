package pingere;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import javax.swing.*;

public class ShapeDraw extends JPanel
{
	private int startX;
	private int startY;
	private int endX;
	private int endY;
	private int x, y;
	private int height;
	private int width;
	
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	
	public ShapeDraw() {
		setBackground(Color.white);
		addMouseListener(new MouseAdapter());
		addMouseMotionListener(new MouseMotionAdapter());
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		
		for (Shape shape: shapes) {
			g2d.setColor(shape.getColor());
			
			switch(shape.getType()) {
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
				default:
					break;
			}
		}

		g2d.setColor(State.getColor());
		
		switch(State.getTool()) {
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

	private class MouseMotionAdapter implements MouseMotionListener {
		public void mouseMoved(MouseEvent event) { }

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
		public void mouseClicked(MouseEvent event) { }
		public void mouseEntered(MouseEvent event) { }
		public void mouseExited(MouseEvent event) { }

		public void mouseReleased(MouseEvent event) { 
			switch(State.getTool()) {
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
		}
	}
}
