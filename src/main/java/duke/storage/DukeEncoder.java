package duke.storage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import duke.logic.task.Task;

/**
 * Class to update list after each command.
 */
public class DukeEncoder {
    /**
     * Updates the change to List.txt.
     * @param workList ArrayList of Task used to store Task in the program.
     */
    public static void rewriteList(ArrayList<Task> workList) {
        try {
            File directory = new File("./data");
            FileWriter fileWriter = new FileWriter(new File(directory, "List.txt"));
            fileWriter.write(convertListToString(workList));
            fileWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("Cannot save the list!");
        }
    }

    /**
     * Convert ArrayList of Task into String in the List.txt file.
     * @param workList
     * @return String
     */
    private static String convertListToString(ArrayList<Task> workList) {
        String stringToAdd = "";
        for (int i = 0; i < workList.size(); i++) {
            stringToAdd += workList.get(i).storedData() + "\n";
        }
        return stringToAdd;
    }
}
