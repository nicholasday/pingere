package pingere;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Oval extends JPanel{

	private int x1, x2, y1, y2;
	
	public Oval() {
		addMouseListener(new MouseAdapter());
		addMouseMotionListener(new MouseMotionAdapter());
		setPreferredSize(new Dimension(400, 400));
	}
	public void paint(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, 400, 400);
		g.setColor(Color.black);
		g.drawOval(x1, y1, x2-x1, y2-y1);
	}
	private class MouseMotionAdapter implements MouseMotionListener {
		public void mouseDragged(MouseEvent event) {
			x2 = event.getX();
			y2 = event.getY();
			repaint();
		}
		public void mouseMoved(MouseEvent event) {
			
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
		
		}
		public void mousePressed(MouseEvent event) {
			x1 = event.getX();
			y1 = event.getY();
		}
	}
}
