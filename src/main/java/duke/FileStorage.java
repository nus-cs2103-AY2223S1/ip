package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import duke.exception.DukeFileException;
import duke.parser.Parser;
import duke.task.Task;

/**
 * A component of the chatBot Duke that handles reading and writing of data.
 */
public class FileStorage {
    private static final String HOME = System.getProperty("user.home");
    private static final String DIRECTORY_NAME = "dukeData";
    private static final String FILE_NAME = "Duke.txt";
    private Path directoryPath;
    private Path filePath;
    private Parser parser;

    /**
     * Creates a fileStorage object with the given OS path.
     */
    public FileStorage() {
        this.parser = new Parser();
        this.directoryPath = Paths.get(HOME, DIRECTORY_NAME);
        this.filePath = Paths.get(HOME, DIRECTORY_NAME, FILE_NAME);
    }

    public boolean isDirectoryPresent() {
        return Files.exists(directoryPath);
    }

    public boolean isFilePresent() {
        return Files.exists(filePath);
    }

    /**
     * Creates a directory using the directoryPath.
     *
     * @throws DukeFileException The exception that occurs when the directory can't be created.
     */
    public void createDirectory() {
        try {
            Files.createDirectory(directoryPath);
        } catch (IOException e) {
            throw new DukeFileException("Directory cannot be created at this path");
        }
    }

    /**
     * Creates a file using the filePath.
     *
     * @throws DukeFileException The exception that occurs when the file can't be created.
     */
    public void createFile() {
        try {
            Files.createFile(filePath);
        } catch (IOException e) {
            throw new DukeFileException("File cannot be created at this path");
        }
    }

    /**
     * Retrieves the data of the file, converting and returning an arraylist of the saved tasks.
     *
     * @return An arraylist consisting of saved tasks from a past session.
     */
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

    /**
     * Converts the given taskList into data that is to be written into the file.
     *
     * @param taskList The given taskList with tasks to be written and saved.
     */
    public void writeToFile(ArrayList<Task> taskList) {
        try {
            ArrayList<String> list = parser.writeFileContents(taskList);
            Files.write(filePath, list);
        } catch (IOException exception) {
            throw new DukeFileException("Error occurred when writing to file");
        }
    }


}
