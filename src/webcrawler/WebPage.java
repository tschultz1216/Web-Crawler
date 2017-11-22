/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
/**
 *
 * @author Todd Schultz and Alan Miller
 */
public class WebPage implements WebElement {

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


    public ArrayList<WebImage> getImages(Element e) {
        Elements foundImages = e.getElementsByAttribute("img");
        for(Element element : foundImages){
            WebImage wi = new WebImage(e);
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

    @Override
    public void setUrl(String s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveToFile() throws MalformedURLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String getUrl() {
        return this.url;
    }
}
