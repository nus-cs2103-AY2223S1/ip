package gibson;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    private Path directoryPath;
    private Path filePath;
    private boolean hasDataToLoad = false;

    public Storage(String directoryString, String fileName) {
        this.directoryPath = Paths.get(directoryString);
        this.filePath = Paths.get(directoryString, fileName);
        try {
            if (Files.exists(directoryPath)) {
                if (Files.exists(filePath)) {
                    hasDataToLoad = true;
                } else {
                    Files.createFile(filePath);
                }
            } else {
                Files.createDirectories(directoryPath);
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            System.err.println("Failed to create directory/file. " + e.getMessage());
        }
    }

    public boolean hasDataToLoad() {
        return hasDataToLoad;
    }

    public void save(String data) {
        try {
            Files.write(filePath, data.getBytes());
        } catch (IOException e) {
            System.err.println("Failed to write to Gibson.txt. " + e.getMessage());
        }
    }

    public List<String> load() {
        List<String> linesInSave = new ArrayList<String>();
        try {
            linesInSave = Files.readAllLines(filePath);
        } catch (IOException e) {
            System.err.println("Failed to read Gibson.txt. " + e.getMessage());
        }
        return linesInSave;
    }
}
