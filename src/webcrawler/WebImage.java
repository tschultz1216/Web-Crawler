/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Todd
 */
public class WebImage implements WebElement {

    private Element element;
    private String baseURI;

    public WebImage(Element e) {
        element = e.clone();
        baseURI = element.baseUri();
    }

    public void saveToFile() throws MalformedURLException {

        URL url = new URL(baseURI);
        String fileName = url.getFile();
        String destName = "./images";

        System.out.println(destName);

        try (InputStream stream = url.openStream();){
            
            OutputStream output = new FileOutputStream(destName);

            byte[] b = new byte[2048];
            int length;

            while ((length = stream.read(b)) != -1) {
                output.write(b, 0, length);
            }

            stream.close();
            output.close();
            
        } catch (IOException ex) {
            Logger.getLogger(WebImage.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("ERROR, IO Exception Thrown on Image");
        }
    }

    public void setUrl(String s) {
        baseURI = s;
    }

    public String getUrl() {
        return baseURI;
    }
}
