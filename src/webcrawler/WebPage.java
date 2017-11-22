/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawler;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
/**
 *
 * @author Todd Schultz and Alan Miller
 */


public class WebPage implements WebElement{
    
    private Element element;
    private String url;
    private ArrayList<WebFile> files;
    private ArrayList<WebImage> images;
    private ArrayList<WebPage> pages;
    
    public WebPage(String string){
        url = string;
    }
    
    public WebPage(Element e){
        element = e.clone();
    }
    
    public void crawl() throws IOException{
        Document doc = Jsoup.connect(url).get();
        
    }
    
    public ArrayList<WebImage> getImages(){
        return images;
    }
    
    public ArrayList<WebFile> getFiles(){
        return files;
    }
    
    public ArrayList<WebPage> getWebPages(){
        return pages;
    }
    /**
     * Getter method for private data field url.
     * @return private date field url.
     */
    public String getUrl(){
        return url;
    }
    
    /**
     * Setter method for private data field url.
     * @param url url to set private field to.
     */
    public void setUrl(String url){
        this.url = url;
    }
    
    public void saveToFile(){
        
    }
    
}
