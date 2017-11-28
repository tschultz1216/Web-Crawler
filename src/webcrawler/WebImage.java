/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawler;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import org.jsoup.nodes.Element;

/**
 *
 * @author Todd Schultz and Alan Miller
 */
public class WebImage implements WebElement {

    static final String pathName = "/Users/alanmiller/Git/Repos/webCrawlerRepo/Web-Crawler";
    private Element image;
    private String absURL;

    public String toString() {
        return this.image.toString();
    }

    public WebImage(Element e) {
        image = e.clone();
        absURL = image.absUrl("src");
        //baseURI = image.toString().substring(image.toString().indexOf("src=\"")+5);
        //System.out.println(baseURI);
        //baseURI = baseURI.substring(0, baseURI.indexOf(".jpg")+4);

    }

    @Override
    public void saveToFile() throws IOException {

        int index = absURL.lastIndexOf("/");
        if (index == absURL.length()) {
            absURL = absURL.substring(1, index);
        }

        index = absURL.lastIndexOf("/");
        String name = absURL.substring(index, absURL.length());

        System.out.println(name);

        URL url = new URL(absURL);

        InputStream input = url.openStream();
        OutputStream out = new BufferedOutputStream(new FileOutputStream(pathName+name));
        
        for (int b; (b = input.read()) != -1;) {
            out.write(b);
        }
        out.close();
        input.close();

    }

    public void setUrl(String s) {
        absURL = s;
    }

    public String getUrl() {
        return absURL;
    }
}
