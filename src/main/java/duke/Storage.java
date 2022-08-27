package duke;

import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String filepath;
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public void save(TaskList taskList) {
        int i = filepath.lastIndexOf('/');
        File dir = new File(filepath.substring(0,i));
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

    public TaskList load() {
        int i = filepath.lastIndexOf('/');
        File dir = new File(filepath.substring(0,i));
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

