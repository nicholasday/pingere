package pingere;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Oval extends JPanel{
	Image image;
	Graphics2D graphics2D;
	private int x1, x2, y1, y2, oldx1, oldy1, oldx2, oldy2;
	Draw draw = new Draw();
	
	public Oval() {
		setDoubleBuffered(false);
		addMouseListener(new MouseAdapter());
		addMouseMotionListener(new MouseMotionAdapter());
	}
	public void paint(Graphics g) {
		if(image == null){
			image = createImage(getSize().width, getSize().height);
			graphics2D = (Graphics2D)image.getGraphics();
			graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			clear();
		}
		g.drawImage(image, 0, 0, null);
		
		
	}
	private class MouseMotionAdapter implements MouseMotionListener {
		public void mouseDragged(MouseEvent event) {
			x2 = event.getX();
			y2 = event.getY();
			graphics2D.setColor(Color.white);
			//graphics2D.fillRect(0, 0, 400, 400);
			graphics2D.fillOval(x1, y1, oldx2-x1, oldy2-y1);
			graphics2D.setColor(Color.black);
			if (x2 > x1) {
				if (y2 > y1)
					graphics2D.drawOval(x1, y1, x2-x1, y2-y1);
				if (y1 > y2)
					graphics2D.drawOval(x1, y2, x2-x1, y1-y2);
			}
			if (x1 > x2) {
				if (y2 > y1)
					graphics2D.drawOval(x2, y1, x1-x2, y2-y1);
				if (y1 > y2)
					graphics2D.drawOval(x2, y2, x1-x2, y1-y2);
			}
			repaint();
			oldx2 = x2;
			oldy2 = y2;
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
	public void clear(){
		graphics2D.setPaint(Color.white);
		graphics2D.fillRect(0, 0, getSize().width, getSize().height);
		graphics2D.setPaint(Color.black);
		repaint();
	}
}
