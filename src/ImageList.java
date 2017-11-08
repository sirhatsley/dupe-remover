import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;
import java.util.stream.IntStream;
import javax.imageio.ImageIO;
import javax.swing.JProgressBar;

public class ImageList
{
	private File path;
	private ImageData[] array;
	private ImageJFrame frame;
	
	public ImageList(File path)
	{
		this.constructList(path);
	}
	
	public ImageList(File path, ImageJFrame frame)
	{
		this.frame=frame;
		this.constructList(path);
	}
	
	private void constructList(File path)
	{
		if(path==null) throw new IllegalArgumentException(
			"Null path in ImageList constructor.");
		if(!path.isDirectory()) throw new IllegalArgumentException(
			"ImageList requires a valid directory as parameter.");
		
		this.path=path;
		File[] images = path.listFiles();
		
		System.out.println(images.length);
		array = new ImageData[images.length];
		
		int newArrayIndex=0;
		for (int i=0; i<images.length; i++)
		{
			//Constructs the image array
			BufferedImage buffer=null;
			System.out.println("Processing... "+(i+1)+"/"+images.length);
			try
			{
				buffer = ImageIO.read(images[i]);
				array[newArrayIndex] = new ImageData(images[i],buffer);
				newArrayIndex++;
			}
			catch(IOException e)
			{
				System.out.println(""+images[i]+" not a valid image file.");
			}
			catch(NullPointerException e)
			{
				System.out.println(""+images[i]+" not a valid image file.");
			}
		}
		array=this.MergeSort(0, newArrayIndex);
	}
	
	private ImageData[] MergeSort(int start, int finish)
	{
		int size=finish-start;
		if (size==1)
		{
			return new ImageData[] {array[start]};
		}
		ImageData[] a = MergeSort(start+size/2,finish);
		ImageData[] b = MergeSort(start,start+size/2);
		int aIndex = 0;
		int bIndex = 0;
		ImageData[] output = new ImageData[size];
		for(int i=0; i<size; i++)
		{
			if(aIndex<a.length)
			{
				if(bIndex<b.length)
				{
					if(a[aIndex].getRatio()>b[bIndex].getRatio())
					{
						output[i]=b[bIndex];
						bIndex++;
					}
					else
					{
						output[i]=a[aIndex];
						aIndex++;
					}
				}
				else
				{
					output[i]=a[aIndex];
					aIndex++;
				}
			}
			else
			{
				output[i]=b[bIndex];
				bIndex++;
			}
		}
		return output;
	}
	
	public Deque<DuplicateImages> CountDupes()
	{
		Deque<DuplicateImages> output = new LinkedList<DuplicateImages>();
		for (int i=0; i<array.length-1;i++)
		{
			double thisRatio = array[i].getRatio();
			
			int j=i+1;
			boolean loop=true;
			while(loop)
			{
				if ((Math.abs(array[i].reds-array[j].reds)<2) &&
					(Math.abs(array[i].greens-array[j].greens)<2) &&
					(Math.abs(array[i].blues-array[j].blues)<2))
				{
					DuplicateImages thisPair = array[i].compareTo(array[j]);
					if (thisPair!=null)
					{
						output.push(thisPair);
					}
				}
				
				j++;
				if(j>=array.length-1)
					loop=false;
				if(Math.abs(thisRatio-array[i].getRatio())>.1)
					loop=false;
			}
		}
		return output;
	}
	
}