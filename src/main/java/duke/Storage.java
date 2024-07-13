package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the storage for the tasks to be saved locally. Handles the
 * loading of tasks from the file and saving tasks in the file
 */
public class Storage {
    private String filepath;
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Constructor for Storage Object.
     *
     * @param filepath path where the tasks are saved locally.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Adds the task to the <code>TaskList</code> from the saved file.
     *
     * @param taskname the description of the saved task.
     */
    protected void addToListfromFile(String taskname) {
        boolean bool = (taskname.charAt(4) == 'X' ? true : false);
        int num = (taskname.charAt(7) == '0') ? 0 : 1;
        if (taskname.charAt(1) == 'T') {
            Task newTask = new ToDo(taskname.substring(10), bool, num);
            tasks.add(newTask);
        } else if (taskname.charAt(1) == 'E') {
            String des = taskname.substring(10, taskname.indexOf("(") - 1);
            String at = taskname.substring(taskname.indexOf(":") + 2, taskname.indexOf(")"));
            Task newTask = new Event(des, bool, at, num);
            tasks.add(newTask);
        } else {
            String des = taskname.substring(10, taskname.indexOf("(") - 1);
            String by = taskname.substring(taskname.indexOf(":") + 2, taskname.indexOf(")"));
            Task newTask = new Deadline(des, bool, by, num);
            tasks.add(newTask);
        }
    }

    /**
     * Loads the tasks from the locally saved file to the Task List of the user.
     *
     * @param ui the ui to display confirmatory message once tasks are loaded.
     * @return a <code>TaskList</code> with the loaded tasks
     * @throws DukeException if no saved tasks are found.
     */
    protected ArrayList<Task> load(Ui ui) throws DukeException {
        if (Files.exists(Path.of(this.filepath))) {
            File taskList = new File(this.filepath);
            Scanner tasks = null;
            try {
                tasks = new Scanner(taskList);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            while (tasks.hasNext()) {
                addToListfromFile(tasks.nextLine());
            }
        } else {
            throw new DukeException("No saved tasks found");
        }
        return tasks;
    }


}
