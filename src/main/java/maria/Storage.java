package maria;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Consists of the logic to interact with the disk storage.
 */
public class Storage {

    private String fileName;

    /**
     * Creates a storage object linked to a file specified by path.
     * A new storage file is created if the file doesn't exist.
     * @param path The path for the storage file
     */
    public Storage(String path) {

        this.fileName = path;

        try {
            File file = new File(this.fileName);
            file.createNewFile(); // Creates a new file if the file doesn't exist, does nothing otherwise
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert Files.exists(Paths.get(this.fileName)) : "The file " + this.fileName + " doesn't exist.";

    }

    /**
     * Overwrites all the content in a file.
     * @param content The new content to be in the file
     * @throws IOException If there's an error with file access
     */
    public void writeToFile(String content) throws IOException {
        Files.writeString(Path.of(this.fileName), content,
                StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    /**
     * Reads all the content from a file.
     * @return The string for the contents of the file
     * @throws IOException If there's an error with file access
     */
    public String readFromFile() throws IOException {
        return Files.readString(Path.of(this.fileName));
    }

}
