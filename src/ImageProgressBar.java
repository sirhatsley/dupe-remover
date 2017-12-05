
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.Deque;
import java.util.LinkedList;
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
	private Deque<File> imageStack;

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
	
	public void constructList(ImageList list, File path,boolean recurse)
	{
		imageStack = new LinkedList<File>();
		this.generateList(path, recurse);
		it = new ImageProgressBar();
		frame = new JFrame("Progress");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(it);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		int newIndex=0;
		//System.out.println(imageStack.size());
		task = new ImageWorker(list);
		task.addPropertyChangeListener(this);
		task.execute();
	}
	
	private void generateList(File path, boolean recurse)
	{
		//Recursively generates the list of files that will be added to ImageList
		File[] fileList=path.listFiles();
		for(int i=0; i<fileList.length; i++)
		{
			if (fileList[i].isDirectory()&&recurse)
			{
				generateList(fileList[i],recurse);
			}
			if (fileList[i].isFile())
			{
				imageStack.add(fileList[i]);
			}
		}
	}
	
	
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
			int initialSize = imageStack.size();
			for(int i=0;i<initialSize;i++) 
			{
				list.addImage(imageStack.pop());
				int thisProgress = initialSize-imageStack.size();
				System.out.println(imageStack.size());
				this.setProgress(i*100/initialSize);
			}
			return null;
		}
		
		@Override
		public void done()
		{
			frame.setVisible(false);
			Driver.deleteDupes();
		}
		
	}
	
}

