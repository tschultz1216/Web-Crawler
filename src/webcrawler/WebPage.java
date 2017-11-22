/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawler;

import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Todd
 */
public class WebPage {

    private String url;
    private ArrayList<WebImage> images;
    private ArrayList<WebFile> files;
    private ArrayList<WebPage> pages;

    public void crawl() throws IOException {
        Document doc = Jsoup.connect(url).get();
  
    }

    public void WebPage(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public ArrayList<WebImage> getImages(Element e) {
        Elements foundImages = e.getElementsByAttribute("img");
        for(Element element : foundImages){
            WebImage wi = new WebImage(element);
            images.add(wi);
        }
        return images;
    }

    public ArrayList<WebFile> getFiles() {
        return files;
    }

    public ArrayList<WebPage> getWebpages() {
        return pages;
    }
}
