package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.task.Task;


/**
 * Represents a storage for the task list, provides save/load task list methods.
 * Task list is saved in / loaded from specified file path.
 */
public class Storage {
    private String filepath;
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Saves the task list in the location indicated by filepath as a txt file.
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
     * @return task list.
     */
    public TaskList load() {
        int i = filepath.lastIndexOf('/');
        File dir = new File(filepath.substring(0, i));
        if (!dir.exists()) {
            dir.mkdirs();
        }
        TaskList l = new TaskList();
        try {
            File f = new File(filepath);
            Scanner s = new Scanner(f);
            s.nextLine(); //skip header line
            while (s.hasNext()) {
                l.addTask(Task.stringToTask(s.nextLine()));
            }
            return l;
        } catch (Exception e) {
            new Ui().showLoadingError();
            return new TaskList();
        }
    }
}

