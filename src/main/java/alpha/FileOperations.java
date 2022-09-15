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
                String textToAdd = "[" + t.getTaskType() + "] [" + t.getTag() + "] ["
                        + t.getStatus() + "] " + t.getDescription();
                if (t instanceof Todo) {
                    textToAdd += "\n";
                } else if (t instanceof Event) {
                    Event e = (Event) t;
                    textToAdd += " (on " + e.getDate() + ")\n";
                } else {
                    assert t instanceof Deadline;
                    Deadline d = (Deadline) t;
                    textToAdd += " (by " + d.getDeadline() + ")\n";
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
    protected List<Task> readFile() throws AlphaException {
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
            int indexOfStatus = task.indexOf("[", 5) + 1;
            int endingIndexOfTag = task.indexOf("]", 5);
            String tag = task.substring(5, endingIndexOfTag);
            boolean taskStatus = String.valueOf(task.charAt(indexOfStatus)).equals("X");
            int indexOfDescription;
            if (taskStatus) {
                indexOfDescription = indexOfStatus + 3;
            } else {
                indexOfDescription = indexOfStatus + 11;
            }
            switch (taskType) {
            case "T": {
                //substring lengths based on format of text being saved in the .txt file
                String taskDescription = task.substring(indexOfDescription);
                Task todo = new Todo(taskDescription, taskType);
                todo.changeStatus(taskStatus);
                if (!tag.equals("")) {
                    todo.addTag(tag);
                }
                tasksInFile.add(todo);
                break;
            }
            case "E": {
                //substring lengths based on format of text being saved in the .txt file
                String taskDescription = task.substring(indexOfDescription, task.length() - 16);
                String date = task.substring(task.length() - 12, task.length() - 1);
                Task event = new Event(taskDescription, date, taskType);
                event.changeStatus(taskStatus);
                if (!tag.equals("")) {
                    event.addTag(tag);
                }
                tasksInFile.add(event);
                break;
            }
            case "D": {
                //substring lengths based on format of text being saved in the .txt file
                String taskDescription = task.substring(indexOfDescription, task.length() - 16);
                String deadlineDate = task.substring(task.length() - 12, task.length() - 1);
                Task deadline = new Deadline(taskDescription, deadlineDate, taskType);
                deadline.changeStatus(taskStatus);
                if (!tag.equals("")) {
                    deadline.addTag(tag);
                }
                tasksInFile.add(deadline);
                break;
            }
            default:
                assert false : taskType;
            }
        }
        return tasksInFile;
    }
}
