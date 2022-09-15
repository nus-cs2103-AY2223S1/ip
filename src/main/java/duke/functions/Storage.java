package duke.functions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import duke.tasks.Task;

/**
 * Class which handles storage of task lists created by users.
 *
 * @author lauralee
 */
public class Storage {

    /**
     * Constructor for storage class which initiates file saving process to store user input.
     *
     * @param taskList
     * @param filePath
     */
    public Storage(TaskList taskList, String filePath) {
        saveFile(taskList, filePath);
    }

    //Solution below is adapted from
    // https://nus-cs2103-ay2223s1.github.io/website/schedule/week3/topics.html#w3-4-java-javadoc-file-i-o-packages-jars
    /**
     * Adds tasks from tasks array in Duke.main into a file.
     * A new file will be created if no existing files with the specified pathname
     * exists currently.
     *
     * @param taskList A Task array which contains the list of tasks inputted by the user.
     * @param pathname The path which the user wants to store their file containing their lists
     *                 of task in.
     */
    public static void saveFile(TaskList taskList, String pathname) {

        try {
            Task[] taskArr = taskList.getTaskArr();

            File dukeFile = new File(pathname);
            dukeFile.createNewFile();

            FileWriter fileWriter = new FileWriter(pathname);
            for (int i = 1; i <= Task.getNumberTasks(); i++) {
                fileWriter.write(taskArr[i].output() + "\n");
            }
            fileWriter.close();
        } catch (IOException ioException) {
            Ui.printFileSavingError();
        }

    }

}
