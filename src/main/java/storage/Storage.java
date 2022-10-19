package storage;

import duke.DukeException;

import java.io.File;
import java.io.IOException;

import java.nio.file.Path;

import java.util.ArrayList;
import java.util.Scanner;

import task.Deadline;
import task.Event;
import task.TaskList;
import task.Task;
import task.Todo;


/**
 * Storage class that stores the TaskList of tasks.
 */
public class Storage {

    protected String filePath;

    protected File file;


    /**
     * Creates a new Storage.
     * @param path The path of the Text file to store the Tasks.
     */
    public Storage(String path) {
        this.filePath = path;
        this.file = Path.of(path).toFile();
    }


    /**
     * Loads the TaskList from Text file.
     * @return The ArrayList that is converted from the Text file.
     * @throws DukeException e
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> arr = new ArrayList<>(100);
        TaskList taskList = new TaskList(arr);
        int currentAction = 0;
        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                currentAction = currentAction + 1;
                String data = myReader.nextLine();
                checkTask(data, taskList);
            }
        } catch (IOException | StringIndexOutOfBoundsException e) {
            throw new DukeException("");
        }
        return arr;
    }

    /**
     * Returns the string format of text from text file.
     * @return The string format of text from text file.
     */
    public String printOutContent() {
        StringBuilder out = new StringBuilder();
        try {
            File task = new File(filePath);
            task.getParentFile().mkdirs();
            if (!task.createNewFile()) {
                Scanner myReader = new Scanner(file);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    out.append("\n").append(data);
                }
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("STRINGOUTOFBOUNDS");
        } catch (IOException e) {
            throw new DukeException("IOEXECPT");
        }
        return out.toString();
    }

    /**
     * Checks what type of tasks the Task is and also if the Task is Marked.
     * @param str The String representation of the Task.
     * @param arr The TaskList to add the task to.
     * @throws StringIndexOutOfBoundsException e
     */
    private static void checkTask(String str, TaskList arr) throws StringIndexOutOfBoundsException {
        //check what task
        String task = Character.toString(str.charAt(1));
        String done = Character.toString(str.charAt(4));
        boolean isDone = !done.equals(" ");

        if (isTodo(task)) {
            if (isDone) {
                addTaskWithMark(new Todo(str.substring(7)), arr);
            } else {
                addTaskWithoutMark(new Todo(str.substring(7)), arr);
            }
        }

        else if (isEvent(task)) {
            int pos = str.indexOf("(") - 1;
            String at = str.substring(pos + 6, str.length() - 1);
            if (isDone) {
                addTaskWithMark(new Event(str.substring(7, pos), at), arr);
            } else {
                addTaskWithoutMark(new Event(str.substring(7, pos), at), arr);
            }
        }

        else if (isDeadline(task)) {
            int pos = str.indexOf("(") - 1;
            String by = str.substring(pos + 6, str.length() - 1);
            if (isDone) {
                addTaskWithMark(new Deadline(str.substring(7, pos), by), arr);
            } else {
                Deadline dl = new Deadline(str.substring(7, pos), by);
                addTaskWithoutMark(dl, arr);
            }
        }
    }

    /**
     * Check if is a Todo Task.
     * @param task The String representation of the task.
     * @return True if is a Todo Task.
     */
    private static Boolean isTodo(String task) {
        return task.equals("T");
    }

    /**
     * Check if is an Event Task.
     * @param task The String representation of the task.
     * @return True if is an Event Task.
     */
    private static Boolean isEvent(String task) {
        return task.equals("E");
    }

    /**
     * Check if is a Deadline Task.
     * @param task The String representation of the task.
     * @return True if is a DeadLine Task.
     */
    private static Boolean isDeadline(String task) {
        return task.equals("D");
    }

    /**
     * Adds a task to the TaskList without marking the Task.
     * @param task Task to be added to TaskList.
     * @param arr The TaskList to add the Task.
     */
    private static void addTaskWithoutMark(Task task, TaskList arr) {
        arr.addStart(task);
    }

    /**
     * Adds a task to the TaskList after marking the Task.
     * @param task Task to be added to TaskList.
     * @param arr The TaskList to add the Task.
     */
    private static void addTaskWithMark(Task task, TaskList arr) {
        task.mark();
        arr.addStart(task);
    }



}


