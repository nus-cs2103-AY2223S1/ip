package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Storage is a Storage that loads and saves data to file.
 *
 * @author John Russell Himawan
 * @version CS2103T AY22/23 Sem 1
 */
public class Storage {
    private static final String DIRECTORY = System.getProperty("user.dir");
    private String filePath = "data/duke.txt";

    /**
     * Constructor for Storage.
     */
    public Storage() {
        this.filePath = DIRECTORY + "/" + filePath;
    }

    /**
     * Loads the data from the file.
     *
     * @return ArrayList containing tasks.
     * @throws DukeException
     * @throws IOException
     */
    public ArrayList<Task> load() throws DukeException, IOException {
        ArrayList<Task> dukeList = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            File parent = new File(DIRECTORY + "/data");
            boolean isDirectoryCreated = parent.mkdir();
            boolean isFileCreated = file.createNewFile();
        }
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            add(dukeList, input);
        }
        return dukeList;
    }

    /**
     * Adds the task to the dukeList.
     * @param dukeList ArrayList of Task.
     * @param input String representation of input.
     */
    public void add(ArrayList<Task> dukeList, String input) {
        String[] splitInput = input.split(Pattern.quote(" | "));
        String taskType = splitInput[0];
        String taskStatus = splitInput[1];
        String taskDescription = splitInput[2];
        boolean isComplete = taskStatus.equals("1");
        switch (taskType) {
        case "T":
            Todo todo = new Todo(taskDescription);
            dukeList.add(todo);
            break;
        case "D":
            String by = splitInput[3];
            Deadline deadline = new Deadline(taskDescription, by);
            dukeList.add(deadline);
            break;
        case "E":
            String at = splitInput[3];
            Event event = new Event(taskDescription, at);
            dukeList.add(event);
            break;
        default:
            break;
        }
        if (isComplete) {
            int size = dukeList.size();
            dukeList.get(size - 1).markAsDone();
        }
    }

    /**
     * Saves the data to the file.
     *
     * @param tasks TaskList containing the tasks.
     * @throws IOException
     */
    public void save(TaskList tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for (int i = 0; i < tasks.getSize(); i++) {
            Task curr = tasks.getTask(i);
            String status = curr.getStatusIcon().equals("X") ? "1" : "0";
            if (curr instanceof Todo) {
                writer.write("T | " + status + " | " + curr.getDescription() + "\n");
            } else if (curr instanceof Deadline) {
                writer.write("D | " + status + " | " + curr.getDescription() + " | "
                        + ((Deadline) curr).getBy().toString() + "\n");
            } else if (curr instanceof Event) {
                writer.write("E | " + status + " | " + curr.getDescription() + " | "
                        + ((Event) curr).getAt().toString() + "\n");
            }
        }
        writer.close();
    }
}
