package Duke.Storage;

import Duke.Exceptions.StoredFileException;

import Duke.Tasks.TaskList;
import Duke.Tasks.Task;
import Duke.Tasks.ToDo;
import Duke.Tasks.Event;
import Duke.Tasks.Deadline;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Class that provides methods to load the data stored before.
 */
public class FileReader {
    private Path filePath;

    /**
     * Public constructor of FileReader Class.
     * Sets up the file path, and checks whether the file exists.
     * @param inputFileName File name to read the data from.
     * @throws IOException Exception may occur when creating new file.
     */
    public FileReader(String inputFileName) throws IOException {
        String fileName = inputFileName + ".txt";
        File dukeFile = new File("data", fileName);
        if (!dukeFile.exists()) {
            dukeFile.createNewFile();
        }
        this.filePath = Paths.get(String.valueOf(dukeFile));
    }

    /**
     * Loads all tasks which are stored in the file.
     * @return TaskList with all tasks in the file.
     */
    public TaskList load() {
        try {
            Scanner sc = new Scanner(filePath);
            TaskList tasks = new TaskList();
            while (sc.hasNextLine()) {
                String nextLine = sc.nextLine();
                Task newTask = convertToTask(nextLine);
                tasks.addTask(newTask);
            }
            return tasks;
        } catch (StoredFileException e) {
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Converts from the input line to the task.
     * @param content String of input.
     * @return Task based on input string.
     * @throws StoredFileException If the content of stored file has exception, e.g. not stored in the correct form.
     */
    private Task convertToTask(String content) throws StoredFileException {  // will be changed later
        try {
            String[] components = content.split(" \\| ");
            String type = components[0].strip();

            switch (type) {

            case "T":
                return new ToDo(
                        components[2].strip(),
                        components[1].strip().equals("true"));
            case "D":
                return new Deadline(
                        components[2].strip(),
                        LocalDateTime.parse(components[3].strip()).toLocalDate(),
                        LocalDateTime.parse(components[3].strip()).toLocalTime(),
                        components[1].strip().equals("true"));
            case "E":
                return new Event(
                        components[2].strip(),
                        LocalDateTime.parse(components[3].strip()),
                        components[1].strip().equals("true"));
            default:
                throw new Exception();
            }

        } catch (Exception e) {
            throw new StoredFileException();
        }
    }

}
