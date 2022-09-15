package duke;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Class that handles interactions with the user
 */
public class Ui {
    //string that will be printed to user for every user input
    private static String dukeResponse = "";

    /**
     * Updates dukeResponse when adding a task
     */
    public static void addTaskLog(Task task) {
        dukeResponse += "Ok, new task for you: \n"
                + " " + task + "\n";

        dukeResponse += "You now have " + TaskList.length() + " tasks.\n";
    }

    /**
     * Updates dukeResponse when removing a task
     */
    public static void removeTaskLog(Task task) {
        dukeResponse += "Ok, I've removed this task for you: \n"
                + " " + task + "\n";

        dukeResponse += "You now have " + (TaskList.length() - 1) + " tasks.\n";
    }

    public static void editDescTaskLog(int index, Task newTask) {
        dukeResponse += "Ok, I've changed task " + (index + 1) + " to:\n";
        dukeResponse += " " + newTask + "\n";
    }


    /**
     * Updates dukeResponse when marking a task
     */
    public static void markLog(Task task, boolean isDone) {
        if (isDone) {
            dukeResponse += "This task is done, goodjob! :)\n";
        } else {
            dukeResponse += "This task hasn't been done yet? I've updated it for you\n";
        }
        
        dukeResponse += task;
    }

    /**
     * Appends a string to dukeResponse
     */
    public static void appendDukeResponse(String text) {
        dukeResponse += text;
    }

    /**
     * Resets dukeResponse to an empty string
     */
    public static void clearDukeResponse() {
        dukeResponse = "";
    }

    /**
     * Returns dukeResponse
     */
    public static String getDukeResponse() {
        return dukeResponse;
    }
}
