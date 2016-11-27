package pingere;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Draw extends JPanel{
	private int x=-10, y=-10;

	public Draw() {
		addMouseMotionListener(new MouseMotionAdapter());
		setPreferredSize(new Dimension(400, 400));
	}	
	
	public void paint(Graphics g) {
		g.fillOval(y, x, 4, 4);
	}
	private class MouseMotionAdapter implements MouseMotionListener{
		public void mouseDragged(MouseEvent event) {
			x = event.getY();
			y = event.getX();
			repaint();
		}
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}
