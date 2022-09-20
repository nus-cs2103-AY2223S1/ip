package uwu.uwu;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import uwu.exception.LoadingFileErrorException;
import uwu.exception.UwuException;
import uwu.task.Deadline;
import uwu.task.Event;
import uwu.task.Task;
import uwu.task.TaskList;
import uwu.task.ToDos;

/**
 * Represents the file used to store task list data.
 */
public class Storage {
    /** The path to the file where the task list is stored. */
    protected String fileName;

    /**
     * Constructs a Storage object.
     *
     * @param filePath The path to the file where the task list is stored.
     */
    public Storage(String filePath) {
        this.fileName = filePath;
    }

    /**
     * Saves the updated task list to the file located at the filePath.
     *
     * @param taskList The string representation of the stored task list.
     */
    public void save(String taskList) {
        try {
            FileWriter fileWriter = new FileWriter("data/taskList.txt");
            fileWriter.write(taskList);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the saved task list in the file located at the filePath when UwuBot starts.
     * Else, creates a new file to store task list in the filePath.
     *
     * @return The TaskList stored in the file.
     * @throws UwuException If there are any problems loading the file.
     */
    public TaskList load() throws UwuException {
        TaskList result = new TaskList();

        try {
            File taskFile = new File(fileName);
            taskFile.createNewFile();

            assert Files.exists(Paths.get(this.fileName)) : "The file, " + this.fileName + ", does not exist.";

            Scanner scanner = new Scanner(taskFile);

            while (scanner.hasNextLine()) {
                String task = scanner.nextLine();
                Task decodedTask = decodeTask(task);
                result.add(decodedTask);
            }
        } catch (IOException e) {
            throw new LoadingFileErrorException("oops! seems like there is trouble loading the task list file TT");
        }

        return result;
    }

    /**
     * Decodes the individual tasks in the storage file from the storage string to a task.
     *
     * @param task The storage string form of the task.
     * @return The task that was encoded.
     * @throws UwuException If the task was not stored properly.
     */
    public Task decodeTask(String task) throws UwuException {
        String[] taskData = task.split(",");
        String taskType = taskData[0];
        String taskIsDone = taskData[1];
        String taskDescription = taskData[2];

        boolean isToDo = taskType.equals("T");
        boolean isDeadline = taskType.equals("D");
        boolean isEvent = taskType.equals("E");

        if (isToDo) {
            ToDos todo = new ToDos(taskDescription);
            todo.setIsDone(taskIsDone.equals("1"));
            return todo;
        } else if (isDeadline) {
            String taskDeadline = taskData[3];
            Deadline deadline = new Deadline(taskDescription, taskDeadline);
            deadline.setIsDone(taskIsDone.equals("1"));
            return deadline;
        } else if (isEvent) {
            String eventStart = taskData[3];
            Event event = new Event(taskDescription, eventStart);
            event.setIsDone(taskIsDone.equals("1"));
            return event;
        } else {
            throw new LoadingFileErrorException("oops! seems like there is trouble loading the task list "
                    + "file TT");
        }
    }
}
