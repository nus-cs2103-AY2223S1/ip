package duke.helper;

import java.io.FileWriter;
import java.io.IOException;

import duke.task.TaskList;

/**
 * Encapsulates a method that updates .txt file given
 */
public class FileWriting {
    /**
     * Updates the taskList based on any changes
     *
     * @param filePath the path of the file to be updated
     * @param list the taskList to be updated
     */
    public static void update(String filePath, TaskList list) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(list.getTasks());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
