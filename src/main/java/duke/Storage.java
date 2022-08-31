package duke;

import duke.command.CommandOutputs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the file used to store list of tasks
 */
public class Storage {
    private final String folderPath;
    private final String filePath;

    /**
     * Constructor for Storage class.
     *
     * @param folderPath The filepath to the data folder.
     * @param filePath The filepath to the text file containing the task list.
     */
    public Storage(String folderPath, String filePath) {
        this.folderPath = folderPath;
        this.filePath = filePath;
    }

    /**
     * Saves tasks in task list file.
     *
     * @param taskList list of tasks.
     */
    public void saveTaskList(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < taskList.size(); i++) {
                fw.write(taskList.get(i).toSaveString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Takes tasks from task list file, creates folder
     * and file if it does not exist.
     * Inputs the tasks into application's task list.
     *
     * @param commandOutputs user interface of application.
     * @param taskList list of tasks.
     */
    public void startUpPullStorage(CommandOutputs commandOutputs, TaskList taskList) {
        createFolder();
        createFile();
        pullSavedTaskList(commandOutputs, taskList);
    }

    private void createFolder() {
        new File(folderPath).mkdir();
    }

    private void createFile() {
        try {
            new File(filePath).createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Pulls from task list file and inputs
     * the tasks into application's task list.
     *
     * @param commandOutputs user interface of application.
     * @param taskList list of tasks.
     */
    private void pullSavedTaskList(CommandOutputs commandOutputs, TaskList taskList) {
        File save = new File(filePath);
        try {
            Scanner s = new Scanner(save);
            while (s.hasNextLine()) {
                Parser.parseSavedInput(s.nextLine()).execute(taskList, commandOutputs, this);
            }
        } catch (FileNotFoundException | DukeException e) {
            throw new RuntimeException(e);
        }
    }
}
