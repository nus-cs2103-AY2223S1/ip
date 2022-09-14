package duke.functions;

import duke.tasks.Task;

/**
 * Class for interactions with Duke bot users.
 *
 * @author lauralee
 */
public class Ui {

    /**
     * Prints introduction for Duke bot when someone runs Duke.
     *
     * @return The introduction description.
     */
    public static String printIntro() {
        return ("Hello! I'm Duke\n" +
                "What can I do for you?");
    }

    /**
     * Prints list of Tasks inputted by the user.
     *
     * @param taskList The array whose elements are the tasks that
     *                the user has added to his task list.
     * @return The list of tasks.
     */
    public static String printList(TaskList taskList) {
        String list = "";
        for (int i = 1; i <= Task.getNumberTasks(); i++) {
            list = list + (i + ". " + taskList.getTaskArr()[i].output() + "\n");
        }
        return "Here are the tasks in your list:\n" + list;
    }

    /**
     * Prints description when a task is deleted by the user.
     *
     * @param deletedTask The task specified by the user to be deleted.
     * @param numberTasksLeft The number of tasks left after deletion of the task.
     * @return The delete task description.
     */
    public static String printDelete(Task deletedTask, int numberTasksLeft) {
        return "Noted. I've removed this task:\n" +
                deletedTask.output() + "\n" +
                "Now you have " + (numberTasksLeft) + " tasks in the list.";
    }

    /**
     * Prints description of the new Todo task that has just been added.
     *
     * @param newTask The new Todo task added by the user.
     * @return The Todo task description.
     */
    public static String printToDo(Task newTask) {
        return "Got it. I've added this task:\n" +
                newTask.output() + "\n" +
                "Now you have " + Task.getNumberTasks() + " tasks in the list.";
    }

    /**
     * Prints description of the new deadline task that has just been added.
     *
     * @param newTask The new deadline task added by the user.
     * @return The Deadline task description.
     */
    public static String printDeadline(Task newTask) {
        return "Got it. I've added this task:\n" +
                newTask.output() + "\n" +
                "Now you have " + Task.getNumberTasks() + " tasks in the list.";
    }

    /**
     * Prints description of the new event task that has just been added.
     *
     * @param newTask The new event task added by the user.
     * @return The Event task description.
     */
    public static String printEvent(Task newTask) {
        return "Got it. I've added this task:\n" +
                newTask.output() + "\n" +
                "Now you have " + Task.getNumberTasks() + " tasks in the list.";
    }

    /**
     * Prints description when a user marks a task.
     *
     * @param type The type of the task, "E", "D" or "T".
     * @param name The name of the task that has been marked by the user.
     * @return The marked task description.
     */
    public static String printMark(String type, String name) {
        return "Nice! I've marked this task as done:\n" +
                "[" + type + "][X] " + name;
    }

    /**
     * Prints description when a user unmarks a task.
     *
     * @param type The type of the task, "E", "D" or "T".
     * @param name The name of the task that has been marked by the user.
     * @return The unmarked task description.
     */
    public static String printUnmark(String type, String name) {
        return "OK, I've marked this task as not done yet: \n" +
                "[" + type + "][ ] " + name;
    }

    /**
     * Prints message displayed when the user tries to find a keyword in the TaskList.
     *
     * @return Finding function description.
     */
    public static String printFind() {
        return ("Here are the matching tasks in your list:");
    }

    /**
     * Prints descriptions of tasks that contains the keyword the user is trying to find.
     *
     * @param index The index number of the task found.
     * @param output The description of the task that matches the user's find specification.
     * @return Description of tasks that match user's input.
     */
    public static String printFindTasks(int index, String output) {
        return (index + ". " + output);
    }

    /**
     * Prints message displayed when a Deadline task is snoozed.
     *
     * @param snoozedTask The Deadline task being snoozed.
     * @return The new description of the Deadline task that has been snoozed.
     */
    public static String printSnoozeDeadline(Task snoozedTask) {
        return "Got it. I've snoozed this task:\n" +
                snoozedTask.output() + "\n" +
                "Now you have " + Task.getNumberTasks() + " tasks in the list.";
    }

    /**
     * Prints message displayed when a Event task is snoozed.
     *
     * @param snoozedTask The Event task being snoozed.
     * @return The new description of the Event task that has been snoozed.
     */
    public static String printSnoozeEvent(Task snoozedTask) {
        return "Got it. I've snoozed this task:\n" +
                snoozedTask.output() + "\n" +
                "Now you have " + Task.getNumberTasks() + " tasks in the list.";
    }

    /**
     * Prints message displayed when there is an error saving the users input into a file.
     *
     * @return Description for file saving error.
     */
    public static String printFileSavingError() {
        return ("Error saving file.");
    }

    /**
     * Prints message that will be displayed when the user exits duke.
     *
     * @return Description for ending Duke programme.
     */
    public static String printBye() {
        return ("Bye. Hope to see you again soon!");
    }

}














