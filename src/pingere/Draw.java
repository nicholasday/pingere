package pingere;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Object;
import java.awt.geom.Path2D;

public class Draw extends JPanel{
	private int x1=-10, x2=-10, x3=-10, y1=-10, y2=-10, y3=-10, x4 = -10, y4 = -10;
	Path2D.Double path = new Path2D.Double();

	public Draw() {
		addMouseMotionListener(new MouseMotionAdapter());
		setPreferredSize(new Dimension(400, 400));
	}	
	
	public void paint(Graphics g) {
		//g.fillOval(y, x, 3, 3);
		Graphics2D g2 = (Graphics2D)g;
		path.moveTo(x1, y1);
		path.curveTo(x2, y2, x3, y3, x4, y4);
		g2.draw(path);
	}
	private class MouseMotionAdapter implements MouseMotionListener{
		public void mouseDragged(MouseEvent event) {
			int i = 1;
			if ((i-1)%4 == 0) {
				x1 = event.getX();
				y1 = event.getY();
			}
			if ((i-2)%4 == 0) {
				x2 = event.getX();
				y2 = event.getY();
			}
			if ((i-3)%4 == 0) {
				x3 = event.getX();
				y3 = event.getY();
			}
			if (i%4 == 0) {
				x4 = event.getX();
				y4 = event.getY();
				repaint();
			}
			i++;

		}
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}
