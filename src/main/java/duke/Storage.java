package duke;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private final String filePathStr;

    /**
     * Constructs a storage object with the specified file path.
     *
     * @param filePathStr The specified file path.
     */
    public Storage(String filePathStr) {
        this.filePathStr = filePathStr;
    }

    /**
     * Loads tasks from file.
     *
     * @return List of tasks from file.
     * @throws DukeException If there is an error in loading the tasks from file.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        Path filePath = Paths.get(filePathStr);

        boolean isDataFileExisting = Files.exists(filePath);

        if (!isDataFileExisting) {
            File f1 = new File(filePathStr);
            boolean isDirsMade = f1.getParentFile().mkdirs();
            if (!isDirsMade) {
                System.out.println("Directories not made");
            }
        }

        try (BufferedReader br = new BufferedReader(
                new FileReader(filePathStr))) {
            String line;
            while ((line = br.readLine()) != null) {
                Task newTask;
                // process data
                switch (line.charAt(4)) {
                    case 'T':
                        newTask = ToDo.createToDoFromString(line);
                        break;
                    case 'E':
                        newTask = Event.createEventFromString(line);
                        break;
                    case 'D':
                        newTask = Deadline.createDeadlineFromString(line);
                        break;
                    default:
                        newTask = null;
                        break;
                }
                tasks.add(newTask);
            }
        } catch (IOException | NullPointerException e) {
            throw new DukeException("There was an error loading your existing tasks, no tasks were loaded :(");
        }
        return tasks;
    }

    /**
     * Saves the specified list of tasks.
     *
     * @param taskList The specified list of tasks.
     * @throws DukeException If the list could not be saved.
     */
    public void save(TaskList taskList) throws DukeException {
        byte[] data = taskList.toString().getBytes();
        Path filePath = Paths.get(filePathStr);

        boolean isDataFileExisting = Files.exists(filePath);

        if (!isDataFileExisting) {
            File f1 = new File(filePathStr);
            boolean isDirsMade = f1.getParentFile().mkdirs();
            if (!isDirsMade) {
                System.out.println("Directories not made");
            }
        }

        try (OutputStream out = new BufferedOutputStream(
                Files.newOutputStream(filePath))) {
            out.write(data, 0, data.length);
        } catch (IOException x) {
            throw new DukeException("There was an error saving your changes!");
        }
    }
}
