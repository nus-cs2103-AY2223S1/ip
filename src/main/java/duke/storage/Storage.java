package duke.storage;

import duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with loading tasks from the hard disk and saving tasks in the hard disk.
 *
 * @author ish1506
 */
public class Storage {

    private static final String DIRECTORY = "data";
    private static final String PATH_NAME = "data/duke.txt";

    /**
     * Reads <code>Task</code> data from hard disk.
     * If there's no existing directory or file, a new directory and/or file is created.
     *
     * @return  an ArrayList containing the Tasks.
     */
    public static ArrayList<Task> readData() {
        try {
            File dir = new File(DIRECTORY);
            dir.mkdir();
            File dataFile = new File(PATH_NAME);
            dataFile.createNewFile();

            // dataFile is used as the input source of the Scanner
            Scanner scanner = new Scanner(dataFile);
            ArrayList<Task> tasksList = new ArrayList<>();
            while (scanner.hasNext()) {
                String input = scanner.nextLine();
                tasksList.add(Task.fromString(input));
            }
            return tasksList;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * Writes <code>Task</code> data from the <code>Duke</code> chatbot to hard disk.
     * If there's no existing directory or file, a new directory and/or file is created.
     *
     * @param data The <code>Task</code> data to be written to hard disk.
     */
    public static void writeData(String data) {
        try {
            FileWriter fileWriter = new FileWriter(PATH_NAME);
            fileWriter.write(data);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
