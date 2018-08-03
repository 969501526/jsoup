package jedis;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class JsoupTset {
	 public static void main(String[] args) throws IOException {
		 
		Document document = Jsoup.connect("https://www.csdn.net/nav/sec").get();
		
		
		Elements elements = document.select("#feedlist_id").select("li[class=clearfix]").select("div").select("div.title")
				.select("h2").select("a");
		// >  > >  > h1
		for (Element element : elements) {
			String url = element.attr("abs:href");
			Document document2 = Jsoup.connect(url).get();
			Elements title = document2.select("#mainBox").select("main").select("div.blog-content-box").select("div.article-title-box")
					.select("h1");
			//#article_content > div
			Elements content = document2.select("#article_content").select("div");
			
			System.out.println("=================================");
			System.out.println("链接:"+url);
			System.out.println("标题:"+title.text());
			System.out.println("内容:"+content.text());
			System.out.println("=================================");
		}
	 }
	 
	 public void pri() {
		 
	 }
}
