/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawler;
//import javax.imageio;

import org.jsoup.nodes.Element;


/**
 *
 * @author Todd
 */
public interface WebElement {
    
    
    public String getUrl();
    public String setUrl();
    public void saveToFile();
    
}
