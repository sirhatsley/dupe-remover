import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Predicate;
import javax.imageio.ImageIO;

/*
An object which creates a list of images which can also sort, process, and
delete images.
*/

public class ImageList implements Serializable
{
	private static final long serialVersionUID = -1;
	private File path;
	//private LinkedList<ImageData> list;
	private HashMap<File,ImageData> map;
	private Object[] array;
	private boolean sourceAll;
	
	
	public ImageList()
	{
		sourceAll=false;
		map = new HashMap<File,ImageData>();
		//list = new LinkedList<ImageData>();
	}
	
	public ImageList(File path, boolean sourceAll)
	{
		this.sourceAll=sourceAll;
		map = new HashMap<File,ImageData>();
		//list = new LinkedList<ImageData>();
	}
	
	public synchronized void addImage(File image)
	{
		if (image==null)
		{
			throw new IllegalArgumentException("Null image in addImage.");
		}
		BufferedImage buffer=null;
		try
		{
			//Reads an image and adds the ImageData to an array.
			
			ImageData thisImage;
			if(!map.containsKey(image))
			{
				buffer = ImageIO.read(image);
				thisImage = new ImageData(image,buffer);
				map.put(image, thisImage);
				//list.add(thisImage);
			}
			else
			{
				System.out.println("Image already added");
			}
			if(sourceAll==true)
			{
				System.out.println(SourceFinder.findSource(image));
			}
		}
		catch(IOException e)
		{
			System.out.println(""+image+" not a valid image file.");
		}
		catch(NullPointerException e)
		{
			System.out.println(""+image+" not a valid image file.");
		}
	}
	
	public int getSize()
	{
		//Returns the size of the image list.
		return map.size();
	}
	
	private Object[] MergeSort(int start, int finish)
	{
		//Sorts all image by aspect ratio, in order to minimize the number
		//of images that need to be compared.
		int size=finish-start;
		if (size<=1)
		{
			return new Object[] {array[start]};
		}
		Object[] a = MergeSort(start+size/2,finish);
		Object[] b = MergeSort(start,start+size/2);
		int aIndex = 0;
		int bIndex = 0;
		Object[] output = new ImageData[size];
		for(int i=0; i<size; i++)
		{
			if(aIndex<a.length)
			{
				if(bIndex<b.length)
				{
					if(((ImageData)a[aIndex]).getRatio()>((ImageData)b[bIndex]).getRatio())
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
	
	public Deque<DuplicateImages> CountDupes(boolean deleteAll)
	{
		//Counts the number of duplicate images in the map and returns them
		array=new ArrayList<ImageData>(map.values()).toArray();
		array=MergeSort(0, array.length);
		Deque<DuplicateImages> output = new LinkedList<DuplicateImages>();
		for (int i=0; i<array.length-1;i++)
		{
			double thisRatio = ((ImageData)array[i]).getRatio();
			int j=i+1;
			boolean loop=true;
			while(loop)
			{
				//Compares images within a certain threshold based on aspect ratio.
				DuplicateImages thisPair = ((ImageData)array[i]).compareTo((ImageData)array[j]);
				
				if (thisPair!=null && deleteAll==false)
				{
					output.push(thisPair);
				}
				else if (thisPair!=null && deleteAll==true)
				{
					thisPair.deleteSmallerImage();
				}
				
				if(Math.abs(thisRatio-((ImageData)array[j]).getRatio())>.1)
					loop=false;
				
				j++;
				if(j>=array.length-1)
					loop=false;

			}
		}
		array=null;
		return output;
	}
	
	public void removeMissingImages()
	{
		/*
		Removes missing images from the list.
		*/
		
		Iterator<File> iter = map.keySet().iterator();
		while(iter.hasNext())
		{
			File thisFile = iter.next();
			if(!thisFile.exists()) {iter.remove();}
		}
	}
}