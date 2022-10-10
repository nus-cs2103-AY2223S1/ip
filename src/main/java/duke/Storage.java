package duke;

import duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


/**
 * Represents a storage for the task list, provides save/load task list methods.
 * Task list is saved in / loaded from specified file path.
 */
public class Storage {
    private String filepath;

    /**
     * This method is Storage object constructor that creates task list storage.
     *
     * @param filepath path to the file that stores the task list
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Saves the task list in the location indicated by filepath as a txt file.
     * The task list store the status (done/undone), task type, description, date and tag information of each task.
     *
     * @param taskList list of tasks.
     */
    public void save(TaskList taskList) {
        int i = filepath.lastIndexOf('/');
        File dir = new File(filepath.substring(0, i));
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            File f = new File(filepath);
            if (!f.exists()) {
                f.createNewFile();
            }
            FileWriter fw = new FileWriter(filepath);
            fw.write(taskList.saveTaskList());
            fw.close();
        } catch (IOException e) {
            new Ui().showSavingError();
        }
    }

    /**
     * Loads the task list from the location indicated by filepath.
     * If the filepath doesn't exist, empty task list is loaded.
     *
     * @return task list in the storage.
     */
    public TaskList load() {
        int i = filepath.lastIndexOf('/');
        assert i >= 0 : "Incorrect filepath.";
        File dir = new File(filepath.substring(0, i));
        if (!dir.exists()) {
            dir.mkdirs();
        } //create file directory if the directory does not exist
        TaskList l = new TaskList();
        try {
            File f = new File(filepath);
            Scanner s = new Scanner(f);
            s.nextLine(); //skip header line
            while (s.hasNext()) {
                l.addTask(Task.stringToTask(s.nextLine()));
            } //convert text String to tasks
            return l;
        } catch (Exception e) {
            new Ui().showLoadingError();
            return new TaskList();
        }
    }
}

