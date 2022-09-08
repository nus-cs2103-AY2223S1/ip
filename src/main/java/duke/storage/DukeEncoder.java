package duke.storage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import duke.logic.task.Task;

/**
 * Class to update list after each command.
 */
public class DukeEncoder {
    /**
     * Updates the change to List.txt.
     * @param workList ArrayList of Task used to store Task in the program
     */
    public static void rewriteList(ArrayList<Task> workList) {
        try {
            File directory = new File("./data");
            FileWriter fileWriter = new FileWriter(new File(directory, "List.txt"));
            String stringToAdd = "";
            for (int i = 0; i < workList.size(); i++) {
                stringToAdd += workList.get(i).storedData() + "\n";
            }
            fileWriter.write(stringToAdd);
            fileWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("Cannot save the list!");
        }
    }
}
