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
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.HttpParams;

/*
	Finds the source of an image using Saucenao
*/

public class SourceFinder
{
	public static void main(String[] args) throws Exception
	{
		getImageFromSite("https://twitter.com/hayashittedare/status/935497177991864320",WebSources.TWITTER);
		getImageFromSite("https://www.pixiv.net/member_illust.php?mode=medium&illust_id=46913688",WebSources.PIXIV);
		getImageFromSite("http://kirigiri-park.tumblr.com/post/167174990173/ultimate-astronaut-kyouko-kirigiri-anthology",WebSources.TUMBLR);
		//String apiKey = "35dbf5ffb60a8bbe45bf7ec9819fd075a4b17027";
		URL url = new URL("http://saucenao.com/search.php?output_type=2&numres=1&minsim=80&api_key=35dbf5ffb60a8bbe45bf7ec9819fd075a4b17027");
		File image = new File("C:\\Users\\Tim\\Google Drive\\Neggiri\\0052f514afce3defb119120547e3c339.jpg");
		//System.out.println(findSource(new File("C:\\Users\\Tim\\Google Drive\\Neggiri\\0052f514afce3defb119120547e3c339.jpg")));
	}
	
	public static void addSource(ImageData image)
	{
		/*
		Adds a source to an image that already exists.
		*/
	}
	
	public static ImageData downloadImageFromUrl(String url,String source)
	{
		//Takes in an image URL and Source (if one is supplied) and downloads
		//the image while returning it to
		ImageData output=null;
		
		if (source==null)
		{
			if (url.contains("https://twitter.com/"))
			{

			}
			else if (url.contains("https://twitter.com/"))
			{

			}
		}
		
		return output;
	}
	
	public static String findSource(File image)
	{
		/*
		Finds the source for an image with SauceNao
		*/
		HttpClient client=new DefaultHttpClient();
		client.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
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
	
	public static String getImageFromSite(String url,WebSources site)
	{

		String imageUrl = null;
		HttpClient client=HttpClients.custom()
        .setDefaultRequestConfig(RequestConfig.custom()
            .setCookieSpec(CookieSpecs.STANDARD).build())
        .build();
		HttpGet get = new HttpGet(url);
		try
		{
			HttpResponse response = client.execute(get);
			HttpEntity resEntity = response.getEntity();
			String html="";

			if (resEntity != null) {
				html=EntityUtils.toString(resEntity);
				resEntity.consumeContent();
			}
			if (html.contains(site.prefix))
			{
				imageUrl=html;
				if(site.beforePrefix!=null)
				{
					imageUrl=imageUrl.substring(imageUrl.indexOf(site.beforePrefix));
				}
				imageUrl=imageUrl.substring(imageUrl.indexOf(site.prefix));
				imageUrl=imageUrl.substring(0,imageUrl.indexOf('"'));
				if (site==WebSources.PIXIV)
				{
					imageUrl=imageUrl.replace("c/600x600/img-master", "img-original");
					imageUrl=imageUrl.replace("_master1200.jpg", ".png");
				}
				System.out.println(imageUrl);
			}
		}
		catch(IOException e){System.out.println("Error");}
		return imageUrl;
	}
	
	private enum WebSources{
		TWITTER("https://pbs.twimg.com/media/","og:image"),
		PIXIV("https://i.pximg.net/c/",null),
		TUMBLR("http://78.media.tumblr.com/","og:image"),
		DEVIANTART("https://orig00.deviantart.net/",null);
		
		public final String prefix;
		public final String beforePrefix;
		WebSources(String prefix,String beforePrefix)
		{
			this.prefix=prefix;
			this.beforePrefix=beforePrefix;
		}
	}
}
