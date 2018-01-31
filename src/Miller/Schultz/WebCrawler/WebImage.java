
package Miller.Schultz.WebCrawler;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import org.jsoup.nodes.Element;
/**
 * WebImage is a class used to represent Elements that have been
 * determined to be image files.  WebImage contains methods to get and
 * set the image URL, as well as to save the image to a specified 
 * directory.
 * @author Todd Schultz and Alan Miller
 */
public class WebImage implements WebElement {


    private Element image;
    private String absURL;
    DownloadRepository repo;
    
   
    @Override
    public String toString() {
        return this.image.toString();
    }

    /**
     * Constructor for WebImage
     * @param e the Element that represents
     * the WebImage.
     */
    public WebImage(Element e) {
        image = e.clone();
        absURL = image.absUrl("src");

    }

    
    /**
     * This function outputs the WebImage object to an image
     * file in the DownloadRepository Directory.  It uses some
     * String parsing to get the proper image file name from its
     * absolute URL.
     * @throws IOException 
     */
    public void saveToFile() throws IOException {
        
        if(absURL.equals("")){
            return;
        }
        //Get instance of singleton.
        repo = DownloadRepository.getInstance();

        //Parse absURL for applicable fileName.
        int index = absURL.lastIndexOf("/");
        if (index == absURL.length()-1) {
            absURL = absURL.substring(0, index);
        }

        index = absURL.lastIndexOf("/");
        String name = absURL.substring(index, absURL.length());

        //Parsing if '?' is the substring following image file extension.
        if(name.contains("?")){
            index = name.indexOf("?");
            name = name.substring(0, index);
        }
        
        System.out.println(name);
        //new URL for using openStream function.
        URL url = new URL(absURL);

        //Open an InputStream on the image url
        try{InputStream input = url.openStream();
        //Open an OutputStream using the parsed fileName
        OutputStream out = new BufferedOutputStream(new FileOutputStream
        (repo.getImageDirName()+name));
        
        //Write the image element to the output image file.
        for (int b; (b = input.read()) != -1;) {
            out.write(b);
        }
        
        //Cleanup
        out.close();
        input.close();
        }catch (java.io.IOException e){
            System.err.println(e.getLocalizedMessage());
        }

    }

    /**
     * Setter method for the absURL data field.
     * @param s what to set absURL to.
     */
    public void setUrl(String s) {
        absURL = s;
    }

    /**
     * Getter method for absURL.
     * @return absURL
     */
    public String getUrl() {
        return absURL;
    }

}
