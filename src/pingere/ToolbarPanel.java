package pingere;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ToolbarPanel extends JPanel
{
	
	JButton brush, eraser, clear, rectangle, ellipse;
	JLabel statuslabel;
	public ToolbarPanel() 
	{
		brush = new JButton("", createImageIcon("paint_brush.png", "brush"));
		clear = new JButton("Clear");
		eraser = new JButton("", createImageIcon("eraser.png", "eraser"));
		rectangle = new JButton("", createImageIcon("rectangle.png", "rectangle"));
		ellipse = new JButton("", createImageIcon("ellipse.png", "ellipse"));
		statuslabel = new JLabel("");
		
		brush.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent selected)
			{
				State.setColor(Color.black); 
				State.setTool(State.Tool.Brush);
				statuslabel.setText("Brush selected");
			}
		});
		eraser.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent selected)
			{
				State.setColor(Color.white);
				State.setTool(State.Tool.Eraser);
				statuslabel.setText("Eraser selected");
			}
		});
		clear.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent selected)
			{
				State.setDrawState(State.DrawState.Clear);
			}
		});
		rectangle.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent selected)
			{
				State.setTool(State.Tool.Rectangle);
				statuslabel.setText("Rectangle selected");
			}
		});
		ellipse.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent selected)
			{
				State.setTool(State.Tool.Ellipse);
				statuslabel.setText("Ellipse selected");
			}
		});
		add(clear);
		add(brush);
		add(eraser);
		add(rectangle);
		add(ellipse);
		add(statuslabel);
	}

	private ImageIcon createImageIcon(String url, String description)
	{
		java.net.URL imgURL = getClass().getResource(url);
		return new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
	}
}