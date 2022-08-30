package scottie.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Handles storing and retrieving data in a data file.
 */
public class Storage {
    private final Path filePath;

    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the data lines from the data file.
     *
     * @return A List of lines from the data file.
     */
    public List<String> loadData() {
        try {
            if (Files.exists(this.filePath)) {
                return Files.readAllLines(this.filePath);
            } else {
                Files.createDirectories(this.filePath.getParent());
                Files.createFile(this.filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    /**
     * Writes the given list of lines to the data file.
     * The file is overwritten every time.
     *
     * @param lines The lines to write to the file.
     */
    public void saveData(Iterable<String> lines) {
        try {
            Files.createDirectories(this.filePath.getParent());
            Files.write(this.filePath, lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
