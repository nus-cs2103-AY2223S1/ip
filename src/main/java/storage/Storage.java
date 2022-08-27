package storage;

import duke.DukeException;
import task.Event;
import task.Task;
import task.Todo;
import task.TaskList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

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
        } catch (IOException e) {
            throw new DukeException("");
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("");
        }
        size = currentAction;
        return arr;
    }

    private static void checkTask(String str, TaskList arr) throws StringIndexOutOfBoundsException {
        //check what task
        String t = Character.toString(str.charAt(1));
        String done = Character.toString(str.charAt(4));
        Boolean d = !done.equals(" ");
        //If td

        if (t.equals("T")) {
            //check done
            if (d) {
                Todo add = new Todo(str.substring(7));
                add.mark();
                arr.addStart(add);
            } else {
                Todo add = new Todo(str.substring(7));
                arr.addStart(add);
            }
        }
        //If event
        else if (t.equals("E")) {
            int pos = str.indexOf("(") - 1;
            if (d) {
                Event add = new Event(str.substring(7, pos), str.substring(pos + 5, -1));
                add.mark();
                arr.addStart(add);
            } else {
                Event add = new Event(str.substring(7, pos), str.substring(pos + 5, -1));
                arr.addStart(add);
            }
        }
        //if task.Deadline
        else if (t.equals("D")) {
            int pos = str.indexOf("(") - 1;
            if (d) {
                Event add = new Event(str.substring(7, pos), str.substring(pos + 5, -1));
                add.mark();
                arr.addStart(add);
            } else {
                Event add = new Event(str.substring(7, pos), str.substring(pos + 5, -1));
                arr.addStart(add);
            }
        }
    }

    public int getSize() {
        return size;
    }

}
