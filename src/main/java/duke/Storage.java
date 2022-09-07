package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private String filePathStr;

    /**
     * Constructs a storage object with the specified file path.
     *
     * @param filePathStr The specified file path.
     */
    public Storage(String filePathStr) {
        this.filePathStr = filePathStr;
    }

    /**
     * Loads tasks from file.
     *
     * @return List of tasks from file.
     * @throws DukeException If there is an error in loading the tasks from file.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File f1 = new File(filePathStr);
            f1.getParentFile().mkdirs();
            f1.createNewFile();

            Scanner scanner = new Scanner(f1);
            String line;
            while (scanner.hasNext()) {
                Task newTask;
                line = scanner.nextLine();
                // process data
                switch (line.charAt(4)) {
                case 'T':
                    newTask = ToDo.createToDoFromString(line);
                    break;
                case 'E':
                    newTask = Event.createEventFromString(line);
                    break;
                case 'D':
                    newTask = Deadline.createDeadlineFromString(line);
                    break;
                default:
                    newTask = null;
                    break;
                }
                tasks.add(newTask);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("File couldn't be created. Starting Duke with no tasks loaded!");
        } catch (NullPointerException e) {
            throw new DukeException("Please input a file path!");
        } catch (IOException e) {
            throw new DukeException("There was an error loading your data!");
        }
        return tasks;
    }

    /**
     * Saves the specified list of tasks.
     *
     * @param taskList The specified list of tasks.
     */
    public void save(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(filePathStr);
            fw.write(taskList.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("There was an error saving your file, your changes were not saved!");
        }
    }
}
