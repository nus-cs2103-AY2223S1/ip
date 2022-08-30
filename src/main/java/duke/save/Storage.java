package duke.save;


import duke.TaskList;
import duke.events.Deadline;
import duke.events.Event;
import duke.events.Task;
import duke.events.Todo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/**
 * deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {

    private static final String DIRECTORY_NAME = "SavedData";
    private static final String FILE_NAME = "SavedData/SaveData.txt";
    private static final String CSV_LOCATION = "SavedData/Save.txt";

    /**
     * Takes in a list of items
     * and saves it to a binary file
     * @param saveItems is a list of savedItems
     */
        public static void save(List<Task> saveItems) {
        try {
            FileWriter csvWriter = new FileWriter(CSV_LOCATION, false);

            for (Task t: saveItems) {
                String s = t.savableString();
                csvWriter.write(s + "\n");
            }
            csvWriter.flush();
            csvWriter.close();


        } catch (FileNotFoundException e) {
            System.out.println("Cannot save, File not found");
        } catch (IOException e) {
            System.out.println("Cannot Initialize Stream");
        }

    }
    /**
     * Takes in a list of items
     * and saves it to a binary file
     * @return List<Task> saveItems
     * @throws FileNotFoundException if the file/path has not been created or initalized
     * @throws IOException if the file is unable to read for any other reason
     */
    public static List<Task> readItems() {
        //1) Try to create file and directory, if it does not already exist
        try {
            File directory = new File(DIRECTORY_NAME);
            directory.mkdir();
            //Make CSV
            File csvCreate = new File(CSV_LOCATION);
            boolean createNewCSV = csvCreate.createNewFile();
            if (createNewCSV) {
                System.out.println("CSV doesn't exist, creating one");
            }
            File f = new File(FILE_NAME);
            boolean createNewSaveFile = f.createNewFile();
            if (createNewSaveFile) {
                System.out.println("File doesn't exist, creating one");
            }
        } catch (IOException exp) {
            System.out.println("IOException occurred");
            exp.printStackTrace();

        }

        try {

            String line = "";
            BufferedReader bf = new BufferedReader(new FileReader(CSV_LOCATION));
            ArrayList<Task> readTaskList = new ArrayList<>();
            while ((line = bf.readLine()) != null) {
                String[] values = line.split("//");
                if (values[0].equals("T")) {
                    readTaskList.add(Todo.readTask(values));
                } else if (values[0].equals("E")) {
                    readTaskList.add(Event.readTask(values));
                } else {
                    readTaskList.add(Deadline.readTask(values));
                }
            }
            return readTaskList;






        } catch (FileNotFoundException e) {
            return new ArrayList<>(); //empty Task list for initial initialization
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }


    public static TaskList load() {
        List<Task> currItems = readItems();
        return new TaskList(currItems);

    }

}
