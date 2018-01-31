
package Miller.Schultz.WebCrawler;

import java.io.IOException;
import java.net.MalformedURLException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;

/**
 * The WebPage class represents an HTTP web address and contains methods
 * to recursively parse and crawl through any WebPage elements linked to
 * by the current WebPage, as well as methods to determine certain characteristics
 * of various web elements.
 * @author Todd Schultz and Alan Miller
 */
public class WebPage implements WebElement {

    private Element webpage;
    private String url;
    private ArrayList<WebImage> images = new ArrayList<>();
    private ArrayList<WebFile> files = new ArrayList<>();
    private ArrayList<WebPage> pages = new ArrayList<>();

    /**
     * isImage checks the element's absUrl string with both the src and
     * href parameters to see if it ends with any common image file
     * extensions. isImage is not case sensitive.
     * @param element The element determined to be an image or not.
     * @return tree if element is an image.
     */
    public boolean isImage(Element element) {


        if (element.absUrl("src").toLowerCase().contains(".ico")
                || element.absUrl("href").toLowerCase().contains(".ico")
                || element.absUrl("src").toLowerCase().contains(".jpg")
                || element.absUrl("href").toLowerCase().contains(".jpg")
                || element.absUrl("src").toLowerCase().contains(".jpeg")
                || element.absUrl("href").toLowerCase().contains(".jpeg")
                || element.absUrl("src").toLowerCase().contains(".gif")
                || element.absUrl("href").toLowerCase().contains(".gif")
                || element.absUrl("src").toLowerCase().contains(".png")
                || element.absUrl("href").toLowerCase().contains(".png")
                || element.absUrl("src").toLowerCase().contains(".svg")
                || element.absUrl("href").toLowerCase().contains(".svg")
                || element.absUrl("src").toLowerCase().contains("image.php")) {
            return true;
        }

        return false;

    }

    /**
     * crawl invokes parse on the current Web Page to sort its images, files,
     * and links to other pages into Array lists and then saves those elements
     * using their saveToFile(); methods, and then recursively invokes crawl
     * until the implied base case of currentDepth being greater than maxDepth
     * is reached.
     *
     * @param currentDepth passes the current depth of the crawl so that the it
     * might be checked against maxDepth for the base case.
     * @param maxDepth passes the constant maximum depth to check for base case.
     * @throws IOException
     */
    public void crawl(int currentDepth, int maxDepth) throws IOException {

        if (currentDepth == maxDepth) {
            for (WebPage page : pages) {
                try {
                    Element e = Jsoup.connect(page.webpage.absUrl("href")).get();
                    System.out.println(page.webpage.absUrl("href"));
                    WebPage wp = new WebPage(e);
                    wp.parse();
                    for (WebImage img : wp.images) {
                        img.saveToFile();
                    }

                    for (WebFile file : wp.files) {
                        file.saveToFile();
                    }

                    wp.crawl(currentDepth + 1, maxDepth);
                } catch (org.jsoup.HttpStatusException e) {
                    System.err.println("Could not connect to " + e.getUrl() + "\n");
                } catch (javax.net.ssl.SSLHandshakeException e) {
                    System.err.println("Cannot connect to untrustworthy host\n"
                            + e.getLocalizedMessage());
                }

            }
        } else if (currentDepth < maxDepth) {
            for (WebPage page : pages) {
                try {
                    Element e = Jsoup.connect(page.webpage.absUrl("href")).get();
                    System.out.println(page.webpage.absUrl("href"));
                    WebPage wp = new WebPage(e);
                    wp.parse();

                    for (WebImage img : wp.images) {
                        img.saveToFile();
                    }

                    for (WebFile file : wp.files) {
                        file.saveToFile();
                    }
                    wp.crawl(currentDepth + 1, maxDepth);
                } catch (org.jsoup.HttpStatusException e) {
                    System.err.println("Could not connect to " + e.getUrl() + "\n");
                } catch (javax.net.ssl.SSLHandshakeException e) {
                    System.err.println("Cannot connect to untrustworthy host\n"
                            + e.getLocalizedMessage());
                }
            }
        }
        //Implied base case outside of conditionals
        System.out.println("-- Leaf reached max crawl depth --");
    }

    /**
     * parse inserts all the web elements on the page into the WebPages local
     * element specific array lists.
     * @throws IOException 
     */
    public void parse() throws IOException {
        this.setFiles();
        this.setImages();
        this.setWebPages();
    }

    /**
     * Constructor for WebPage that sets a private element data field.
     * @param e 
     */
    public WebPage(Element e) {
        this.webpage = e.clone();
    }

    /**
     * Setter method for private field url.
     * @param s 
     */
    @Override
    public void setUrl(String s) {
        this.url = s;
    }

    /**
     * Constructor that gets an element based on a string name from a
     * network and then sets the private data field URL.
     * @param url
     * @throws IOException 
     */
    public WebPage(String url) throws IOException {
        Element e = Jsoup.connect(url).get();
        this.url = url;
    }

    /**
     * setImages loops through all Elements finding the images
     * and adding them to the local Element ArrayList images.
     */
    public void setImages() {
        Elements foundImages = this.webpage.getElementsByTag("img");
        for (Element element : foundImages) {
            WebImage wi = new WebImage(element);
            images.add(wi);
        }
    }

    /**
     * Getter method for the local image ArrayList images.
     * @return images
     */
    public ArrayList<WebImage> getImages() {
        return images;
    }

    /**
     * setFiles loops through all Elements finding the files
     * and adding them to the local Element ArrayList files.
     */
    public void setFiles() {
        Elements foundFiles = this.webpage.getElementsByAttribute("src");
        for (Element element : foundFiles) {
            if (!isImage(element)) {
                WebFile fi = new WebFile(element);
                files.add(fi);
            }
        }
    }

    /**
     * getter method for the local Element ArrayList files.
     * @return files
     */
    public ArrayList<WebFile> getFiles() {
        
        for(WebFile file : files){
            System.out.println(file.toString());
        }
        return files;
    }

    /**
     * setWebPages loops through all Elements finding links to other
     * web pages and adding them to the local Element ArrayList pages.
     */
    public void setWebPages() {
        Elements foundLinks = this.webpage.getElementsByAttribute("href");
        for (Element element : foundLinks) {
            if (element.absUrl("href").endsWith(".ico") || element.absUrl("href").endsWith(".jpg") || element.absUrl("href").endsWith(".png") || element.absUrl("href").contains("image.php")) {
            } else if (element.absUrl("href").startsWith("http") || element.absUrl("href").startsWith("/")) {
                WebPage pi = new WebPage(element);
                pages.add(pi);
            }

        }
    }

    /**
     * getter method for local Element ArrayList pages.
     * @return pages
     */
    public ArrayList<WebPage> getWebpages() {
        return pages;
    }

    /**
     * SaveToFile method for webPages to implemented later.
     * @throws MalformedURLException 
     */
    @Override
    public void saveToFile() throws MalformedURLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * getter method for WebPage String URL.
     * @return url
     */
    @Override
    public String getUrl() {
        return this.url;
    }

    /**
     * toString method for WebPage that returns the HTML of the
     * WebPage.
     * @return HTML string
     */
    @Override
    public String toString() {
        return this.webpage.toString();
    }
}
