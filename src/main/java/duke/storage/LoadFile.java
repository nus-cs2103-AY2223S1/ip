package duke.storage;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/**
 * Assists with loading .txt file containing a <code>Task[]</code> in String
 * format.
 * Note that this class is package-private.
 *
 * @author Kang Wei
 */
class LoadFile {

    /**
     * Parses a task in String format into a <code>Task</code>
     * format, and returns it.
     *
     * @param taskString The task in String format.
     * @return The task converted to a <code>Task</code> format.
     */
    private static Task parseTaskString(String taskString) {
        String taskType = taskString.substring(1, 2);
        String taskDescription;
        String dateDescription;
        if (taskType.equals("T")) { // Task is a Todo
            taskDescription = taskString.substring(7);
            return new Task(taskDescription);
        } else {
            /**
             * Setting the appropriate dateDelimiter.
             */
            String dateDelimiter;
            boolean isDeadline = false;
            if (taskType.equals("D")) { // Task is a Deadline
                dateDelimiter = " \\(by: ";
                isDeadline = true;
            } else { // Task is an Event
                dateDelimiter = " \\(at: ";
            }

            /**
             * Getting the appropriate descriptions after splitting the 
             * task in String format by the dateDelimiter.
             */
            String[] inputArr = taskString.split(dateDelimiter);
            taskDescription = inputArr[0].substring(7);
            dateDescription = inputArr[1].substring(0, inputArr[1].length() - 1);

            return (new Task(taskDescription, dateDescription, (
                            isDeadline ? Task.Type.DEADLINE : Task.Type.EVENT)));
        }
    }

    /**
     * Loads a .txt file of tasks, converts it to a <code>TaskList</code>
     * and returns it.
     *
     * @param filePath The location of the file.
     * @return The <code>TaskList</code> converted from the .txt file.
     * @throws DukeException Throws an exception if an IOException is caught.
     */
    public static TaskList load(String filePath) throws DukeException {
        TaskList tasks = new TaskList(new ArrayList<Task>());
        try {
            File file = new File(filePath);
            Scanner sc = new Scanner(file);

            while (sc.hasNext()) {
                String taskString = sc.nextLine();
                Task task = parseTaskString(taskString);
                tasks.addTask(task, false);
            }
        } catch (IOException e) {
            throw new DukeException("Honnney! There was a problem with loading your list " +
                    "of tasks from " + filePath + "! :,(");
        }
        return tasks;
    }
}
