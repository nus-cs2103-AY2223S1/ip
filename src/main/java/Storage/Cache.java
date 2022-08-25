package Storage;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.IOException;
import java.io.FileNotFoundException;
import DukeException.DukeException;
import DukeException.TypeNotExistException;
import Tasks.Task;

import Ui;

/**
 * A class represents a Storage.
 */
public class Cache {
    private final String filePath;

    /**
     * Constructs a Storage.
     * @param path The file path specified by the Duke bot.
     */
    public Cache(String path) {
        filePath = path;
    }

    /**
     * Alerts the user that there are some previously saved work and recover, otherwise create a new file for future cache.
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
                assert (file.exists());
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
    public static TaskList recovery(File f) throws DukeException, FileNotFoundException {
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
                commands = line.split(" \\| ",3);
                type = commands[0].trim();
                isDone = commands[1].trim().equals("1") ? true : false;
                description = commands[2].trim();

                // sync task using Ui functions
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
            throw new DukeException("     ☹ OOPS!!! Cannot be read cos the file doesn't exist");
        }
    }

    /**
     * Updates the records in cache file once for all before closing Duke.
     * @param taskList A Tasks.Task list to be recorded.
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
            throw new DukeException("     ☹ OOPS!!! Cannot be written cos the file doesn't exist");
        }
    }
}