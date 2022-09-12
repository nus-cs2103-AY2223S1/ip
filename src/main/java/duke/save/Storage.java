package duke.save;


import duke.TaskList;
import duke.events.Deadline;
import duke.events.Event;
import duke.events.Task;
import duke.events.Todo;
import duke.exceptions.InvalidReadTaskException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/**
 * Deals with loading tasks from the file and saving tasks in a text file.
 */
public class Storage {

    private static final String CSV_LOCATION = "SavedData/Save.txt";

    /**
     * Takes in a list of items and saves each item
     * line by line into the text file.
     * Note: The text file is human-editable
     * @param saveItems a list of saved items
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
     * Reads items line by line from a text file
     * And saves it into a List to be used by the program
     * @return List<Task> saveItems
     * @throws FileNotFoundException if the file/path has not been created or initialized
     * @throws IOException if the file is unable to read for any other reason
     */

    public static List<Task> readItems() {
        //1) Try to create file and directory, if it does not already exist
        try {
            //Attempt to make CSV in appropriate location
            File csvFile = new File(CSV_LOCATION);
            boolean isCreatedSuccessfully = csvFile.createNewFile();
            if (isCreatedSuccessfully) {
                System.out.println("CSV directory doesn't exist, creating one");
            }
        } catch (IOException exp) {
            System.out.println("IOException occurred");
            exp.printStackTrace();

        }

        try {

            String currentLine = "";
            BufferedReader reader = new BufferedReader(new FileReader(CSV_LOCATION));
            ArrayList<Task> readTasksList = new ArrayList<>();
            int taskIndex = 1;
            while ((currentLine = reader.readLine()) != null) {
                String[] readValues = currentLine.split("//");
                String taskType = readValues[0];
                if (taskType.equals("T")) {
                    readTasksList.add(Todo.readTask(readValues));
                } else if (taskType.equals("E")) {
                    readTasksList.add(Event.readTask(readValues));
                } else if (taskType.equals("D")) {
                    readTasksList.add(Deadline.readTask(readValues));
                } else {
                    Exception invalidReadTask = new InvalidReadTaskException("Failed to read task at index: " + taskIndex);
                    System.out.println(invalidReadTask);
                }
                taskIndex += 1;
            }
            return readTasksList;






        } catch (FileNotFoundException e) {
            return new ArrayList<>(); //empty Task list for initial initialization
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Helper method called by main function to wrap List into custom
     * TaskList with functionalities
     * @return A Tasklist with items loaded from file
     */
    public static TaskList load() {
        List<Task> currItems = readItems();
        return new TaskList(currItems);

    }

}
