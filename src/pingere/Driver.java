package pingere;
import javax.swing.JFrame;

public class Driver
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		JFrame frame = new JFrame ("Paint");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new Oval());//change the Oval to Draw to make it draw a line
		frame.pack();
		frame.setVisible(true);

	}

	
}
