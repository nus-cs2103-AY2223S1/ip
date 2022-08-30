package duke.helper;

import java.io.FileWriter;
import java.io.IOException;

import duke.task.TaskList;

/**
 * Class to update .txt file
 */
public class FileWriting {
    /**
     * Method to update the taskList based on any changes
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

    /**
     * Method to clear all task in the .txt of the filePath
     *
     * @param filePath the path of the file to be cleared
     */
    public void clear(String filePath) {
        update(filePath, new TaskList());
    }
}
