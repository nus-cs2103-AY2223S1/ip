package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.exception.LoadException;
import duke.exception.SaveException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.ToDo;
/**
 * A class to retrieve and store user data
 *
 * @author Elbert Benedict
 */
public class Storage {
    private static final String SAVE_FILE_PATH = "./data/duke.txt";

    /**
     * Stores the list of tasks to the save file.
     *
     * @param taskList the list of tasks to be saved.
     * @throws DukeException If there is a problem when writing to file.
     */
    public static void saveTasks(TaskList taskList) throws DukeException {
        try {
            FileWriter writer = new FileWriter(SAVE_FILE_PATH);
            String tasks = taskList.toSaveFileString();
            writer.write(tasks);
            writer.close();
        } catch (IOException e) {
            throw new SaveException();
        }
    }

    /**
     * Retrieve the list of tasks from the save file.
     *
     * @return a Tasklist instance representing the saved tasks.
     * @throws DukeException If data is corrupted or there is a problem when
     *     loading the save file.
     */
    public static TaskList getSavedTasks() throws DukeException {
        try {
            File saveFile = new File(SAVE_FILE_PATH);
            //Create file if it does not exist
            saveFile.createNewFile();
            Scanner reader = new Scanner(saveFile);
            TaskList savedTasks = new TaskList();

            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] splitted = data.split("\\s@\\s");
                String taskType = splitted[0];
                String task;
                String date;
                boolean isDone;

                switch (taskType) {
                case ToDo.TYPE_SYMBOL:
                    // assert data is in correct format and
                    // has not been corrupted
                    assert splitted.length == 3 : "Incorrect Todo Format";
                    task = splitted[2];
                    isDone = splitted[1].equals("[X]");
                    savedTasks.add(new ToDo(task, isDone));
                    break;

                case Event.TYPE_SYMBOL:
                    // assert data is in correct format and
                    // has not been corrupted
                    assert splitted.length == 4 : "Incorrect Event Format";
                    task = splitted[2];
                    isDone = splitted[1].equals("[X]");
                    date = splitted[3];
                    savedTasks.add(new Event(task, date, isDone));
                    break;

                case Deadline.TYPE_SYMBOL:
                    // assert data is in correct format and
                    // has not been corrupted
                    assert splitted.length == 4 : "Incorrect Deadline Format";
                    task = splitted[2];
                    isDone = splitted[1].equals("[X]");
                    date = splitted[3];
                    savedTasks.add(new Deadline(task, date, isDone));
                    break;

                default:
                    throw new LoadException();
                }
            }

            return savedTasks;
        } catch (IOException e) {
            throw new LoadException();
        }
    }
}
