package pingere;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;

import javax.swing.*;

public class Oval2 extends JPanel
{
	Image image;
	private int x1, x2, y1, y2;
	private int x, y;
	private int height;
	private int width;
	private int i = 1;
	
	private ArrayList<MyOval> ovals = new ArrayList<MyOval>();
	
	public Oval2() {
		setBackground(Color.white);
		addMouseListener(new MouseAdapter());
		addMouseMotionListener(new MouseMotionAdapter());
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		for (MyOval oval: ovals) {
			g2d.setColor(State.getColor());
			//g.drawOval(oval.getX(), oval.getY(), oval.getWidth(), oval.getHeight());
			g2d.draw(new Ellipse2D.Double(oval.getX(), oval.getY(), oval.getWidth(), oval.getHeight()));
		}

		g2d.setColor(State.getColor());
		g2d.draw(new Ellipse2D.Double(x, y, width, height));
		
		if (i == 1) {
			g2d.draw(new Rectangle2D.Double(x, y, width, height));
			i++;
		} else if (i == 2) {
			i--;
		}

	}
	private class MouseMotionAdapter implements MouseMotionListener {
		public void mouseMoved(MouseEvent event) { }

		public void mouseDragged(MouseEvent event) {
			x2 = event.getX();
			y2 = event.getY();
			
			x = Math.min(x1, x2);
			y = Math.min(y1, y2);

			height = Math.abs(y1 - y2);
			width = Math.abs(x1 - x2);
			repaint();
		}
	}

	private class MouseAdapter implements MouseListener {
		public void mouseClicked(MouseEvent event) { }
		public void mouseEntered(MouseEvent event) { }
		public void mouseExited(MouseEvent event) { }

		public void mouseReleased(MouseEvent event) { 
			MyOval oval = new MyOval(x, y, width, height);
			ovals.add(oval);
		}

		public void mousePressed(MouseEvent event) {
			x1 = event.getX();
			y1 = event.getY();
		}
	}
}
