
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class ImageProgressBar extends JPanel
{

	JProgressBar pbar;

	static int max = 100;

	public ImageProgressBar(int max) 
	{
		this.max=max;
		pbar = new JProgressBar();
		pbar.setMinimum(0);
		pbar.setMaximum(max);
		add(pbar);
	}

	public void updateBar(int newValue) 
	{
		pbar.setValue(newValue);
	}

	public static void main(String args[])
	{
		ImageList list = new ImageList(new File("C:\\Users\\Tim\\Desktop\\Shaftastic"));
		ImageProgressBar it = new ImageProgressBar(list.filesLength);
		
		JFrame frame = new JFrame("Progress Bar Example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(it);
		frame.pack();
		frame.setVisible(true);
		
		// run a loop to demonstrate raising
		for (int i = 0; i <= list.filesLength; i++) 
		{
			final int percent = i;
			try 
			{
				SwingUtilities.invokeLater(new Runnable() 
				{
					public void run()
					{
						list.constructImage(percent);
						it.updateBar(percent);
					}
				});
				java.lang.Thread.sleep(100);
			  } catch (InterruptedException e) {;}
		}
		SwingUtilities.invokeLater(new Runnable() 
		{
			public void run()
			{
				list.MergeSort();
			}
		});
	}
	
	public static void constructList(ImageList list)
	{
		//ImageList list = new ImageList(new File("C:\\Users\\Tim\\Desktop\\SugoiBox"));
		final ImageProgressBar it = new ImageProgressBar(list.filesLength);
		
		JFrame frame = new JFrame("Progress");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(it);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		int newIndex=0;
		
		// run a loop to demonstrate raising
		for (int i = 0; i <= list.filesLength; i++) 
		{
			final int percent = i;
			try 
			{
				SwingUtilities.invokeLater(new Runnable() 
				{
					public void run()
					{
						list.constructImage(percent);
						it.updateBar(percent);
					}
				});
				java.lang.Thread.sleep(100);
			  } catch (InterruptedException e) {;}
		}
	}	

}

