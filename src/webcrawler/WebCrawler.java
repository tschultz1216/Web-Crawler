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

        ArrayList<WebImage> images = new ArrayList<>();
        ArrayList<WebPage> pages = new ArrayList<>();
        ArrayList<WebFile> files = new ArrayList<>(); 
        // TODO code application logic here
        // add Scanner to get first inpput link
        // parse input doc for each "href" 
        // parse input for each image file
        // new doc  for each href link using Jsoup.connect(string).get()
//            System.out.println("Change");
//
        Element doc = Jsoup.connect("http://en.wikipedia.org/").get();
        Elements e = doc.getAllElements();
        Elements foundImages = doc.getElementsByTag("img");
        Elements foundPages = doc.getElementsByAttribute("href");
        Elements foundFiles = doc.getElementsByAttributeValueNot("src", "img");
        for (Element element : foundImages) {
            WebImage wi = new WebImage(element);
//            System.out.println(element.toString());
            images.add(wi);
        }
        for (WebImage image : images) {
            image.saveToFile();
            System.out.println(image.toString());
        }
        for (Element element : foundPages) {
            WebPage pi = new WebPage(element);
            System.out.println(element.toString());
            pages.add(pi);
        }
        for (WebPage page : pages) {
            System.out.println(page.toString());
        }
        for (Element element : foundFiles) {
            WebFile fi = new WebFile(element);
            System.out.println(element.toString());
            files.add(fi);
        }
        for (WebFile file : files) {
//            image.saveToFile();
            System.out.println(file.toString());
        }
}
