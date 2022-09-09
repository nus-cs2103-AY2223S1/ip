package duke;

import duke.command.Command;
import duke.task.TaskList;

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
    private final String taskFilePath;
    private final String clientFilePath;

    /**
     * Constructor for Storage class.
     *
     * @param folderPath The filepath to the data folder.
     * @param taskFilePath The filepath to the text file containing the task list.
     */
    public Storage(String folderPath, String taskFilePath, String clientFilePath) {
        this.folderPath = folderPath;
        this.taskFilePath = taskFilePath;
        this.clientFilePath = clientFilePath;
    }

    /**
     * Saves tasks in task list file.
     *
     * @param taskList list of tasks.
     */
    public void saveTaskList(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(taskFilePath);
            fw.write(taskList.toSaveString());
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveClientList(ClientList clientList) {
        try {
            FileWriter fw = new FileWriter(clientFilePath);
            fw.write(clientList.toSaveString());
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
     * @param taskList list of tasks.
     */
    public void startUpPullStorage(TaskList taskList, ClientList clientList) {
        createFolder();
        createFile();
        pullSavedInformation(taskList, clientList);
    }

    private void createFolder() {
        new File(folderPath).mkdir();
    }

    private void createFile() {
        try {
            new File(taskFilePath).createNewFile();
            new File(clientFilePath).createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Pulls from task list file and inputs
     * the tasks into application's task list.
     *
     * @param taskList list of tasks.
     */
    private void pullSavedInformation(TaskList taskList, ClientList clientList) {
        File savedTasks = new File(taskFilePath);
        File savedClients = new File(clientFilePath);
        try {
            Scanner scanner = new Scanner(savedTasks);
            while (scanner.hasNextLine()) {
                String nextInput = scanner.nextLine();
                Command c = Parser.parseSavedTaskList(nextInput);
                c.execute(taskList, this, clientList);
            }
            scanner = new Scanner(savedClients);
            while (scanner.hasNextLine()) {
                String nextInput = scanner.nextLine();
                Command c = Parser.parseSavedClientList(nextInput);
                c.execute(taskList, this, clientList);
            }
        } catch (FileNotFoundException | DukeException e) {
            throw new RuntimeException(e);
        }
    }
}
