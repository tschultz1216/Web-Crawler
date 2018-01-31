package Miller.Schultz.WebCrawler;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import org.jsoup.nodes.Element;

/**
 * WebFile is a class used to represent Elements determined to be files, as well
 * as methods to get and set their URL and to download them to a specified
 * directory.
 *
 * @author Todd Schultz and Alan Miller
 */
public class WebFile implements WebElement {

    private String absURL;
    private Element element;
    DownloadRepository repo;

    /**
     * Constructor that sets private data field Element element and private data
     * field absURL.
     *
     * @param e Element to clone.
     */
    public WebFile(Element e) {
        element = e.clone();
        //System.out.println("New WebFile Element =\n" +element.absUrl("src"));
        absURL = element.absUrl("src");
    }

    /**
     * toString method returns local private Element element.toString();
     *
     * @return element.toString();
     */
    public String toString() {
        return element.toString();
    }

    /**
     * Getter method for local private data field absURL.
     *
     * @return absURL.
     */
    @Override
    public String getUrl() {
        return absURL;
    }

    /**
     * saveToFile parses the absURL data field for a valid file name and then
     * saves the element to the specified directory using a byte stream.
     *
     * @throws MalformedURLException
     * @throws IOException
     */
    @Override
    public void saveToFile() throws MalformedURLException, IOException {

        if (absURL.equals("")) {
            return;
        }
        //Get instance of Singleton class.
        repo = DownloadRepository.getInstance();

        //System.out.println(absURL +": preSubstring webFile URL");
        int index = absURL.lastIndexOf("/");
        if (index == absURL.length() - 1) {
            absURL = absURL.substring(0, index);
        }

        String name;

        if (absURL.contains("?href")) {
            index = absURL.indexOf("?href");
            absURL = absURL.substring(0, index);
        }

        //System.out.println(absURL +": webFile URL");
        index = absURL.lastIndexOf("/");
        name = absURL.substring(index, absURL.length());

        //Parsing if '?' is the substring following image file extension.
        if (name.contains("?")) {
            index = name.indexOf("?");
            name = name.substring(0, index);
        }

        //Checking for any image file duplicates.
        if (name.toLowerCase().endsWith(".jpg")
                || name.toLowerCase().endsWith(".png")
                || name.toLowerCase().endsWith(".gif")
                || name.toLowerCase().endsWith(".svg")
                || name.toLowerCase().endsWith(".jpeg")
                || name.toLowerCase().endsWith(".ico")) {
            return;
        }

        System.out.println(name);

        URL url = new URL(absURL);

        try {
            //Create input stream from element URL.
            InputStream in = new BufferedInputStream(url.openStream());
            //Create byte output stream, outputting 1024 bytes at a time.
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int n = 0;
            //in.read returns -1 if EOF is reached.
            while (-1 != (n = in.read(buffer))) {
                out.write(buffer, 0, n);
            }
            //cleanup
            out.close();
            in.close();
            //Send outputstream to byte array output
            byte[] output = out.toByteArray();
            //Output byte array to FileStream on specified pathname.
            FileOutputStream fos = new FileOutputStream(repo.getFileDirName() + name);
            //cleanup
            fos.write(output);
            fos.close();
        //Catch any 403 / 404 type errors.
        } catch (java.io.IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    /**
     * Setter method for local private date field absURL.
     * @param s 
     */
    @Override
    public void setUrl(String s) {
        absURL = s;
    }

}
