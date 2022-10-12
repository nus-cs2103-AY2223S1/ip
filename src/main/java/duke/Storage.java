package duke;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import duke.task.TaskList;

public final class Storage {

    private static final String DEFAULT_DIRECTORY = "./data";
    private static final String DEFAULT_FILEPATH = "duke.dat";

    /**
     * Writes {@code TaskList} tasks into ./data/duke.dat
     * Overwrites the whole file with current tasks
     *
     * @param tasks the {@code TaskList} we are saving
     * @throws DukeException when there is error saving tasks
     */
    public static void write(TaskList tasks) throws DukeException {
        File storageDirectory = new File(DEFAULT_DIRECTORY);
        if (!storageDirectory.exists() && !storageDirectory.mkdir()) {
            throw new DukeException(String.format("Could not create %s directory", DEFAULT_DIRECTORY));
        }
        try {
            FileOutputStream fos = new FileOutputStream(String.format("%s/%s",
                    DEFAULT_DIRECTORY, DEFAULT_FILEPATH));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tasks);
        } catch (IOException e) {
            e.printStackTrace();
            throw new DukeException(String.format("Failed to write file at %s/%s",
                    DEFAULT_DIRECTORY, DEFAULT_FILEPATH));
        }
    }

    /**
     * Loads {@code TaskList} tasks from ./data/duke.dat
     *
     * @return {@code TaskList} tasks loaded from ./data/duke.dat
     * @throws DukeException when there is error loading tasks
     */
    public static TaskList load() throws DukeException {
        try {
            FileInputStream fis = new FileInputStream(String.format("%s/%s",
                    DEFAULT_DIRECTORY, DEFAULT_FILEPATH));
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object o = ois.readObject();
            if (!(o instanceof TaskList)) {
                throw new DukeException(String.format("File at %s/%s corrupted",
                        DEFAULT_DIRECTORY, DEFAULT_FILEPATH));
            }
            return (TaskList) o;
        } catch (IOException | ClassNotFoundException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
