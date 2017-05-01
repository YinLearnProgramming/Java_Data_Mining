import java.io.IOException;
import java.io.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.*;

public class ZillowJsoup
{
	public static void main(String[] args) throws IOException
	{
		int pageNumber = 1;
		int maxPageNumber = 20;
		String pageString = " ";
		int currPageNumber = 1;
		String pageNumberString = " ";
		String html = " ";

		String addressInfo = "";
		String priceInfo = "";
		String itemInfo = "";
		String bedInfo = "";
		String bathInfo = "";
		String sizeInfo = "";
		String need = "";

		html = "https://www.zillow.com/charlotte-nc/50000-_price/";
		Document doc = Jsoup.connect(html).get();
		String title = doc.title();
		System.out.println(title);

		for(int index = pageNumber ; index < maxPageNumber ; index++)
		{
			if(index == 1)
			{
				html = "https://www.zillow.com/charlotte-nc/50000-_price/";
			}
			else
			{
				currPageNumber = index;
				pageString = String.valueOf(currPageNumber);
				pageNumberString = pageString+"_p/";
				html = "https://www.zillow.com/charlotte-nc/50000-_price/"+pageNumberString;
			}
			doc = Jsoup.connect(html).get();

			//Elements information = doc.select(".zsg-photo-card-caption");
			Elements address = doc.select(".zsg-photo-card-address");
			//Elements price = doc.select(".zsg-photo-card-price:contains($)");
			Elements info = doc.select(".zsg-aspect-ratio-content");
			for(int i=0; i < address.size(); i++)
			{
				int t = i;

				need = info.get(i).text();
				addressInfo = address.get(i).text().replaceAll("Charlotte NC", "");
				boolean checkPriceInfo = need.contains("$");

				if(checkPriceInfo == true)
				{
					itemInfo = info.get(i).text().substring(info.get(i).text().indexOf("$"),info.get(i).text().lastIndexOf("·"));

					if(itemInfo.contains("Studio")|| itemInfo.contains("lot"))
					{

					}
					else
					{
						priceInfo = info.get(i).text().substring(info.get(i).text().indexOf("$"),info.get(i).text().lastIndexOf("bd"));
						priceInfo = priceInfo.substring(0,priceInfo.length()-2);
						bedInfo = itemInfo.substring(itemInfo.indexOf("bd")-2,itemInfo.indexOf("bd"));
						bathInfo = itemInfo.substring(itemInfo.indexOf("ba")-2,itemInfo.indexOf("ba"));
						sizeInfo = itemInfo.substring(itemInfo.indexOf("sqft")-6, itemInfo.indexOf("sqft")-1).replaceAll(",", "");
						System.out.println(addressInfo + "\t" + priceInfo + "\t" + bedInfo + "\t" + bathInfo + "\t" + sizeInfo);
					}
				}
				else
				{

				}
			}
		}
	}
}
