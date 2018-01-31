package Miller.Schultz.WebCrawler;

/*
 * Alan Miller, Todd Schultz, CSCI 373, Sheaffer
 * Project 2, Web Crawler.
 */
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

/**
 * This java application gets a user provided HTTP address, directory pathname,
 * and crawl depth, and then downloads all of the accessible files hosted by
 * that HTTP address while recursively doing the same to all linked HTTP
 * addresses from the original address. The crawl terminates once the depth of
 * the recursion has reached the maximum crawl depth provided by the user. The
 * results will be contained in two directories contained in the pathname
 * provided by the user. If the user provides a non-existant pathname the
 * application will attempt to create and use that pathname anyway.
 *
 * @author Todd
 */
public class WebCrawler {

    public static boolean tryURL(String address) throws IOException {

        try {
            Element doc = Jsoup.connect(address).get();
        } catch (java.lang.IllegalArgumentException e) {
            System.out.println("Invalid HTTP Address, Try again:");
            return false;
        }
        return true;
    }

    /**
     * The main function gets the necessary information from the user and
     * invokes crawl on the provided HTTP address.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        //Singleton instance
        DownloadRepository repo = DownloadRepository.getInstance();

        Scanner scanner = new Scanner(System.in);
        boolean check = false;
        String address = null;
        while (!check) {
            System.out.println("Please enter an HTTP address:  ");
            address = scanner.next();
            if (tryURL(address)) {
                check = true;
            }
        }

        System.out.println("\n\nPlease enter a name for the new directory:  ");
        repo.setDirName(scanner.next());

        System.out.println("\n\nPlease enter desired crawl depth:");
        int depth = scanner.nextInt();

        scanner.close();

        /*
        Error checking to see if the application was able to create the
        specified pathnames and child pathnames.
         */
        boolean success = (new File(repo.getDirName())).mkdirs();
        if (success) {
            System.out.println("Directories: " + repo.getDirName() + " created");
        } else {
            System.err.println("Error creating directory " + repo.getDirName()
                    + "\nTerminating.");
            return;
        }

        success = (new File(repo.getFileDirName())).mkdirs();
        if (success) {
            System.out.println("Directories: " + repo.getFileDirName() + " created");
        } else {
            System.err.println("Error creating directory " + repo.getFileDirName()
                    + "\nTerminating.");
            return;
        }

        success = (new File(repo.getImageDirName())).mkdirs();
        if (success) {
            System.out.println("Directories: " + repo.getImageDirName() + " created");
        } else {
            System.err.println("Error creating directory " + repo.getImageDirName()
                    + "\nTerminating.");
            return;
        }

        //Get the first WebPage instance using provided HTTP address.
        Element doc = Jsoup.connect(address).get();
        WebPage wp = new WebPage(doc);
        wp.parse();
        wp.crawl(0, depth);

    }
}
