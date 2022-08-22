public class Storage {
    private String relativePath;

    public Storage(String relativePath) {
        this.relativePath = relativePath;
    }

    public void load(TaskList taskList) {
        // Tries to load data from this.relativePath to taskList
        // Throws exception if
        // 1. File does not exist
        // 2. Folder does not exist
    }

    public void writeTask() {
        // Writes to file.txt at relativePath
    }

    public void removeTask() {
        // Writes to file.txt at relativePath
    }

}
