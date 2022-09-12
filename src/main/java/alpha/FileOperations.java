package alpha;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import alpha.task.Deadline;
import alpha.task.Event;
import alpha.task.Task;
import alpha.task.Todo;

/**
 * Operates on the file.
 */
public class FileOperations {

    /** File to store data locally */
    private final File f;

    /** Directory of the file */
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    private final String filePath;

    /**
     * Constructor to initialise the global variables.
     *
     * @param filePath To initialise the directory of the file.
     */
    public FileOperations(String filePath) {
        this.filePath = filePath;
        f = new File(filePath);
    }

    /**
     * Creates a file in the specified directory if it does not already exist.
     *
     * @throws AlphaException If the file cannot be created in the specified directory.
     */
    public void createFile() throws AlphaException {
        try {
            f.createNewFile();
        } catch (IOException e) {
            throw new AlphaException(e.getMessage());
        }
    }

    /**
     * Writes data to the file.
     *
     * @param textToAppend Text to be added to the file.
     * @throws AlphaException If file cannot be found or unable to write to file.
     */
    public void writeToFile(String textToAppend) throws AlphaException {
        try {
            FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
            fw.write(textToAppend);
            fw.close();
        } catch (IOException e) {
            throw new AlphaException(e.getMessage());
        }
    }

    /**
     * Rewrites the entire file.
     *
     * @param taskList The list of tasks to be added to the file.
     * @throws AlphaException If file cannot be found or unable to write to file.
     */
    public void rewriteFile(TaskList taskList) throws AlphaException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task t: taskList.tasks) {
                String textToAdd = t.getTaskType() + " | " + t.getStatus() + " | " + t.getDescription();
                if (t instanceof Todo) {
                    textToAdd += "\n";
                }
                if (t instanceof Event) {
                    Event e = (Event) t;
                    textToAdd += " | " + e.getDate() + "\n";
                } else if (t instanceof Deadline) {
                    Deadline d = (Deadline) t;
                    textToAdd += " | " + d.getDeadline() + "\n";
                }
                fw.write(textToAdd);
            }
            fw.close();
        } catch (IOException e) {
            throw new AlphaException(e.getMessage());
        }

    }

    /**
     * Reads data from the file.
     * Returns the list of the tasks stored in the file.
     *
     * @return List of tasks.
     * @throws AlphaException If file is not found or cannot be read.
     */
    public List<Task> readFile() throws AlphaException {
        List<Task> tasksInFile = new ArrayList<>();
        Scanner s;
        try {
            s = new Scanner(f); // create a Scanner using the File as the source
        } catch (FileNotFoundException e) {
            throw new AlphaException(e.getMessage());
        }
        while (s.hasNext()) {
            String task = s.nextLine();
            String taskType = String.valueOf(task.charAt(1));
            boolean taskStatus = String.valueOf(task.charAt(5)) == "X";
            switch (taskType) {
            case "T": {
                String taskDescription = task.substring(8);
                Task todo = new Todo(taskDescription, taskType);
                todo.changeStatus(taskStatus);
                tasksInFile.add(todo);
                break;
            }
            case "E": {
                String taskDescription = task.substring(8, task.length() - 15);
                String date = task.substring(task.length() - 12, task.length() - 1);
                Task event = new Event(taskDescription, date, taskType);
                event.changeStatus(taskStatus);
                tasksInFile.add(event);
                break;
            }
            case "D": {
                String taskDescription = task.substring(8, task.length() - 15);
                String deadlineDate = task.substring(task.length() - 12, task.length() - 1);
                Task deadline = new Deadline(taskDescription, deadlineDate, taskType);
                deadline.changeStatus(taskStatus);
                tasksInFile.add(deadline);
                break;
            }
            default:
            }
        }
        return tasksInFile;
    }
}
