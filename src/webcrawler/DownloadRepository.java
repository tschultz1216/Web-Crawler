
package webcrawler;

/**
 * DownloadRepository is a singleton class used to access the name 
 * of the directory which information will be written to during
 * the crawl.
 * @author alanmiller
 */
public class DownloadRepository {
    
    private static DownloadRepository repo;
    private String dirName;
    
    /**
     * Private constructor method to maintain
     * the singleton property.
     */
    private DownloadRepository(){
        
    }
    
    /**
     * Static method to return the static instance
     * of DownloadRepository.
     * @return static instance repo.
     */
    public static DownloadRepository getInstance(){
        if(repo == null){
            repo = new DownloadRepository();
        }
        return repo;
    }
    
    /**
     * Setter method for dirName.
     * @param s the String to set dirName to.
     */
    public void setDirName(String s){
        dirName = s;
    }
    
    /**
     * Getter method for dirName.
     * @return dirName
     */
    public String getDirName(){
        return dirName;
    }
}
