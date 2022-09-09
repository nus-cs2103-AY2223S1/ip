package duke;

import duke.dukeExceptions.DukeException;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

import java.util.Scanner;

/**
 * Represents a Storage class that is responsible for saving and loading data.
 * 
 * @author Ramanathan Kumarappan
 */
public class Storage {
    private final String DIR = System.getProperty("user.dir") + "/data";
    private final String SAVE_FILE = DIR + "/dukeData.txt";

    /**
     * Constructor for Storage.
     * 
     * @param taskList The TaskList to load the saved data onto.
     * @throws DukeException When there is an error loading storage.
     */
    Storage(TaskList taskList) throws DukeException {
        try {
            File savedTasks = new File(SAVE_FILE);
            if (savedTasks.exists()) {
                Scanner s = new Scanner(savedTasks);
                while (s.hasNext()) {
                    String task = s.nextLine();
                    char firstChar = task.charAt(0);
                    assert firstChar == 'T' || firstChar == 'E' || firstChar == 'D';
                    switch (task.charAt(0)) {
                    case 'T':
                        taskList.addTask(ToDo.taskFromSave(task));
                        break;
                    case 'E':
                        taskList.addTask(Event.taskFromSave(task));
                        break;
                    case 'D':
                        taskList.addTask(Deadline.taskFromSave(task));
                        break;
                    }
                }
            } else {
                File saveFolder = new File(DIR);
                if (saveFolder.exists()) {
                    savedTasks.createNewFile();
                } else {
                    saveFolder.mkdir();
                    savedTasks.createNewFile();
                }
            }
        } catch (IOException e) {
            throw new DukeException("Storage error");
        } 
    }

    /**
     * Saves the given TaskList.
     * 
     * @param taskList The TaskList to be saved.
     * @throws DukeException When there is an error saving storage.
     */
    public void saveTaskList(TaskList taskList) throws DukeException {
        try {
            File savedTasks = new File(SAVE_FILE);
            FileWriter fw = new FileWriter(savedTasks);
            int noOfTasks = taskList.getTaskListSize();
            for (int i  = 0; i < noOfTasks; i++) {
                fw.write(taskList.getTask(i).saveString());
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Storage error");
        }
    }
}
