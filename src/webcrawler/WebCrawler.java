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
        // TODO code application logic here
        // add Scanner to get first inpput link
        // parse input doc for each "href" 
        // parse input for each image file
        // new doc  for each href link using Jsoup.connect(string).get()
//            System.out.println("Change");
//
        Element doc = Jsoup.connect("https://en.wikipedia.org/wiki/Sea_mink#/media/File:The_Canadian_field-naturalist_(1988)_(20332897078).jpg").get();
        Elements e = doc.getAllElements();
        Elements foundImages = doc.getElementsByTag("img");
        for (Element element : foundImages) {
            WebImage wi = new WebImage(element);
//            System.out.println(element.toString());
            images.add(wi);
        }
        for (WebImage image : images) {
            image.saveToFile();
        } //Elements newsHeadlines = doc.select("#mp-itn b a");
//for (Element headline : newsHeadlines) {
//  log("%s\n\t%s", 
//    headline.attr("title"), headline.absUrl("href"));
    }

    Scanner scanner = new Scanner(System.in);
    //WebPage webPage = new WebPage(scanner.next());

}
