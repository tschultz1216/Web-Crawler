
package Miller.Schultz.WebCrawler;

/**
 * DownloadRepository is a singleton class used to access the name 
 * of the directory which information will be written to during
 * the crawl.
 * @author alanmiller
 */
public class DownloadRepository {
    
    private static DownloadRepository repo;
    //String containing the pathname for the parent directory.
    private String dirName;
    //String containing the pathname for the child image directory.
    private String imageDirName;
    //String containing the pathname for the child file directory.
    private String fileDirName;
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
        imageDirName = s+"/imageDir";
        fileDirName = s+"/fileDir";
    }
    
    /**
     * Getter method for dirName.
     * @return dirName
     */
    public String getDirName(){
        return dirName;
    }
    
    /**
     * Getter method for fileDirName.
     * @return fileDirName.
     */
    public String getFileDirName(){
        return fileDirName;
    }
    
    /**
     * Getter method for imageDirName
     * @return imageDirnName.
     */
    public String getImageDirName(){
        return imageDirName;
    }
}
