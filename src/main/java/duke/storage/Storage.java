package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.DukeTask;
import duke.task.Event;
import duke.task.ToDo;

/**
 * Encapsulates saving/loading data from storage.
 *
 * @author Kartikeya
 */
public class Storage {
    private static final String FILE_FOLDER_NAME = "data";
    private static final String FILENAME = "artemis.txt";
    private static final String FILEPATH = FILE_FOLDER_NAME + File.separator + FILENAME;

    public Storage() throws DukeException {
        this.createStorageFile();
    }

    private void createStorageFile() throws DukeException {
        try {
            File directory = new File(FILE_FOLDER_NAME);
            if (!directory.exists()) {
                directory.mkdir();
            }
            File saved = new File(FILEPATH);
            if (!saved.exists()) {
                saved.createNewFile();
            }
            assert saved.exists() : "Save file should always be created.";
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Loads saved TaskList data from the storage file. Parses saved data
     * into required format that complies with the ui.
     *
     * @return list of DukeTasks that were saved in local storage
     * @throws DukeException if the storage file cannot be found
     */
    public ArrayList<DukeTask> load() throws DukeException {
        ArrayList<DukeTask> taskList = new ArrayList<>();
        try {
            File f = new File(FILEPATH);
            assert f.exists() : "Save file should always exist.";
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String[] taskSplit = s.nextLine().split(" >> ");
                DukeTask task;
                switch (taskSplit[0]) {
                case "T": {
                    task = new ToDo(taskSplit[2]);
                    break;
                }
                case "D": {
                    task = new Deadline(taskSplit[2], taskSplit[3]);
                    break;
                }
                case "E": {
                    task = new Event(taskSplit[2], taskSplit[3]);
                    break;
                }
                default: {
                    throw new DukeException("Invalid storage configuration encountered.");
                }
                }
                checkTaskDone(taskSplit, task);
                taskList.add(task);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException(e.getMessage());
        }
        return taskList;
    }

    /**
     * Saves TaskList data to the storage file. Uses a getter for specified
     * storage strings to achieve this in a consistent format.
     *
     * @param itemList list of DukeTasks
     */
    public void save(ArrayList<DukeTask> itemList) {
        try {
            FileWriter fw = new FileWriter(FILEPATH);
            for (DukeTask t : itemList) {
                assert t != null : "Empty tasks should not exist in itemList.";
                assert !t.getStorageString().equals("")
                        : "Storage string should never be empty.";
                fw.write(t.getStorageString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Checks if the loaded task is completed.
     * @param taskSplit loaded task data
     * @param task DukeTask representation of loaded task
     */
    private void checkTaskDone(String[] taskSplit, DukeTask task) {
        if (taskSplit[1].equals("1")) {
            task.markAsDone();
        }
    }
}
