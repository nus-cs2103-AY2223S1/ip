package meower;

import java.util.ArrayList;

public class Archive {

    private ArrayList<String> archives;
    
    public Archive() {
        this.archives = new ArrayList<String>();
    }

    public boolean contains(String target) {
        return this.archives.contains(target);
    }

    public void clear() {
        this.archives.clear();
    }

    public boolean isEmpty() {
        return this.archives.isEmpty();
    }

    public void add(String newArchive) {
        this.archives.add(newArchive);
    }

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
