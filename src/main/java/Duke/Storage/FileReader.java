package Duke.Storage;

import Duke.Exceptions.StoredFileException;

import Duke.Tasks.TaskList;
import Duke.Tasks.Task;
import Duke.Tasks.ToDo;
import Duke.Tasks.Event;
import Duke.Tasks.Deadline;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * FileReader
 * provides methods to load the data stored before.
 */
public class FileReader {

    private Path filePath;

    public FileReader(String fileName){
        String dirPath = System.getProperty("user.dir");
        this.filePath = Paths.get(dirPath, "src", "test", "artifacts", "ip_jar", "data", fileName + ".txt");    // Change the storage path
    }

    /**
     * Load all tasks the stored before.
     *
     * @return the stored tasks with a TaskList Object.
     */
    public TaskList load() {
        try {
            Scanner sc = new Scanner(filePath);
            TaskList tasks = new TaskList();        // Set up a class
            while (sc.hasNextLine()) {

                String nextLine = sc.nextLine();
                Task newTask = convertToTask(nextLine);
                tasks.addTask(newTask);

            }
            return tasks;

        } catch (StoredFileException e) {
            System.out.println(e.toString());
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;

        }

    }


    /**
     * Covert from the input line to the task
     * @param content
     * @return the tasks with a Task Object
     * @throws StoredFileException
     */
    private Task convertToTask(String content) throws StoredFileException {  // will be changed later
        try {
            String[] components = content.split(" \\| "); // Here it is very important
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
