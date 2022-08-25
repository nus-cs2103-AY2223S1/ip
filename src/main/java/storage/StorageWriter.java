package storage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * Encapsulates all write operations to disk file storage.
 */
public class StorageWriter {
    private Path path;

    public StorageWriter(Path path) {
        this.path = path;
    }

    /**
     * Appends line to file and returns true if operation completed successfully.
     * @param s line to be appended.
     * @return true if appended successfully.
     */
    public boolean appendLine(String s) {
        try {
            Files.write(path, s.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
            return true;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
        return false;
    }

    /**
     * Removes everything from file at path.
     * Rewrites to file from userInputHistory arraylist in DukeToStorage class.
     * @param index index of line to delete (1-indexed).
     * @return true if line deleted successfully.
     */
    public boolean deleteLine(int index) {
        try {
            String temp = "";
            List<String> history = Files.readAllLines(path);
            Files.write(path, temp.getBytes(StandardCharsets.UTF_8));
            int n = history.size();
            int i;
            for (i = 0; i < n; i++) {
                if (i != index - 1) {
                    temp = history.get(i) + "\n";
                    Files.write(path, temp.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
                }
            }
            return true;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
        return false;
    }

    /**
     * Changes line at index (index - 1) in storage file, by:
     * 1. deleting line at index - 1,
     * 2. appending newString to end of file.
     * @param index line to be changed.
     * @param newString new string to be added instead.
     * @return true if changed successfully.
     */
    public boolean changeLine(int index, String newString) {
        return deleteLine(index) & appendLine(newString);
    }

}
