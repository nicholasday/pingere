package pingere;

import java.awt.BasicStroke;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ToolbarPanel extends JPanel {

	JButton brush, eraser, clear, rectangle, ellipse, save, chooseColor, chafic;
	JLabel statuslabel;

	public ToolbarPanel() {

		chooseColor = new JButton("Choose Color");
		chafic = new JButton("Chafic");
		brush = new JButton("", createImageIcon("paint_brush.png", "brush"));
		clear = new JButton("Clear");
		eraser = new JButton("", createImageIcon("eraser.png", "eraser"));
		save = new JButton("Save");
		rectangle = new JButton("", createImageIcon("rectangle.png", "rectangle"));
		ellipse = new JButton("", createImageIcon("ellipse.png", "ellipse"));
		statuslabel = new JLabel("");

		brush.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent selected) {
				State.setTool(State.Tool.Brush);
				statuslabel.setText("Brush selected");
			}
		});
		eraser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent selected) {
				State.setStroke(new BasicStroke(20));
				State.setTool(State.Tool.Eraser);
				statuslabel.setText("Eraser selected");
			}
		});
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent selected) {
				State.Tool prevTool = State.getTool();
				State.setTool(State.Tool.Clear);
				State.clear();
				statuslabel.setText("Image cleared");
				State.setTool(prevTool);
			}
		});
		rectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent selected) {
				State.setTool(State.Tool.Rectangle);
				statuslabel.setText("Rectangle selected");
			}
		});
		ellipse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent selected) {
				State.setTool(State.Tool.Ellipse);
				statuslabel.setText("Ellipse selected");
			}
		});
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent selected) {
				State.saveImage();
				statuslabel.setText("Drawing Saved");
			}
		});
		chooseColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				State.colorChooser();
			}
		});
		chafic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				State.setTool(State.Tool.Chafic);
			}
		});
		add(clear);
		add(brush);
		add(eraser);
		add(rectangle);
		add(ellipse);
		// add(save);
		add(chooseColor);
		add(chafic);
		add(statuslabel);
	}

	private ImageIcon createImageIcon(String url, String description) {
		java.net.URL imgURL = getClass().getResource(url);
		return new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
	}
}