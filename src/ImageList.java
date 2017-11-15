import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;
import javax.imageio.ImageIO;

/*
An object which creates a list of images which can also sort, process, and
delete images.
*/

public class ImageList
{
	private File path;
	private ImageData[] array;
	private boolean deleteAll;
	private boolean sourceAll;
	
	
	public ImageList(File path)
	{
		deleteAll=false;
		sourceAll=false;
		this.constructList(path);
	}
	
	public ImageList(File path, boolean deleteAll, boolean sourceAll)
	{
		this.deleteAll=deleteAll;
		this.sourceAll=sourceAll;
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
				//Reads an image and adds the ImageData to an array.
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
			
			if(sourceAll==true)
			{
				//If the user wants to source their images, adds the source to
				//the .EXIF fields.
			}
		}
		//Sorts the array.
		array=this.MergeSort(0, newArrayIndex);
	}
	
	public int getSize()
	{
		//Returns the size of the image.
		return array.length;
	}
	
	private ImageData[] MergeSort(int start, int finish)
	{
		//Sorts all image by aspect ratio, in order to minimize the number
		//of images that need to be compared.
		int size=finish-start;
		if (size<=1)
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
				//Compares images within a certain threshold based on aspect ratio.
				DuplicateImages thisPair = array[i].compareTo(array[j]);
				
				if (thisPair!=null && deleteAll==false)
				{
					output.push(thisPair);
				}
				else if (thisPair!=null && deleteAll==true)
				{
					thisPair.deleteSmallerImage();
				}
				
				if(Math.abs(thisRatio-array[j].getRatio())>.1)
					loop=false;
				
				j++;
				if(j>=array.length-1)
					loop=false;

			}
		}
		return output;
	}
	
}