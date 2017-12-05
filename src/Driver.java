
import java.io.File;
import java.util.Deque;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


public class Driver
{
	private static FileChooser choose;
	private static boolean deleteAll;
	private static ImageList list;
	private static File path;
	
	public static void main(String[] args)
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e){}
		choose = new FileChooser(null);
		choose.setTitle("Please select a folder...");
		choose.setLocationRelativeTo(null);
		choose.setVisible(true);
		//ImageProgressBar.main(null);
	}
	
	public static void loadImages(File file, boolean deleteEverything, boolean sourceAll,boolean recurse)
	{
		deleteAll=deleteEverything;
		choose.setVisible(false);
		path=file;
		list = ListLoader.LoadList(file);
		new Thread(new Runnable() {
			public void run()
			{
				new ImageProgressBar().constructList(list,file,recurse);
			}
		}).start();
	}
	
	public static void deleteDupes()
	{
		Deque<DuplicateImages> dupes;
		dupes=list.CountDupes(deleteAll);
		ListLoader.SaveList(list);
		if (dupes.size()==0)
		{
			if (deleteAll==false)
			{
				JOptionPane.showMessageDialog(null, "No duplicates found.");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Complete.");
			}
			System.exit(0);
		}

		ImageJFrame listFrame = new ImageJFrame(dupes);
		listFrame.setResizable(false);
		listFrame.setLocationRelativeTo(null);
		listFrame.setVisible(true);
	}
}

