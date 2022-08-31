package duke.manager;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the saving and loading of user's tasks.
 */
public class Storage {
    /** The save file to be read from. */
    private File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Saves the user's current tasks into a text file.
     *
     * @param list The list of current tasks.
     */
    public void saveTasks(TaskList list) {
        FileWriter fr = null;
        try {
            fr = new FileWriter(this.file);
            fr.write(list.getTasksString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //close resources, otherwise disk won't be saved? something about OS buffering
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Loads the user's saved tasks from a text file.
     *
     * @return A list of user's saved tasks.
     * @throws DukeException If a generic error occurs trying to parse the input.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        Scanner sc = null;

        try {
            if (!this.file.exists()) {
                System.out.println("No existing save file found. Created one for you.");
                try {
                    this.file.getParentFile().mkdirs();
                    this.file.createNewFile();
                } catch (IOException err) {
                    System.out.println("Problem trying to create directory!");
                }
            }
            sc = new Scanner(this.file);
        } catch (FileNotFoundException e) {
            return loadedTasks;
        }

        while (sc.hasNext()) {
            //store each duke.task into an array
            String taskString = sc.nextLine();
            String[] taskData = taskString.split("\\|");

            //remove whitespaces
            for (int i = 0; i < taskData.length; i++) {
                taskData[i] = taskData[i].trim();
            }

            //organise duke.task data into named variables
            String taskType = taskData[0];
            boolean isTaskDone = taskData[1].equals("1");
            String taskDescription = taskData[2];
            LocalDate taskDate = null;
            if (taskType.equals("D") || taskType.equals("E")) {
                taskDate = LocalDate.parse(taskData[3]);
            }

            //Create duke.task and add to list
            Task newTask;
            switch (taskType) {
                case "T":
                    newTask = new Todo(isTaskDone, taskDescription);
                    break;
                case "D":
                    newTask = new Deadline(isTaskDone, taskDescription, taskDate);
                    break;
                case "E":
                    newTask = new Event(isTaskDone, taskDescription, taskDate);
                    break;
                default:
                    throw new DukeException("duke.task.Task type not defined!");
            }

            loadedTasks.add(newTask);
        }

        sc.close();
        return loadedTasks;
    }
}
