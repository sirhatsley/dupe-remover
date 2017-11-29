
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;

public class ImageData implements Serializable
{
	private static final long serialVersionUID = -1;
	File imagePath;
	int height;
	int width;
	int reds;
	int[][] redsArray;
	int[][] greensArray;
	int[][] bluesArray;
	final int SEGMENTS = 4;
	int greens;
	int blues;
	
	String source;
	
	public ImageData(File imagePath, BufferedImage buffer)
	{

		this.imagePath=imagePath;
		this.height=buffer.getHeight();
		this.width=buffer.getWidth();

		reds=0;
		redsArray= new int[][] {new int[SEGMENTS],new int[SEGMENTS],new int[SEGMENTS],new int[SEGMENTS]};
		greens=0;
		greensArray= new int[][] {new int[SEGMENTS],new int[SEGMENTS],new int[SEGMENTS],new int[SEGMENTS]};
		blues=0;
		bluesArray= new int[][] {new int[SEGMENTS],new int[SEGMENTS],new int[SEGMENTS],new int[SEGMENTS]};

		int segmentHeight=Math.max(1,height/SEGMENTS);
		int segmentWidth=Math.max(1,width/SEGMENTS);

		for (int y=0; y<SEGMENTS; y++)
		{
			for (int x=0; x<SEGMENTS; x++)
			{
				for(int subY=y*segmentHeight; subY<(y+1)*segmentHeight; subY++)
				{
					int theseReds=0;
					int theseGreens=0;
					int theseBlues=0;
					for(int subX=x*segmentWidth; subX<(x+1)*segmentWidth; subX++)
					{
						Color thisColor = new Color(buffer.getRGB(
								Math.min(subX, width-1),
								Math.min(subY, height-1)));

						theseReds = theseReds+thisColor.getRed();
						theseGreens = theseGreens+thisColor.getGreen();
						theseBlues = theseBlues+thisColor.getBlue();
					}
					redsArray[x][y] = redsArray[x][y] + theseReds/segmentWidth;
					greensArray[x][y] = greensArray[x][y] + theseGreens/segmentWidth;
					bluesArray[x][y] = bluesArray[x][y] + theseBlues/segmentWidth;
				}
				redsArray[x][y] = redsArray[x][y]/segmentHeight;
				reds=reds+redsArray[x][y];
				greensArray[x][y] = greensArray[x][y]/segmentHeight;
				greens=greens+greensArray[x][y];
				bluesArray[x][y] = bluesArray[x][y]/segmentHeight;
				blues=blues+bluesArray[x][y];
			}
		}
		reds=reds/SEGMENTS/SEGMENTS;
		greens=greens/SEGMENTS/SEGMENTS;
		blues=blues/SEGMENTS/SEGMENTS;
	}

	public double getRatio()
	{
		return (double)height/(double)width;
	}

	public DuplicateImages compareTo(ImageData other)
	{
		DuplicateImages output=null;
		
		int count=0;
		for(int y=0; y<SEGMENTS; y++)
		{
			for(int x=0; x<SEGMENTS; x++)
			{
				count = count +
					Math.abs(this.redsArray[x][y]-other.redsArray[x][y]) +
					Math.abs(this.greensArray[x][y]-other.greensArray[x][y]) +
					Math.abs(this.bluesArray[x][y]-other.bluesArray[x][y]);
			}
		}
		if(count<100)
		{
			System.out.println("" +(100-(double)count/1000.0*100.0)+"%");
			output=new DuplicateImages(
				this,
				other,
				("" +(100-(double)count/1000.0*100.0)+"%"));
		}
		return output;
	}
}