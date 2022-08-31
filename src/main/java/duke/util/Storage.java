package duke.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Class responsible for saving and loading tasks to and from the hard disk
 *
 * @author Nephelite
 * @version 0.1
 */
public class Storage {
    /**
     * File path to the root directory where this class resides
     * (Duke should also be in the same directory)
     */
    private final String saveFilePath;

    /**
     * Constructor for a Storage object. Mostly only for initialising the saveFilePath class field
     */
    public Storage() {
        this.saveFilePath = locateHomeDir();
    }

    /**
     * Retrieves the file path used by Duke to store previously saved tasks
     *
     * @return String representation of the file path
     */
    public String locateHomeDir() {
        return System.getProperty("user.dir") + "\\data\\saves\\tasks.txt";
    }
    /**
     * Saves a TaskList object in a file with the path saveFilePath
     *
     * @param tasks TaskList object Duke is using
     * @since 0.1
     */
    public void saveDuke(TaskList tasks) {
        try {
            File save = new File(saveFilePath);
            FileWriter saveWriter = new FileWriter(save);
            String saveString = "";

            for (int i = 0; i < tasks.size(); i++) {
                saveString += tasks.getTask(i) + "\n";
            }
            saveWriter.write(saveString);
            saveWriter.flush();
            saveWriter.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Loads a ToDo object.
     *
     * @param isTaskDone was the task done
     * @param taskDesc the descriptor of the ToDo object
     * @return a ToDo object with the correct status
     * @since 0.1
     */
    private ToDo loadToDo(boolean isTaskDone, String taskDesc) {
        ToDo todo = new ToDo("todo " + taskDesc);
        if (isTaskDone) {
            todo.setDone();
        } else {
            todo.setUndone();
        }
        return todo;
    }

    /**
     * Loads a Deadline object.
     *
     * @param isTaskDone was the task done
     * @param taskDesc the descriptor of the Deadline object
     * @return a Deadline object with the correct status
     * @since 0.1
     */
    private Deadline loadDeadline(boolean isTaskDone, String taskDesc) {
        String[] deadlineSpecifics = taskDesc.split(" \\(by: ");
        Deadline deadline = new Deadline(deadlineSpecifics[0],
                deadlineSpecifics[1].substring(0, deadlineSpecifics[1].length() - 1));
        if (isTaskDone) {
            deadline.setDone();
        } else {
            deadline.setUndone();
        }
        return deadline;
    }

    /**
     * Loads an Event object.
     *
     * @param isTaskDone was the task done
     * @param taskDesc the descriptor of the Event object
     * @return an Event object with the correct status
     * @since 0.1
     */
    private Event loadEvent(boolean isTaskDone, String taskDesc) {
        String[] eventSpecifics = taskDesc.split(" \\(at: ");
        Event event = new Event(eventSpecifics[0], eventSpecifics[1].substring(0, eventSpecifics[1].length() - 1));
        if (isTaskDone) {
            event.setDone();
        } else {
            event.setUndone();
        }
        return event;
    }

    /**
     * Determines what task the input data is trying to reference, and calls the appropriate task loader method
     *
     * @param data String to parse for the appropriate method
     * @return a Task corresponding to the input data
     * @throws DukeException thrown if the task is not a recognised type
     * @since 0.1
     */
    private Task loadTaskDifferentiator(String data) throws DukeException {
        String taskType = data.substring(0, 3);
        boolean taskWasDone = data.startsWith("[X]", 4);
        String taskDesc = data.substring(8);
        switch (taskType) {
        case "[T]":
            return loadToDo(taskWasDone, taskDesc);
        case "[D]":
            return loadDeadline(taskWasDone, taskDesc);
        case "[E]":
            return loadEvent(taskWasDone, taskDesc);
        default:
            throw new DukeException("I seem to forgotten what tasks you have asked me to remember."
                    + "Do you know where I left my notes?");
        }
    }

    /**
     * Loads an ArrayList of tasks that Duke recorded in the previous session.
     * If there was no previous session, return an empty ArrayList of tasks.
     *
     * @return the ArrayList of tasks that Duke recorded in the previous session
     * @throws DukeException thrown by loadTaskDifferentiator
     * @throws IOException thrown if Scanner throws an IOException
     * @since 0.1
     */
    public ArrayList<Task> load() throws DukeException, IOException {
        File dataDir = new File(System.getProperty("user.dir") + "\\data");
        File savesDir = new File(System.getProperty("user.dir") + "\\data\\saves");
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }
        if (!savesDir.exists()) {
            savesDir.mkdir();
        }

        File save = new File(saveFilePath);
        if (save.exists()) {
            Scanner sc = new Scanner(save);
            ArrayList<Task> res = new ArrayList<>();
            while (sc.hasNextLine()) {
                String command = sc.nextLine();
                res.add(loadTaskDifferentiator(command));
            }
            sc.close();
            return res;
        } else {
            return new ArrayList<>();
        }
    }
}
