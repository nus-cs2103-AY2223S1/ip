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
    private static final String FOLDER_PATH = "data";
    private static final String TASK_FILE_PATH = "data/Tasks.txt";
    private static final String CLIENT_FILE_PATH = "data/Clients.txt";

    /**
     * Saves tasks in task list file.
     *
     * @param taskList list of tasks.
     */
    public static void saveTaskList(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(TASK_FILE_PATH);
            fw.write(taskList.toSaveString());
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Saves clients in client list file.
     *
     * @param clientList list of clients.
     */
    public static void saveClientList(ClientList clientList) {
        try {
            FileWriter fw = new FileWriter(CLIENT_FILE_PATH);
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
    public static void startUpPullStorage(TaskList taskList, ClientList clientList) {
        createFolder();
        createFile();
        pullSavedInformation(taskList, clientList);
    }

    private static void createFolder() {
        new File(FOLDER_PATH).mkdir();
    }

    private static void createFile() {
        try {
            new File(TASK_FILE_PATH).createNewFile();
            new File(CLIENT_FILE_PATH).createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Pulls from task and client list file and inputs
     * the tasks and clients into application's task and client list.
     *
     * @param taskList list of tasks.
     */
    private static void pullSavedInformation(TaskList taskList, ClientList clientList) {
        pullSavedTasks(taskList, clientList);
        pullSavedClients(taskList, clientList);
    }

    private static void pullSavedTasks(TaskList taskList, ClientList clientList) {
        File savedTasks = new File(TASK_FILE_PATH);
        try {
            Scanner scanner = new Scanner(savedTasks);
            while (scanner.hasNextLine()) {
                String nextInput = scanner.nextLine();
                Command c = Parser.parseSavedTaskList(nextInput);
                c.execute(taskList, clientList);
            }
        } catch (FileNotFoundException | DukeException e) {
            throw new RuntimeException(e);
        }
    }

    private static void pullSavedClients(TaskList taskList, ClientList clientList) {
        File savedClients = new File(CLIENT_FILE_PATH);
        try {
            Scanner scanner = new Scanner(savedClients);
            while (scanner.hasNextLine()) {
                String nextInput = scanner.nextLine();
                Command c = Parser.parseSavedClientList(nextInput);
                c.execute(taskList, clientList);
            }
        } catch (FileNotFoundException | DukeException e) {
            throw new RuntimeException(e);
        }
    }

}
