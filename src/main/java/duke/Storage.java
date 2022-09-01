package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exceptions.DukeException;

/**
 * Represents a <code>Storage</code> class that reads and writes to the local storage.
 */
public class Storage {

    private File file;
    private FileWriter writer;
    private Scanner sc;

    /**
     * Constructs a <code>Storage</code> object that will read and write to the specified file path.
     *
     * @param filePath Path of the file to be read and written into.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Creates a directory at the specified path in the local storage, unless the directory already exists.
     *
     * @param path Path of the directory to be created.
     * @throws DukeException If directory cannot be created.
     */
    public void createDirectory(String path) throws DukeException {
        File dir = new File(path);
        try {
            if (!dir.exists()) {
                dir.mkdir();
            }
        } catch (SecurityException e) {
            throw new DukeException("Oh no! I can't make a folder to store the tasks!");
        }
    }

    /**
     * Creates a file at the path specified in the constructor, unless the file already exists.
     *
     * @throws DukeException If file cannot be created.
     */
    public void createFile() throws DukeException {
        try {
            this.file.createNewFile();
        } catch (IOException e) {
            throw new DukeException("Oops! I can't make the file to store the tasks!");
        }
    }

    /**
     * Reads the list of tasks stored locally.
     *
     * @return An <code>ArrayList</code> of <code>Task</code>.
     * @throws DukeException If the file is not found.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            this.createDirectory("data");
            this.createFile();
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String taskString = sc.nextLine();
                if (taskString.isBlank()) {
                    continue;
                }
                String[] taskElements = taskString.split(" \\| ");
                boolean isTaskDone = taskElements[1].equals("1");

                switch (taskElements[0]) {
                case "T":
                    taskList.add(new ToDo(taskElements[2], isTaskDone));
                    break;
                case "D":
                    taskList.add(new Deadline(taskElements[2], taskElements[3], isTaskDone));
                    break;
                case "E":
                    taskList.add(new Event(taskElements[2], taskElements[3], isTaskDone));
                    break;
                default:
                    break;
                }
            }
            sc.close();
            return taskList;
        } catch (FileNotFoundException e) {
            throw new DukeException("You don't have any previously saved tasks!");
        }
    }

    /**
     * Writes the specified list of tasks into local storage.
     *
     * @param taskList <code>TaskList</code> to be saved into local storage.
     * @throws DukeException If writing into the file fails.
     */
    public void save(TaskList taskList) throws DukeException {
        try {
            writer = new FileWriter(file);
            for (int i = 0; i < taskList.getSize(); i++) {
                writer.write(taskList.get(i).toStorageFormat());
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Something went wrong while saving the file! :(");
        }
    }

}
