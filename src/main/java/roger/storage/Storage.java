package roger.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


/**
 * Encapsulates storage for a text file.
 */
public class Storage {
    private Path path;

    /**
     * Create a Storage instance.
     *
     * @param path The file path to load and write to.
     */
    public Storage(Path path) {
        this.path = path;
    }

    /**
     * Load a .txt file from the designated storage path.
     *
     * @return Each line of the .txt file.
     * @throws IOException Could not read file.
     */
    public List<String> load() throws IOException {
        return Files.readAllLines(this.path);
    }

    /**
     * Create and write to the .txt file designated in the storage path.
     * If the file already exists, it will be overwritten.
     *
     * @param lines The lines of text to write to the file.
     * @throws IOException Could not write to file.
     */
    public void write(List<String> lines) throws IOException {
        try {
            Files.delete(path);
        } catch (IOException ignored) {
            // File does not exist yet.
        }

        File file = new File(String.valueOf(path.toAbsolutePath()));
        file.getParentFile().mkdirs();
        file.createNewFile();
        FileWriter fw = new FileWriter(file);
        for (String line: lines) {
            fw.append(line + '\n');
        }

        fw.close();
    }

}
