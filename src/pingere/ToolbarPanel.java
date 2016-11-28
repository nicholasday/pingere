package pingere;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ToolbarPanel extends JPanel
{
	
	JButton brush, eraser;
	JLabel statuslabel;
	public ToolbarPanel() 
	{
		brush = new JButton("", createImageIcon("java.png", "brush"));
		eraser = new JButton("", createImageIcon("java.png", "eraser"));
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
		add(brush);
		add(eraser);
		add(statuslabel);
		setPreferredSize(new Dimension(100, 50));
		setMaximumSize(new Dimension(100, 50));
		setMinimumSize(new Dimension(100, 50));
	}

	private ImageIcon createImageIcon(String url, String description)
	{
		java.net.URL imgURL = getClass().getResource(url);
		return new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
	}
}