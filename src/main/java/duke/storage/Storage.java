package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.util.TaskList;

/**
 * Represents a storage object that deals with loading the tasklist from the txt file
 * and storing the tasklist to the file.
 * @author Huang Yuchen
 * @author hyuchen@u.nus.edu
 */
public class Storage {
    private final String filePath;

    /**
     * Constructor for Storage class.
     *
     * @param filePath the path of the txt file to be used for storing list
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads the txt file line by line for each task
     * and returns an ArrayList of all the tasks in the file.
     *
     * @return an ArrayList of all the tasks in the file
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            File parentFolder = file.getParentFile();
            if (!parentFolder.exists()) {
                parentFolder.mkdirs();
            }
            assert parentFolder.exists() : "Parent folder should exist";
            if (!file.exists()) {
                file.createNewFile();
                return tasks;
            }
            assert file.exists() : "File should exist";
            Scanner sc = new Scanner(file);
            if (!sc.hasNextLine()) {
                return tasks;
            }
            scanStorage(sc, tasks);
        } catch (IOException e) {
            throw new DukeException("File not found!");
        }
        return tasks;
    }

    private void scanStorage(Scanner sc, ArrayList<Task> tasks) throws DukeException {
        String input = sc.nextLine();
        while (true) {
            String[] indTask = input.split(" \\| ");
            String taskType = indTask[0];
            switch (taskType) {
            case "T":
                Todo todo = new Todo(indTask[2]);
                if (indTask[1].equals("1")) {
                    todo.markDone();
                } else {
                    todo.markUndone();
                }
                tasks.add(todo);
                break;
            case "D":
                Deadline deadlineTask = new Deadline(indTask[2], indTask[3]);
                if (indTask[1].equals("1")) {
                    deadlineTask.markDone();
                } else {
                    deadlineTask.markUndone();
                }
                tasks.add(deadlineTask);
                break;
            case "E":
                Event eventTask = new Event(indTask[2], indTask[3]);
                if (indTask[1].equals("1")) {
                    eventTask.markDone();
                } else {
                    eventTask.markUndone();
                }
                tasks.add(eventTask);
                break;
            default:
                // Defensive coding for the case of errors in the starting letters
                throw new DukeException("Invalid task type");
            }
            if (!sc.hasNextLine()) {
                break;
            }
            input = sc.nextLine();
        }
    }

    /**
     * Writes the reformatted ArrayList of tasks to the txt file.
     *
     * @param tasklist the reformatted ArrayList of tasks to be written to the txt file
     */
    public void writeToFile(TaskList tasklist) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath, false);
            for (Task task : tasklist.tasks) {
                fw.write(task.formatTask());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Failed to write to file!");
        }
    }
}
