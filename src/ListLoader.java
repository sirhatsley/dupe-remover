import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*
Loads an ImageList from memory if one is stored at the file location.
 */
public class ListLoader
{
	public static ImageList LoadList(File path)
	{
		ImageList output=null;
		File listAddress = new File("dat");
		File thisList = new File(("dat\\"+path.getName()+".dat"));
		try
			{


			if (!listAddress.exists())
			{
				listAddress.mkdir();
			}


			System.out.println(thisList);
			if (thisList.exists())
			{
				FileInputStream fileInputStream = new FileInputStream(thisList);
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
				output = (ImageList)objectInputStream.readObject();
				output.removeMissingImages();
				fileInputStream.close();
			}
			else
			{
				output=new ImageList(path);
			}
			
		}
		catch(IOException e) {System.out.println("IOExeption occured");}
		catch(ClassNotFoundException cnfe) {System.out.println("ClassNotFoundException occured");}
		return output;
	}
	
	public static void SaveList(ImageList list)
	{
		File path=list.getPath();
		File thisList = new File(("dat\\"+path.getName()+".dat"));
		try
		{
			if (thisList.exists()){thisList.delete();}
			
			thisList.createNewFile();
			
			FileOutputStream fos = new FileOutputStream(thisList);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(list);
			fos.close();
		}
		catch(IOException e) {System.out.println("IOExeption occured");}
		//catch(ClassNotFoundException cnfe) {System.out.println("ClassNotFoundException occured");}
	}
}
