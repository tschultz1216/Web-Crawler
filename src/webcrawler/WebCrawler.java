/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawler;

//import org.jsoup.Jsoup.;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.Scanner;

/**
 *
 * @author Todd Schultz and Alan Miller
 */
public class WebCrawler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        ArrayList<WebImage> images = new ArrayList<WebImage>();
        // add Scanner to get first input link

        // parse input doc for each "href" 
        // parse input for each image file
        // new doc  for each href link using Jsoup.connect(string).get()
        Element base = Jsoup.connect("https://en.wikipedia.org/").get();
        WebPage doc = new WebPage("https://en.wikipedia.org/");
        doc.parse();
//        Elements e = base.getAllElements();

//        Elements foundPages = base.getElementsByTag("img");
//        for (Element element : foundPages) {
//            WebImage wi = new WebImage(element);
//            System.out.println(element.toString());
//            images.add(wi);
//        }
//        Elements foundImages = doc.getElementsByTag("img");
//        for (Element element : foundImages) {
//            WebImage wi = new WebImage(element);
//            System.out.println(element.toString());
//            images.add(wi);
//        }
//        for (WebImage image : images) {
//            image.saveToFile();
//        }
//    }

    Scanner scanner = new Scanner(System.in);
    //WebPage webPage = new WebPage(scanner.next());

}
