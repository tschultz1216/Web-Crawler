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

    private Element webpage;
    private String url;
    private ArrayList<WebImage> images;
    private ArrayList<WebFile> files;
    private ArrayList<WebPage> pages;

    public void crawl() throws IOException {


    }

    public void parse() throws IOException {
        this.getFiles();
        this.getImages();
        this.getWebpages();
//        Document doc = Jsoup.connect(url).get();

    }

    public WebPage(Element e) {
        this.webpage = e.clone();
    }

    @Override
    public void setUrl(String s) {
        this.url = s;
    }

    public WebPage(String url) throws IOException {
        Element e = Jsoup.connect(url).get();
        this.url = url;
    }

    public ArrayList<WebImage> getImages() {
        Elements foundImages = this.webpage.getElementsByAttribute("img");
        for (Element element : foundImages) {
            WebImage wi = new WebImage(element);
            images.add(wi);
            System.out.println(wi);
        }
        return images;
    }


    public ArrayList<WebFile> getFiles() {
        Elements foundImages = this.webpage.getElementsByAttributeValueNot("src", "img");
        for (Element element : foundImages) {
            WebFile fi = new WebFile(element);
            files.add(fi);
        }        return files;
    }

    public ArrayList<WebPage> getWebpages() {
        Elements foundPages = this.webpage.getElementsByAttribute("href");
        for (Element element : foundPages) {
            WebPage pi = new WebPage(element);
            pages.add(pi);
        }
        return pages;
    }

    @Override
    public void saveToFile() throws MalformedURLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUrl() {
        return this.url;
    }
    
    @Override
    public String toString(){
        return this.webpage.toString();
    }
}
