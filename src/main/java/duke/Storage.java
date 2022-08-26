package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructor for Storage
     *
     * @param filePath
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the list of strings in taskList to the file.
     *
     * @param taskList List of string to be saved.
     */
    public void saveList(ArrayList<String> taskList) {
        try {
            new File(this.filePath).getParentFile().mkdirs();
            FileWriter fileWriter = new FileWriter(this.filePath);

            for (String task : taskList) {
                fileWriter.write(task);
                fileWriter.write(System.lineSeparator());
            }

            fileWriter.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads an ArrayList of strings from a save file.
     *
     * @return ArrayList of strings in the save file, separated by line.
     * @throws FileNotFoundException
     */
    public ArrayList<String> load() throws FileNotFoundException {
        ArrayList<String> taskList = new ArrayList<>();
        File f = new File(this.filePath);
        Scanner sc = new Scanner(f);

        while (sc.hasNext()) {
            taskList.add(sc.nextLine());
        }
        sc.close();

        return taskList;
    }
}

