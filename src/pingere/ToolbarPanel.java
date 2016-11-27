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
		brush = new JButton("", createImageIcon("/paint_brush.png", "brush"));
		eraser = new JButton("", createImageIcon("/eraser.png", "eraser"));
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
	}

	private static ImageIcon createImageIcon(String url, String description)
	{
		java.net.URL imgURL = ToolbarPanel.class.getResource(url);
		return new ImageIcon(new ImageIcon(imgURL, description).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
	}
}