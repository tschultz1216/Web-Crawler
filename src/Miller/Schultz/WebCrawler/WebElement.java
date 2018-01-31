/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Miller.Schultz.WebCrawler;
//import javax.imageio;

import java.io.IOException;

/**
 * WebElement is an interface implemented by all of the
 * classes used to represent web elements.  It contains
 * common methods each element class will need to properly
 * interface with the crawl methods.
 * @author Todd Schultz and Alan Miller
 */
public interface WebElement {
    

    public String getUrl();
    public void setUrl(String s);
    public void saveToFile()throws IOException;

}