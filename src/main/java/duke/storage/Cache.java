package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import duke.dukeexception.DukeException;
import duke.dukeexception.TypeNotExistException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.ui.Ui;

/**
 * A class represents a duke.Storage.
 */
public class Cache {
    private final String filePath;

    /**
     * Constructs a duke.Storage.
     * @param path The file path specified by the duke.Duke bot.
     */
    public Cache(String path) {
        filePath = path;
    }

    /**
     * Alerts the user that there are some previously saved work and recover,
     * otherwise create a new file for future cache.
     * @return A recovered list of previous work or a blank list if no cache.
     * @throws DukeException If IOException occurs during the process.
     */
    public TaskList printPath() throws DukeException, IOException {
        try {
            // Create path if not exist
            File file = new File(System.getProperty("user.dir") + "/data");
            if (!file.exists()) {
                Path path = Paths.get(System.getProperty("user.dir") + "/data");
                Files.createDirectories(path);
                assert (file.exists());
            }

            // Create file if not exist
            file = new File(filePath);
            if (file.exists()) {
                return Ui.loading(file);
            } else {
                file.createNewFile();
                assert (file.exists()) : "The input file has not been created. Please check!";
                return new TaskList();
            }
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
    /**
     * Returns an ArrayList of previously recorded tasks.
     * @return A recovered list of previous work or a blank list if no cache.
     * @throws DukeException If IOException occurs during the process.
     */
    public static TaskList recover(File f) throws DukeException, FileNotFoundException {
        ArrayList<Task> taskList = new ArrayList<>();
        String[] commands;
        String type;
        String description;
        boolean isDone;
        Scanner sc;
        try {
            sc = new Scanner(f);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                commands = line.split(" \\| ", 3);
                type = commands[0].trim();
                isDone = commands[1].trim().equals("1") ? true : false;
                description = commands[2].trim();

                // sync task using ui.Ui functions
                if (type.equals("T")) {
                    taskList.add(Ui.syncToDo(description, isDone));
                } else if (type.equals("D")) {
                    taskList.add(Ui.syncDeadline(description, isDone));
                } else if (type.equals("E")) {
                    taskList.add(Ui.syncEvent(description, isDone));
                } else {
                    throw new TypeNotExistException("");
                }

            }

            return new TaskList(taskList);
        } catch (FileNotFoundException e) {
            System.out.println(":( OOPS!!! Cannot be read cos the file doesn't exist");
            return null;
        }
    }

    /**
     * Updates the records in cache file once for all before closing duke.Duke.
     * @param taskList A duke.Tasks.Task list to be recorded.
     */
    public void update(TaskList taskList) throws DukeException {
        try {
            FileWriter writer = new FileWriter(this.filePath);
            StringBuilder builder = new StringBuilder();
            for (Task task : taskList.getList()) {
                builder.append(task.recordString());
                builder.append("\n");
            }
            writer.write(builder.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println(":( OOPS!!! Cannot be written cos the file doesn't exist");
        }
    }

    /**
     * Express the list of tasks using a String.
     * @param taskList The task list needed to be saved.
     * @return String that contains all the information of task list.
     * @throws DukeException
     */
    public static String listToString(TaskList taskList) throws DukeException {
        StringBuilder builder = new StringBuilder();
        for (Task task : taskList.getList()) {
            builder.append(task.recordString());
            builder.append("\n");
        }
        return builder.toString();
    }

    /**
     * Save the task list to a file.
     * @param str The string contains task information
     * @param path The path that contains the cache file
     * @return The file saved.
     * @throws DukeException
     */
    public static File saveFile(String str, String path) throws DukeException {
        try {
            // Create path if not exist
            File file = new File(System.getProperty("user.dir") + "/data");
            if (!file.exists()) {
                Path folderPath = Paths.get(System.getProperty("user.dir") + "/data");
                Files.createDirectories(folderPath);
                assert (file.exists());
            }

            // Create file if not exist
            file = new File(path);
            file.createNewFile();
            assert (file.exists()) : "The input file has not been created. Please check!";
            FileWriter writer = new FileWriter(path);
            writer.write(str);
            writer.close();
            return file;

        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Used for undo command, cancel the previous action.
     * @param f The file previously saved.
     * @return The task list restored.
     * @throws DukeException
     * @throws FileNotFoundException
     */
    public static TaskList backToLastStep(File f) throws DukeException, FileNotFoundException {
        ArrayList<Task> taskList = new ArrayList<>();
        String[] commands;
        String type;
        String description;
        boolean isDone;
        Scanner sc;
        Task.clear();
        try {
            sc = new Scanner(f);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                commands = line.split(" \\| ", 3);
                type = commands[0].trim();
                isDone = commands[1].trim().equals("1") ? true : false;
                description = commands[2].trim();

                // sync task using ui.Ui functions
                if (type.equals("T")) {
                    ToDo task = new ToDo(description);
                    if (isDone) {
                        task.changeStatus();
                    }
                    taskList.add(task);
                } else if (type.equals("D")) {
                    String name = description.split(" \\| ", 2)[0];
                    String by = description.split(" \\| ", 2)[1];
                    Deadline task = new Deadline(name, by);
                    if (isDone) {
                        task.changeStatus();
                    }
                    taskList.add(task);
                } else if (type.equals("E")) {
                    String name = description.split(" \\| ", 2)[0];
                    String at = description.split(" \\| ", 2)[1];
                    Event task = new Event(name, at);
                    if (isDone) {
                        task.changeStatus();
                    }
                    taskList.add(task);
                } else {
                    System.out.println(":( OOPS!!! No such task type.");
                }
            }

            return new TaskList(taskList);
        } catch (FileNotFoundException e) {
            System.out.println(" :( OOPS!!! Cannot be read cos the file doesn't exist");
            return null;
        }
    }
}
