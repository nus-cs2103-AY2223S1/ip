package duke.storage;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {

    public static final String DEFAULT_STORAGE_FILE = "tasklist.txt";
    public static final String DEFAULT_STORAGE_FOLDER = "Resources";
    public static final String CURRENT_DIRECTORY = "user.dir";

    public final Path path;


    public Storage(String filePath) {
        String currentDir = System.getProperty(CURRENT_DIRECTORY);
        this.path = Paths.get(currentDir, DEFAULT_STORAGE_FOLDER, DEFAULT_STORAGE_FILE);
    }

    private static boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".txt");
    }

}
