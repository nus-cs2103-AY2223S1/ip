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

    /**
     * Constructs a new Storage object that saves to the given directory
     * and name the text file with the given name.
     * @param directoryString the relative path where you want the text file to be saved
     * @param fileName the name of the text file that will be saved
     */
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

    /**
     * Saves the given data to the directory specified in the constructor
     * and with the name specified in the constructor.
     * @param data String to be saved in the file
     */
    public void save(String data) {
        try {
            Files.write(filePath, data.getBytes());
        } catch (IOException e) {
            System.err.println("Failed to write to Gibson.txt. " + e.getMessage());
        }
    }

    /**
     * Loads the text in the text file described in the constructor of the object
     * into a List of Strings. Each index in the List is a line in the text file.
     * @return the List of Strings in the text file
     */
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
