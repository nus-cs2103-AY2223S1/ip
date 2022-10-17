package duke.functions.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.functions.TaskList;
import duke.functions.Ui;
import duke.tasks.Task;

/**
 * Class which handles storage of task lists created by users.
 *
 * @author lauralee
 */
public class Storage {

    private Scanner scanner;
    private String filePath;
    private TaskList taskList;

    /**
     * Constructor for storage class which initiates file saving process to store user input.
     *
     * @param taskList
     * @param filePath
     */
    public Storage(TaskList taskList, String filePath) {
        this.taskList = taskList;
        this.filePath = filePath;
        try {
            File dukeFile = new File(this.filePath);
            dukeFile.createNewFile();
            scanner = new Scanner(dukeFile);
        } catch (IOException ioException) {
            Ui.printFileSavingError();
        }
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
    public void saveFile(TaskList taskList, String pathname) {
        Task[] taskArr = taskList.getTaskArr();
        try {
            FileWriter fileWriter = new FileWriter(pathname);
            for (int i = 1; i <= Task.getNumberTasks(); i++) {
                fileWriter.write(taskArr[i].output() + "\n");
            }
            fileWriter.close();
        } catch (IOException ioException) {
            Ui.printFileSavingError();
        }
    }

    /**
     * Loads Tasks from a previous TaskList saved in storage at the specified filepath
     * into the Duke Bot's TaskList.
     *
     * @param taskList Duke's TaskList.
     * @return The TaskList with the Tasks from the previous TaskList created and stored
     *         at the specified file location.
     */
    public TaskList loadFile(TaskList taskList) {
        return taskList.addFromStorage(scanner);
    }



}
