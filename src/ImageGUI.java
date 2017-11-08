import java.awt.*;  
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;  
import javax.swing.JPanel;
  
public class ImageGUI extends JPanel
{  
	private DuplicateImages data;
	Image i;
	Image i2;
	int x;
	int xBorder;
	int yBorder;
	
	public ImageGUI(DuplicateImages data)
	{
		this.data=data;
        // 390/300 picture
		try
		{
			i=ImageIO.read(data.image1.imagePath);
			i2=ImageIO.read(data.image2.imagePath);
			int xScale=(int)(300.0/data.image1.height*data.image1.width);
			int yScale=(int)(390.0/data.image1.width*data.image1.height);
			if (yScale>300)
			{
				i=i.getScaledInstance(-1, 300, Image.SCALE_DEFAULT);
				i2=i2.getScaledInstance(-1, 300, Image.SCALE_DEFAULT);
				yBorder=12;
				xBorder=(400-xScale)/2;
			}
			else
			{
				i=i.getScaledInstance(390, -1, Image.SCALE_DEFAULT);
				i2=i2.getScaledInstance(390, -1, Image.SCALE_DEFAULT);
				xBorder=5;
				yBorder=(300-yScale)/2+12;
			}
		}
		catch(IOException e){}
	}
	
	@Override
    protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		//int height = (int)(400.0/data.width*data.height);
        //Toolkit t=Toolkit.getDefaultToolkit();
        g.drawImage(i, xBorder,yBorder,this);
		g.drawImage(i2, 400+xBorder,yBorder,this);
		
		String file1=data.image1.imagePath.toString();
		String file2=data.image2.imagePath.toString();
		if(file1.length()>60)
		{
			file1=file1.substring(0, 60)+"...";
		}
		if(file2.length()>60)
		{
			file2=file2.substring(0, 60)+"...";
		}
		g.drawString(file1, 0, 12);
		g.drawString(file2, 400, 12);
    }
}  