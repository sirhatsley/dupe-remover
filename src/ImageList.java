import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/*
An object which creates a list of images which can also sort, process, and
delete images.
*/

public class ImageList implements Serializable
{
	public ArrayList<String> preferences;
	private static final long serialVersionUID = -1;
	private File path;
	private HashMap<File,ImageData> map;
	private Object[] array;
	public boolean recurse;
	
	
	
	public ImageList(File path)
	{
		this.path=path;
		map = new HashMap<File,ImageData>();
	}
	
	public File getPath()
	{
		return path;
	}
	
	public void addImage(File image)
	{
		addImage(image,null);
	}
	
	public String getSourceFromFile(String key)
	{
		File thisFile = new File(key.trim());
		
		System.out.println(thisFile.exists());
		System.out.println(map.containsKey(thisFile));
		
		ImageData thisImage = map.get(thisFile);
		
		System.out.println(thisImage);
		return thisImage.source;
	}
	
	private void addImage(File image, String source)
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
				System.out.println("Image added");
				buffer = ImageIO.read(image);
				thisImage = new ImageData(image,buffer);
				thisImage.source=source;
				map.put(image, thisImage);
				//list.add(thisImage);
			}
			else
			{
				System.out.println("Image already added");
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
	
	public void downloadImage(String urlString,String source)
	{
		/*
		Downloads an image and attempts to add it into the folder.
		*/
		try
		{
			System.out.println(source);
			if (urlString.length()==0 && source.length()>0)
			{
				System.out.println("hi");
				urlString=null;
				urlString = SourceFinder.findImageFromSource(source);
				System.out.println(urlString);
			}
			
			URL url = new URL(urlString);
			String fileName = new String (path.toString()+"\\"+
					urlString.substring(urlString.lastIndexOf('/')+1));
			File thisPath=new File(fileName);
			
			int i=0;
			while (thisPath.exists() && i<1000)
			{
				thisPath=new File(fileName.substring(0,fileName.lastIndexOf('.'))
				+i+fileName.substring(fileName.lastIndexOf('.')));
				i++;
			}
			
			System.out.println(thisPath);			
			try
			{
				thisPath.createNewFile();
				FileOutputStream fos = new FileOutputStream(thisPath);
				InputStream in = new BufferedInputStream(url.openStream());
				byte[] buf = new byte[1024];
				int n = 0;
				while (-1!=(n=in.read(buf)))
				{
				   fos.write(buf, 0, n);
				}
				fos.close();
				in.close();

				addImage(thisPath, source);
			}
			catch (IOException ex){thisPath.delete();}
		}
		catch (MalformedURLException ex){System.out.println("Bad URL!");}
	}
	
	public boolean copyOrSource(File image)
	{
		/*
		Returns true if the map already contains the image, otherwise copies the
		image into the folder.
		*/
		
		if(map.containsKey(image))
		{
			return true;
		}
		
		File newFile = new File((path.toString()+"\\")+image.getName());
		
		try
		{
			FileOutputStream fos = new FileOutputStream(newFile);
			FileInputStream fin = new FileInputStream(image);
			
			byte[] buf = new byte[1024];
			int i=0;
			while ((i=fin.read(buf))!=-1)
			{
			   fos.write(buf, 0, i);
			}
			fin.close();
			fos.close();
			
			addImage(newFile);
			image.delete();
		}
		catch (FileNotFoundException fnfe) {newFile.delete();}
		catch (IOException IOE) {newFile.delete();}
		
		System.out.println("Image added: " +newFile.getPath());
		return false;
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
					System.out.println(thisPair.image1.imagePath.getParentFile().getName());
					output.push(thisPair);
				}
				else if (thisPair!=null && deleteAll==true)
				{
					if (preferences==null)
					{
						thisPair.deleteSmallerImage();
					}
					else
					{
						String path1=thisPair.image1.imagePath.getParentFile().getName();
						String path2=thisPair.image2.imagePath.getParentFile().getName();
						int index1 = preferences.indexOf(path1);
						int index2 = preferences.indexOf(path2);
						
						if (index1>index2)
						{
							System.out.println("deleting an image from "+path2);
							thisPair.deleteImage(2);
						}
						else if(index2>index1)
						{
							System.out.println("deleting an image from "+path1);
							thisPair.deleteImage(1);
						}
						else
						{
							thisPair.deleteSmallerImage();
						}
					}

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