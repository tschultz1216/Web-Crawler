/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawler;
//import javax.imageio;

import java.io.IOException;
import org.jsoup.nodes.Element;


import java.net.MalformedURLException;

/**
 *
 * @author Todd Schultz and Alan Miller
 */
public interface WebElement {
    

    public String getUrl();
    public void setUrl(String s);
    public void saveToFile()throws IOException;

}
