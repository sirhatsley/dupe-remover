
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class ImageProgressBar extends JPanel implements PropertyChangeListener
{
	private ImageWorker task;
	private JProgressBar pbar;
	private ImageProgressBar it;
	static int max = 100;
	private JFrame frame;

	public ImageProgressBar() 
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
	
	public void constructList(ImageList list)
	{
		it = new ImageProgressBar();
		frame = new JFrame("Progress");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(it);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		int newIndex=0;
		task = new ImageWorker(list);
		task.addPropertyChangeListener(this);
		task.execute();
	}
	
	
	
	/*public static void constructList(ImageList list)
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
						it.updateBar(percent);
						list.constructImage(percent);
					}
				});
				java.lang.Thread.sleep(100);
				
			  } catch (InterruptedException e) {;}
		}
		
		SwingUtilities.invokeLater(new Runnable() 
		{
			public void run()
			{
				frame.setVisible(false);
				
			}
		});
	}	*/

	@Override
	public void propertyChange(PropertyChangeEvent evt)
	{
		if ("progress" == evt.getPropertyName())
		{
			it.updateBar((Integer)evt.getNewValue());
		}
	}

	private class ImageWorker extends SwingWorker<Void, Void>
	{
		private ImageList list;
		public ImageWorker(ImageList list)
		{
			this.list=list;
		}
		
		@Override
		protected Void doInBackground() throws Exception
		{
			for (int i = 0; i <= list.filesLength; i++) 
			{
				final int percent = i;
				list.constructImage(percent);
				this.setProgress(i*100/list.filesLength);
			}
			return null;
		}
		
		@Override
		public void done()
		{
			frame.setVisible(false);
			list.MergeSort();
		}
		
	}
	
}

