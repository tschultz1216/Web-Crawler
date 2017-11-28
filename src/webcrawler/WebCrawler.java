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
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Todd
 */
public class WebCrawler {

    /*
    Try with this dirName: /Users/alanmiller/Git/Repos/webCrawlerRepo/Web-Crawler
     */
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

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
        Elements e = doc.getAllElements();
        Elements foundImages = doc.getElementsByTag("img");
        for (Element element : foundImages) {
            WebImage wi = new WebImage(element);
//            System.out.println(element.toString());
            images.add(wi);
        }

        for (WebImage image : images) {
            image.saveToFile();
        }
    }
}

