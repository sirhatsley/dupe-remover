
import java.io.File;
import java.util.Deque;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Driver
{
	private static FileChooser choose;

	
	public static void main(String[] args)
	{
		/*ImageList list;
		list = new ImageList(new File("C:\\Users\\Tim\\Desktop\\Shaftastic"));
		//System.out.println(list.CountDupes());
		//list = new ImageList(new File("C:\\Users\\Tim\\Google Drive\\Neggiri"));
		Deque<DuplicateImages> dupes = list.CountDupes();
		
		JFrame f=new JFrame();
		JPanel panel = new JPanel();
		JButton nextButton=new JButton();

		while (dupes.size()>0)
		{
			
			DuplicateImages thisDupe=dupes.pop();
			int maxHeight = (int)Math.max((400.0/thisDupe.image1.width)*thisDupe.image1.height,
							(200.0/thisDupe.image2.width)*thisDupe.image2.height)+100;  
			ImageGUI one=new ImageGUI(thisDupe);
			f.add(one);
			f.setSize(800,maxHeight);
			f.setVisible(true);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

		//ImageJFrame theFrame = new ImageJFrame(dupes);
				*/
		choose = new FileChooser();
		choose.setVisible(true);
	}
	
	public static void loadImages(File file)
	{
		choose.setVisible(false);
		ImageList list;
		list = new ImageList(file);
		
		new ImageJFrame(list).setVisible(true);
	}
}
