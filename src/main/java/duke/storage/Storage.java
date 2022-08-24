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
    private File data = new File("data/");
    private File list = new File("data/list.txt");
    private ArrayList<String[]> infoList;

    /**
     * Loads the task list from a file in the hard drive. If the
     * required directory or file cannot be made, an empty TaskList is created.
     *
     * @return A list containing information about each task to be saved in TaskList.
     * @throws IOException If the directory or file cannot be created.
     */
    public ArrayList<String[]> load() throws IOException {
        infoList = new ArrayList<>(100);

        //create a file to store the task list if it does not exist
        if (!list.exists()) {
            if (!data.exists()) {
                data.mkdir();
            }
            list.createNewFile();
            return infoList;
        }

        //read contents from file containing the task list
        Scanner listSc = new Scanner(list);
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
     * @throws IOException If the directory or data file cannot be found.
     */
    public void save(String[] taskDescriptions) throws IOException {
        FileWriter fw = new FileWriter("data/list.txt");
        for (String task : taskDescriptions) {
            fw.write(task);
        }
        fw.close();
    }
}
