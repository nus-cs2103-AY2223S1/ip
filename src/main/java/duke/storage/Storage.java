package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Encapsulates storage to save the task list to
 * the hard drive and load the task list from the hard drive.
 */
public class Storage {
    private static final File DATA_DIRECTORY = new File("data/");
    private static final File LIST_FILE = new File("data/list.txt");
    private static ArrayList<String[]> infoList;

    /**
     * Loads the task list from a file in the hard drive. If the
     * required directory or file cannot be made, an empty TaskList is created.
     *
     * @return A list containing information about each task to be saved in TaskList.
     * @throws IOException If the directory or file cannot be created.
     */

    private static void createDirectoryIfNotExists() {
        if (!DATA_DIRECTORY.exists()) {
            DATA_DIRECTORY.mkdir();
        }
    }

    private static void createFile() throws IOException {
        createDirectoryIfNotExists();
        LIST_FILE.createNewFile();
    }

    /**
     * Loads information from a file on the hard drive, if any,
     * into Duke.
     *
     * @return A list containing the tasks found on the file, if any.
     * @throws IOException if there was an error creating the file or data directory.
     */
    public ArrayList<String[]> load() throws IOException {
        infoList = new ArrayList<>(100);
        //create a file to store the task list if it does not exist
        if (!LIST_FILE.exists()) {
            createFile();
            return infoList;
        }

        //read contents from file containing the task list
        assert LIST_FILE.exists() : "File not created and exception not thrown.";
        Scanner listSc = new Scanner(LIST_FILE);
        while (listSc.hasNext()) {
            String[] info = listSc.nextLine().split(" \\| ");
            infoList.add(info);
        }
        return infoList;

    }

    /**
     * Saves the contents of TaskList to the hard drive.
     *
     * @param taskDescriptions An array containing information about each task.
     * @throws IOException If the directory or list file cannot be found.
     */
    public void save(String[] taskDescriptions) throws IOException {
        FileWriter fw = new FileWriter("data/list.txt");
        for (String task : taskDescriptions) {
            fw.write(task);
        }
        fw.close();
    }
}
