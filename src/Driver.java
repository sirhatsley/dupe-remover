
import java.io.File;
import java.util.Deque;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;


public class Driver
{
	private static FileChooser choose;

	
	public static void main(String[] args)
	{
		try
		{
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e){}
		choose = new FileChooser();
		choose.setTitle("Please select a file...");
		choose.setVisible(true);
	}
	
	public static void loadImages(File file, boolean deleteAll)
	{
		choose.setVisible(false);
		ImageList list;
		list = new ImageList(file,deleteAll,false);
		Deque<DuplicateImages> dupes;
		dupes=list.CountDupes();
		
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
		listFrame.setVisible(true);
	}
}
