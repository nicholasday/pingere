package pingere;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Draw extends JPanel
{
	Image image;
	Graphics2D graphics2D;
	int currentX, currentY, oldX, oldY;
	
	public Draw(){
		setDoubleBuffered(false);

		addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				oldX = e.getX();
				oldY = e.getY();
			}
		});

		addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseDragged(MouseEvent e){
				currentX = e.getX();
				currentY = e.getY();
				if(graphics2D != null)
				graphics2D.setColor(State.getColor());
				if (State.getColor() == Color.white) 
					graphics2D.setStroke(new BasicStroke(10));
				else
					graphics2D.setStroke(new BasicStroke(1));
				graphics2D.drawLine(oldX, oldY, currentX, currentY);
				repaint();
				oldX = currentX;
				oldY = currentY;
			}
		});
	}

	public void paintComponent(Graphics g) {
		if (image == null) {
			image = createImage(getSize().width, getSize().height);
			graphics2D = (Graphics2D)image.getGraphics();
			graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			clear();
		}

		g.drawImage(image, 0, 0, null);
	}

	public void save() {
		 try {
             
	            Robot robot = new Robot();
	            // Capture the screen shot of the area of the screen defined by the rectangle
	            BufferedImage bi=robot.createScreenCapture(new Rectangle(getWidth(), getHeight()));
	            ImageIO.write(bi, "png", new File("/home/mohammad/image/testImage.png"));
	             
	        } catch (AWTException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    
	}
	public void clear() {
		graphics2D.setPaint(Color.white);
		graphics2D.fillRect(0, 0, getSize().width, getSize().height);
		graphics2D.setPaint(Color.black);
		repaint();
	}
	
	public void stroke(BasicStroke stroke) {
		graphics2D.setStroke(stroke);
	}

}
