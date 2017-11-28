/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawler;

//import org.jsoup.Jsoup.;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
<<<<<<< HEAD
import java.util.Iterator;
=======
import java.util.Scanner;
>>>>>>> 82f9caa46910d3d1ea813a3f7c05242ea899e3d6
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.Scanner;

/**
 *
 * @author Todd Schultz and Alan Miller
 */
public class WebCrawler {

    /*
    Try with this dirName: /Users/alanmiller/Git/Repos/webCrawlerRepo/Web-Crawler
     */
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
<<<<<<< HEAD
        
        ArrayList<WebImage> images = new ArrayList<WebImage>();
        // TODO code application logic here
        // add Scanner to get first inpput link
        // parse input doc for each "href" 
        // parse input for each image file
        // new doc  for each href link using Jsoup.connect(string).get()
//            System.out.println("Change");
//
        Element doc = Jsoup.connect("https://en.wikipedia.org/wiki/Sea_mink#/media/File:The_Canadian_field-naturalist_(1988)_(20332897078).jpg").get();
=======

        ArrayList<WebImage> images = new ArrayList<WebImage>();

        DownloadRepository repo = DownloadRepository.getInstance();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter an HTTP address:  ");
        String address = scanner.next();

        System.out.println("\n\nPlease enter a name for the new directory:  ");
        repo.setDirName(scanner.next());

        scanner.close();

        boolean success = (new File(repo.getDirName())).mkdirs();
        if (success) {
            System.out.println("Directories: " + repo.getDirName() + " created");
        }

        Element doc = Jsoup.connect(address).get();
>>>>>>> 82f9caa46910d3d1ea813a3f7c05242ea899e3d6
        Elements e = doc.getAllElements();
        Elements foundImages = doc.getElementsByTag("img");
        for (Element element : foundImages) {
            WebImage wi = new WebImage(element);
//            System.out.println(element.toString());
            images.add(wi);
        }
<<<<<<< HEAD
        for (WebImage image : images) {
            image.saveToFile();
        } //Elements newsHeadlines = doc.select("#mp-itn b a");
//for (Element headline : newsHeadlines) {
//  log("%s\n\t%s", 
//    headline.attr("title"), headline.absUrl("href"));
    }

    Scanner scanner = new Scanner(System.in);
    //WebPage webPage = new WebPage(scanner.next());

=======

        for (WebImage image : images) {
            image.saveToFile();
        }
    }
>>>>>>> 82f9caa46910d3d1ea813a3f7c05242ea899e3d6
}

