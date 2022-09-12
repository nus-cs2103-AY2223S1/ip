package storage;

import duke.DukeException;

import java.io.File;
import java.io.IOException;

import java.nio.file.Path;

import java.util.ArrayList;
import java.util.Scanner;

import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;




public class Storage {

    private int size = 0;

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
     * @throws DukeException
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> arr = new ArrayList<Task>(100);
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
        size = currentAction;
        return arr;
    }

    public String printOutContent() {
        String out = "";
        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                out = out + "\n" + data;
            }
        } catch (IOException | StringIndexOutOfBoundsException e) {
            throw new DukeException("");
        }
        return out;
    }

    private static void checkTask(String str, TaskList arr) throws StringIndexOutOfBoundsException {
        System.out.println("checking task");
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
            if (isDone) {
                addTaskWithMark(new Event(str.substring(7, pos), str.substring(pos + 5, -1)), arr);
            } else {
                addTaskWithoutMark(new Event(str.substring(7, pos), str.substring(pos + 5, -1)), arr);
            }
        }

        else if (isDeadline(task)) {
            int pos = str.indexOf("(") - 1;
            if (isDone) {
                addTaskWithMark(new Event(str.substring(7, pos), str.substring(pos + 5, -1)), arr);
            } else {
                addTaskWithoutMark(new Event(str.substring(7, pos), str.substring(pos + 5, -1)), arr);
            }
        }
    }

    private static Boolean isTodo(String task) {
        return task.equals("T");
    }

    private static Boolean isEvent(String task) {
        return task.equals("E");
    }

    private static Boolean isDeadline(String task) {
        return task.equals("D");
    }

    private static void addTaskWithoutMark(Task task, TaskList arr) {
        arr.addStart(task);
    }

    private static void addTaskWithMark(Task task, TaskList arr) {
        task.mark();
        arr.addStart(task);
    }



}


