import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.*;
import java.net.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Spliterator;
import java.util.function.Consumer;

/*
	Finds the source of an image using Saucenao
*/

public class SourceFinder
{
	public static void main(String[] args) throws Exception
	{
		//String apiKey = "35dbf5ffb60a8bbe45bf7ec9819fd075a4b17027";
		URL url = new URL("http://saucenao.com/search.php?output_type=2&numres=1&minsim=80&api_key=35dbf5ffb60a8bbe45bf7ec9819fd075a4b17027");
		File image = new File("C:\\Users\\Tim\\Google Drive\\Neggiri\\0052f514afce3defb119120547e3c339.jpg");
		System.out.println(findSource(new File("C:\\Users\\Tim\\Google Drive\\Neggiri\\0052f514afce3defb119120547e3c339.jpg")));
	}
	
	public static void addSource(ImageData image)
	{
		
	}
	
	public static String findSource(File image)
	{
		HttpClient client=new DefaultHttpClient();
		client.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		//HttpPost post = new HttpPost("http://saucenao.com/search.php?output_type=2&numres=1&minsim=80&api_key=35dbf5ffb60a8bbe45bf7ec9819fd075a4b17027");
		HttpPost post = new HttpPost("http://saucenao.com/search.php?output_type=2&numres=1&minsim=80");
		MultipartEntity mpEntity = new MultipartEntity();
		ContentBody cbFile = new FileBody(image, "image/"+image.getName().substring(image.getName().lastIndexOf(".")));
		mpEntity.addPart("file", cbFile);
		post.setEntity(mpEntity);
		//System.out.println("executing request " + post.getRequestLine());
		try
		{
		HttpResponse response = client.execute(post);
		HttpEntity resEntity = response.getEntity();
		String json="";
		String source = "";
		//System.out.println(response.getStatusLine());
		if (resEntity != null) {
			json=EntityUtils.toString(resEntity);
			resEntity.consumeContent();
		}
		ObjectMapper map = new ObjectMapper();
		return map.readTree(json).findValue("ext_urls").get(0).toString();
		}
		catch(IOException e){System.out.println("Error");}
		return null;
	}
}
