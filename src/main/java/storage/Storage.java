package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import exceptions.DukeException;
import tasklist.Task;
import tasklist.TaskList;

public class Storage {

    private static Storage storage;

    public static Storage getInstance() {
        if (storage == null) {
            storage = new Storage();
        }
        return storage;
    }



    public void readSavedTasks(){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.
                    ofPattern("yyyy-MM-dd HHmm");
            File dir = new File("data/");
            if (!dir.exists()) {
                boolean makeDir = dir.mkdir();
            }
            File temp = new File("data/duke.txt");
            if (!temp.exists()) {
                boolean makeFile = temp.createNewFile();
            }

            Scanner in = new Scanner(temp);
            while (in.hasNext()) {
                String curr = in.nextLine();
                TaskList list = TaskList.getInstance();
                list.addTasksFromSave(curr);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeToSavedFile() {
        try {
            FileWriter writer = new FileWriter("data/duke.txt");
            ArrayList<Task> storage = TaskList.getInstance().getTaskList();
            for (Task x : storage) {
                writer.write(x.savedFileFormat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
