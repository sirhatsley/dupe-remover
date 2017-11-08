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
}
