package scottie.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Handles storing and retrieving data in a data file.
 */
public class FileStorage implements Storage {
    private final Path filePath;

    /**
     * Constructs a FileStorage.
     *
     * @param filePath The path to the file to use for reading and writing data.
     */
    public FileStorage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the data lines from the data file.
     *
     * @return A List of lines from the data file.
     */
    @Override
    public List<String> loadData() {
        try {
            if (Files.exists(this.filePath)) {
                return Files.readAllLines(this.filePath);
            }
            Files.createDirectories(this.filePath.getParent());
            Files.createFile(this.filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    /**
     * Writes the given lines to the data file.
     * The file is overwritten every time.
     *
     * @param lines The lines to write to the file.
     */
    @Override
    public void saveData(Iterable<String> lines) {
        try {
            Files.createDirectories(this.filePath.getParent());
            Files.write(this.filePath, lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
