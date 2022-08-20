import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class FileStorage {
    private Path directoryPath;
    private Path filePath;
    private Parser parser;
    public FileStorage(String home) {
        this.parser = new Parser();
        this.directoryPath = Paths.get(home, "dukeData");
        this.filePath = Paths.get(home, "dukeData", "Duke.txt");
    }

    public boolean isDirectoryPresent() {
        return Files.exists(directoryPath);
    }

    public boolean isFilePresent() {
        return Files.exists(filePath);
    }

    public void createDirectory() {
        try {
            Files.createDirectory(directoryPath);
        } catch (IOException e) {
            throw new DukeFileException("Directory cannot be created at this path");
        }
    }

    public void createFile() {
        try {
            Files.createFile(filePath);
        } catch (IOException e) {
            throw new DukeFileException("File cannot be created at this path");
        }
    }

    public ArrayList<Task> retrieveFileContents() {
        ArrayList<Task> listOfTasks;
        try {
            List<String> contents = Files.readAllLines(filePath);
            listOfTasks = parser.parseFileContents(contents);
        } catch (IOException e) {
            throw new DukeFileException("Error occurred when retrieving file");
        }
        return listOfTasks;
    }

    public void writeToFile(ArrayList<Task> taskList) {
        try {
            ArrayList<String> list = parser.writeFileContents(taskList);
            Files.write(filePath, list);
        } catch (IOException exception) {
            throw new DukeFileException("Error occurred when writing to file");
        }
    }


}
