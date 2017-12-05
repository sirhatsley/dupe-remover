/*
An object which contains 2 ImageData files and their similarity to eachother
*/

public class DuplicateImages
{
	public ImageData image1;
	public ImageData image2;
	public String similarity;
	
	public DuplicateImages(ImageData image1, ImageData image2, String similarity)
	{
		this.image1=image1;
		this.image2=image2;
		this.similarity=similarity;
	}
	
	public void deleteSmallerImage()
	{
		//Deletes whichever image is smaller
		if (image1.width<image2.width)
		{
			deleteImage(1);
		}
		
		if (image2.width<=image1.width)
		{
			deleteImage(2);
		}
	}
	
	public void deleteImage(int i)
	{
		String source;
		source=null;
		if (image1.source!=null&&image1.source!="") {source=image1.source;}
		if (image2.source!=null&&image2.source!="") {source=image2.source;}
		if (i==1) {image1.imagePath.delete();image2.source=source;}
		if (i==2) {image2.imagePath.delete();image1.source=source;}
	}
}
