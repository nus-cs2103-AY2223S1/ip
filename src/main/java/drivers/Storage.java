package drivers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import exceptions.TumuException;
import tasks.Task;

/**
 * Deal with loading tasks from the file
 * and saving tasks in the file.
 */
public class Storage {
    private static final String FOLDER_PATH = "data";
    private final String filePath;

    /**
     * Constructor for the Storage class.
     * @param filePath Indicate file path to local txt file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the data from the local txt file into the program
     * TaskList.
     * @return A list of the tasks stored within the local txt file.
     */
    public List<Task> loadData(Ui ui) {
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
            ui.notifyUser(e.toString());
        } catch (FileNotFoundException e) {
            ui.notifyUser("No save file is found!");
        }
        return userTasks;
    }

    /**
     * Saves the data from the program TaskList into the local
     * txt file.
     *
     * @param userTasks A list of the tasks stored within the local txt file.
     */
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

    /**
     * Checks for the file and return it.
     * If the file or folder it is contained in does not exist,
     * then make the folder and the file.
     * @return Local txt file to store and load task information.
     */
    private File getFile() {
        File folder = new File(FOLDER_PATH);
        if (!folder.exists()) {
            folder.mkdir();
        }

        File file = new File(filePath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }
}
