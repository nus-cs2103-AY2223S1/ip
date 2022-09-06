package duke.functions;

import duke.support.Parser;
import duke.tasks.Task;

/**
 * Class for interactions with Duke bot users.
 */
public class Ui {

    /**
     * Prints introduction for Duke bot when someone runs Duke.
     */
    public static String printIntro() {
        return ("Hello! I'm Duke\n" +
                "What can I do for you?");
    }

    /**
     * Prints list of Tasks inputted by the user.
     * @param taskList The array whose elements are the tasks that
     *                the user has added to his task list.
     */
    public static String printList(TaskList taskList) {
        String list = "";
        for (int i = 1; i <= Task.getNumberTasks(); i++) {
            list = list + (i + ". " + taskList.getTaskArr()[i].output() + "\n");
        }
        return "Here are the tasks in your list:\n" + list;
    }

    /**
     * @param deletedTask The task specified by the user to be deleted.
     * @param numberTasksLeft The number of tasks left after deletion of the task.
     */
    public static String printDelete(Task deletedTask, int numberTasksLeft) {
        return "Noted. I've removed this task:\n" +
                deletedTask.output() + "\n" +
                "Now you have " + (numberTasksLeft) + " tasks in the list.";
    }

    /**
     * Prints description of the new todo task that has just been added.
     * @param newTask The new todo task added by the user.
     */
    public static String printToDo(Task newTask) {
        return "Got it. I've added this task:\n" +
                newTask.output() + "\n" +
                "Now you have " + Task.getNumberTasks() + " tasks in the list.";
    }

    /**
     * Prints description of the new deadline task that has just been added.
     * @param newTask The new deadline task added by the user.
     */
    public static String printDeadline(Task newTask) {
        return "Got it. I've added this task:\n" +
                newTask.output() + "\n" +
                "Now you have " + Task.getNumberTasks() + " tasks in the list.";
    }

    /**
     * Prints description of the new event task that has just been added.
     * @param newTask The new event task added by the user.
     */
    public static String printEvent(Task newTask) {
        return "Got it. I've added this task:\n" +
                newTask.output() + "\n" +
                "Now you have " + Task.getNumberTasks() + " tasks in the list.";
    }

    /**
     * @param type The type of the task, "E", "D" or "T".
     * @param name The name of the task that has been marked by the user.
     */
    public static String printMark(String type, String name) {
        return "Nice! I've marked this task as done:\n" +
                "[" + type + "][X] " + name;
    }

    /**
     * @param type The type of the task, "E", "D" or "T".
     * @param name The name of the task that has been marked by the user.
     */
    public static String printUnmark(String type, String name) {
        return "OK, I've marked this task as not done yet: \n" +
                "[" + type + "][ ] " + name;
    }

    /**
     * Prints message displayed when the user tries to find a keyword in the TaskList.
     */
    public static String printFind() {
        return ("Here are the matching tasks in your list:");
    }

    /**
     * Prints descriptions of tasks that contains the keyword the user is trying to find.
     */
    public static String printFindTasks(int index, String output) {
        return (index + ". " + output);
    }

    /**
     * Prints message displayed when there is an error saving the users input into a file.
     */
    public static String printFileSavingError() {
        return ("Error saving file.");
    }

    /**
     * Prints message that will be displayed when the user exits duke.
     */
    public static String printBye() {
        return ("Bye. Hope to see you again soon!");
    }

}














