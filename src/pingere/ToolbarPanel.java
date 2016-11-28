package pingere;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ToolbarPanel extends JPanel
{
	
	JButton brush, eraser, clear;
	JLabel statuslabel;
	public ToolbarPanel() 
	{
		brush = new JButton("", createImageIcon("paint_brush.png", "brush"));
		clear = new JButton("Clear");
		eraser = new JButton("", createImageIcon("eraser.png", "eraser"));
		statuslabel = new JLabel("");
		
		brush.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent selected)
			{
				statuslabel.setText("Brush selected");
			}
		});
		eraser.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent selected)
			{
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
		add(brush);
		add(clear);
		add(eraser);
		add(statuslabel);
	}

	private ImageIcon createImageIcon(String url, String description)
	{
		java.net.URL imgURL = getClass().getResource(url);
		return new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
	}
}