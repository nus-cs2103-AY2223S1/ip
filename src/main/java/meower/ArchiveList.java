package meower;

import java.util.ArrayList;

public class ArchiveList {

    private ArrayList<String> archives;
    
    public ArchiveList() {
        this.archives = new ArrayList<String>();
    }

    
    /** 
     * Returns true if archive contains the input archive name, false otherwise
     * @param target archive name inputted
     * @return boolean
     */
    public boolean contains(String target) {
        return this.archives.contains(target);
    }

    /**
     * Clears the archive list of all archives
     */
    public void clear() {
        this.archives.clear();
    }

    public ArrayList<String> getArchives() {
        return this.archives;
    }

    /** 
     * Returns true if the archive list is empty, false otherwise
     * @return boolean
     */
    public boolean isEmpty() {
        return this.archives.isEmpty();
    }

    
    /** 
     * Adds a new archive into the archive list
     * @param newArchive Input archive to be added
     */
    public void add(String newArchive) {
        this.archives.add(newArchive);
    }

    
    /** 
     * Returns the string representation of an archive as it is displayed in listA command
     * @return String
     */
    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "There are no archives";
        }
        String output = "Archive list:";
        int count = 0;
        for (String archive : archives) {
            count += 1;
            output += String.format("\n%d. %s", count, archive);
        }
        return output;
    }
}
