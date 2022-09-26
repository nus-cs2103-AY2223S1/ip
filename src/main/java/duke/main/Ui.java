package duke.main;

import duke.exception.DukeException;
import duke.commandword.CommandWord;
import duke.task.*;

/**
 * Class encapsulating user interactions.
 */
public class Ui {
    /**
     * Constructor for the Ui class.
     */
    public Ui() {
    }

    /**
     * Prints out the greeting message
     */
    public void greetUser() {
        System.out.println("Hello! I'm Jukebox :)\n" + "What can I do for you today?");
    }

    /**
     * Prints out the exit message.
     */
    public void exitJukebox() {
        System.out.println("Aww... OK, Hope to see you again!");
    }

    /**
     * Prints out error message of DukeException.
     * @param de DukeException of which message to print.
     */
    public void printErrorMessage(DukeException de) {
        System.out.println(de.getMessage());
    }

    /**
     * Prints out message for a successful loading of save file.
     */
    public void successLoadMessage() {
        System.out.println("Loading save file... Done!");
    }

    /**
     * Prints out message for adding a task.
     * @param task Task that was added into the task list.
     * @param taskList Task list that the task was added into.
     */
    public void addTaskMessage(Task task, TaskList taskList) {
        if (task != null) {
            System.out.println("Okay!\n" + "Added: " + task);
            System.out.println(String.format("You now have %d task(s) in your task list!", taskList.getSize()));
        }
    }

    /**
     * Prints out message for marking and unmarking a task.
     * @param task Task that was marked or unmarked.
     * @param command Command that is either MARK or UNMARK.
     */
    public void markUnmarkTaskMessage(Task task, CommandWord command) {
        switch (command) {
        case MARK: {
            System.out.println(String.format("Goodjob! This task is now completed :)\n" + "%s", task.toString()));
            break;
        }
        case UNMARK: {
            System.out.println(String.format("Oh... OK, I'll mark this task as uncompleted!\n" + "%s", task.toString()));
            break;
        }
        }
    }

    /**
     * Prints out message for deleting a task.
     * @param task Task that was deleted.
     * @param taskList Task list that the task was deleted from.
     */
    public void deleteTaskMessage(Task task, TaskList taskList) {
        if (task != null) {
            System.out.println("Ok! I've removed this task: \n" + task);
        }
    }

}
