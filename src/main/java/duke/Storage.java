package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * A class that encapsulates the loading tasks and saving of tasks
 * to file.
 */
public class Storage {
    /**
     * Class constructor that initialises the path where we will
     * save the data in.
     *
     * @throws DukeException If we are unable to create the 'data'
     *                       directory.
     */
    public Storage() throws DukeException {
        String home = System.getProperty("user.dir");
        Path path = Paths.get(home, "data");
        File dataDir = new File(path.toString());
        if (!dataDir.exists() && !dataDir.mkdir()) {
            throw new DukeException("Unable to create 'data' directory");
        }
    }

    /**
     * Loads in the data from text file to the given TaskList.
     *
     * @param data The TaskList to addTask the data to.
     * @throws DukeException If the text in the file is of
     *                       invalid format.
     */
    public void loadData(TaskList data) throws DukeException {
        String home = System.getProperty("user.dir");
        Path path = Paths.get(home, "data", "duke.txt");
        try {
            File savedData = new File(path.toString());
            Scanner sc = new Scanner(savedData);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] taskData = line.split("\\|");
                checkFormat(taskData);
                Task task;
                switch (taskData[0]) {
                case "T":
                    task = new ToDo(taskData[2]);
                    break;
                case "D":
                    task = new Deadline(taskData[2], LocalDate.parse(taskData[3]));
                    break;
                case "E":
                    task = new Event(taskData[2], LocalDate.parse(taskData[3]));
                    break;
                default:
                    throw new DukeException("File format invalid!");
                }
                data.addTask(task);
                setCompletion(task, taskData);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("Cannot read from saved data.");
        }
    }

    /**
     * Saves data from the given TaskList to the text file.
     *
     * @param data The TaskList that contains all the tasks to be saved.
     * @throws DukeException If there is an error writing to the text file.
     */
    public void saveData(TaskList data) throws DukeException {
        try {
            FileWriter file = new FileWriter("data/duke.txt", false);
            for (int i = 0; i < data.getSize(); i++) {
                file.write(data.getTask(i).stringToSave() + System.lineSeparator());
            }
            file.close();
        } catch (IOException e) {
            throw new DukeException("An error occurred when writing to file");
        }
    }

    /**
     * Checks if the format of the task data is valid.
     *
     * @param taskData The data read from the saved file.
     * @throws DukeException If the data is not valid.
     */
    private void checkFormat(String[] taskData) throws DukeException {
        boolean isToDo = "T".equals(taskData[0]);
        if (taskData.length < 3) {
            throw new DukeException("File format invalid!");
        }
        if (taskData.length == 3 && !isToDo) {
            throw new DukeException("File format invalid!");
        }
    }

    /**
     * Sets the completion status of the saved task.
     *
     * @param task The saved task.
     * @param taskData The data that was saved for that task.
     */
    private void setCompletion(Task task, String[] taskData) {
        boolean isComplete = "1".equals(taskData[1]);
        if (isComplete) {
            task.markDone();
        } else {
            task.markNotDone();
        }
    }
}
