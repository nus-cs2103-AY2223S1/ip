package duke;

import tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    /**
     * The file path of the text file that stores the date
     */
    private final String filePath;

    /**
     * A constructor that initializes the storage object with the filepath
     *
     * @param filePath the file path of the text file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Method that updates the textfile with the latest tasklist
     *
     * @param lst latest tasklist
     */
    public void updateFile(TaskList lst) throws IOException {
        File file = new File(this.filePath);
        FileWriter writer = new FileWriter(file);
        for (Task task: lst.lst) {
            writer.write(task.toFileString() + "\n");
        }
        writer.close();
    }

    /**
     * Method that loads an arraylist with the task based on the data read
     *
     * @return ArrayList<Task> arraylist containing tasks from the text file
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> lst = new ArrayList<>();
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            new File(filePath).getParentFile().mkdirs();
            try {
                new File(filePath).createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else if (!file.exists()) {
            try {
                new File(filePath).createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            lst = TextConverter.textToTask(sc.nextLine(), lst);
        }
        sc.close();
        return lst;
    }
}
