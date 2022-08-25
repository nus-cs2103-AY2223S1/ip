package drivers;

import exceptions.TumuException;
import tasks.Task;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file
 * and saving tasks in the file.
 */
public class Storage {
    private static final String FOLDER_PATH = "data";
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> loadData() {
        File file = getFile();
        List<Task> userTasks = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            //Each line in the file is an individual task.
            while (sc.hasNextLine()) {
                String taskStr = sc.nextLine();
                //Figure out what type of command this is, add back to userTasks.
                Task task = new TaskFormatter(taskStr).getTask();
                userTasks.add(task);
            }
            sc.close();
        } catch (TumuException e) {
            System.out.println(e);
        } finally {
            return userTasks;
        }
    }

    public void saveData(List<Task> userTasks) {
        try {
            getFile();
            FileWriter fileWriter = new FileWriter(filePath);

            //Add contents to file.
            for (Task task : userTasks) {
                fileWriter.write(task.parseToFile() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("\tAn I/O error has occurred.");
            e.printStackTrace();
        }
    }

    private File getFile() {
        /**
         * Checks for the file and return it.
         * If the file or folder it is contained in does not exist,
         * then make the folder and the file.
         */

        File folder = new File(FOLDER_PATH);
        if (!folder.exists()) {
            folder.mkdir();
        }

        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return file;
        }
    }
}