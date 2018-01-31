
import java.io.File;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import Miller.Schultz.WebCrawler.DownloadRepository;
import Miller.Schultz.WebCrawler.WebFile;
import Miller.Schultz.WebCrawler.WebImage;
import Miller.Schultz.WebCrawler.WebPage;

/**
 *
 * @author Todd
 */
public class UnitTest {

    private WebPage wp;
    private String address;
    private Element e;
    private DownloadRepository repo;
    private static File dirFolder;
    private static File imageFolder;
    private static File fileFolder;

    /**
     * Tests that all web elements are identified and added to the constructed WebPage
     *
     * @throws IOException
     */
    @Test
    public void WebPageParseIdentifiesAllImagesFilesAndWebPages() throws IOException {
        //Precondition
        Assert.assertEquals(wp.getImages().isEmpty(), true);
        Assert.assertEquals(wp.getFiles().isEmpty(), true);
        Assert.assertEquals(wp.getWebpages().isEmpty(), true);
        //Action
        wp.parse();
        //Postcondition
        Assert.assertEquals(wp.getImages().isEmpty(), false);
        Assert.assertEquals(wp.getFiles().isEmpty(), false);
        Assert.assertEquals(wp.getWebpages().isEmpty(), false);
    }

    /**
     * An image folder is constructed and made, it is initialized as an empty directory parse is invoked on the WebPage instance; each image is then saved to disk.
     *
     * @throws IOException
     */
    @Test
    public void SaveImageToDisk() throws IOException {

        imageFolder = new File(repo.getImageDirName());
        boolean imageCheck = imageFolder.mkdirs();
        Assert.assertEquals(imageCheck, true);

        //Precondition
        Assert.assertEquals(0, imageFolder.list().length);
        //Action
        wp.parse();
        for (WebImage img : wp.getImages()) {
            img.saveToFile();
        }
        //Postcondition
        Assert.assertEquals(wp.getImages().size(), imageFolder.list().length);

    }

    /**
     * An file folder is constructed and made, it is initialized as an empty directory parse is invoked on the WebPage instance; each file is then saved to disk.
     *
     * @throws IOException
     */
    @Test
    public void SaveFileToDisk() throws IOException {

        fileFolder = new File(repo.getFileDirName());
        boolean fileCheck = fileFolder.mkdirs();
        Assert.assertEquals(fileCheck, true);
        //Preconditon   
        Assert.assertEquals(0, fileFolder.list().length);
        //Action
        wp.parse();
        for (WebFile file : wp.getFiles()) {
            file.saveToFile();
        }
        //Postcondition
        Assert.assertEquals(wp.getFiles().size(), fileFolder.list().length);

    }

    /**
     * Builds the WebPage a date repo instances for the unit test; Constructs base directory folder to hold directories for files and images
     *
     * @throws IOException
     */
    @Before
    public void SetUp() throws IOException {
        address = "http://www.unca.edu";
        e = Jsoup.connect(address).get();
        wp = new WebPage(e);
        repo = DownloadRepository.getInstance();
        repo.setDirName("outputDir");
        dirFolder = new File(repo.getDirName());

    }

    /**
     * invoker method for each clean helper method
     */
    @AfterClass
    public static void clean() {
        cleanDir();
        Assert.assertNull(dirFolder.list());
        Assert.assertNull(imageFolder.list());
        Assert.assertNull(fileFolder.list());   
    }

    /**
     * cleans static image and file directories and then clears base directory
     */
    private static void cleanDir() {
        cleanImage();
        cleanFile();
        File dir = new File(dirFolder.getAbsolutePath());
        dir.delete();
    }

    /**
     * delete each file contained imageFolder
     */
    private static void cleanImage() {
        File dir = new File(imageFolder.getAbsolutePath());
        for (File f : dir.listFiles()) {
            f.delete();
        }
        dir.delete();
    }

    /**
     * delete each file contained fileFolder
     */
    private static void cleanFile() {
        File dir = new File(fileFolder.getAbsolutePath());
        for (File f : dir.listFiles()) {
            f.delete();
        }
        dir.delete();
    }

}
