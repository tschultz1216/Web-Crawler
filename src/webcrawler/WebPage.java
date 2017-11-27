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

    private Element thisPage;
    private String url;
    private ArrayList<WebImage> images;
    private ArrayList<WebFile> files;
    private ArrayList<WebPage> pages;

    public void crawl() throws IOException {
        thisPage = Jsoup.connect(url).get();

    }

    public void parse() throws IOException {
        this.getFiles(thisPage);
        this.getImages(thisPage);
        this.getWebpages(thisPage);
    }

    public WebPage(String url) throws IOException {
        Element e = Jsoup.connect(url).get();
        thisPage = e.clone();
        this.url = url;
    }

    public ArrayList<WebImage> getImages(Element e) {
        Elements foundImages = e.getElementsByAttribute("img");
        for (Element element : foundImages) {
            WebImage wi = new WebImage(e);
            images.add(wi);
            System.out.println(wi);
        }
        return images;
    }

    public ArrayList<WebFile> getFiles(Element e) {
        Elements foundFiles = e.getElementsByAttributeValueNot("src", "img");
        for (Element element : foundFiles) {
            WebFile fi = new WebFile(e);
            files.add(fi);
            System.out.println(fi);
        }
        return files;
    }

    public ArrayList<WebPage> getWebpages(Element e) throws IOException {
        Elements foundPages = e.getElementsByAttribute("href");
        for (Element element : foundPages) {
            WebPage wp = new WebPage(e.absUrl("href"));
            pages.add(wp);
            System.out.println(wp);
        }
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
