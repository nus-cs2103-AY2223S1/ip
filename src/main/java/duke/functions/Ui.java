package duke.functions;

import duke.support.Parser;
import duke.tasks.Task;

/**
 * Class for interactions with Duke bot users.
 * @author lauralee
 */
public class Ui {

    private Parser parser;

    private TaskList taskList;

    /**
     * Constructor for the Ui class.
     */
    public Ui() {
        this.parser = new Parser();
        this.parser.userInput();
        this.taskList = this.parser.getTaskList();
    }

    /**
     * To retrieve the TaskList created by the user.
     * @return The user's TaskList.
     */
    public TaskList getTaskList() {
        return this.taskList;
    }

    /**
     * Prints introduction for Duke bot when someone runs Duke.
     */
    public static void printIntro() {
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
    }

    /**
     * Prints list of tasks descriptions that has been added by user.
     * @param taskList The array whose elements are the tasks that
     *                the user has added to his task list.
     */
    public static void printList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= Task.getNumberTasks(); i++) {
            System.out.println(i + "." + taskList.getTaskArr()[i].output());
        }
    }

    /**
     * Prints description of the task that has been deleted from the task list by the user.
     * @param deletedTask The task specified by the user to be deleted.
     * @param numberTasksLeft The number of tasks left after deletion of the task.
     */
    public static void printDelete(Task deletedTask, int numberTasksLeft) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedTask.output());
        System.out.println("Now you have " + (numberTasksLeft) + " tasks in the list.");
    }

    /**
     * Prints description of the new todo task that has just been added.
     * @param newTask The new todo task added by the user.
     */
    public static void printToDo(Task newTask) {
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask.output());
        System.out.println("Now you have " + Task.getNumberTasks() + " tasks in the list.");
    }

    /**
     * Prints description of the new deadline task that has just been added.
     * @param newTask The new deadline task added by the user.
     */
    public static void printDeadline(Task newTask) {
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask.output());
        System.out.println("Now you have " + Task.getNumberTasks() + " tasks in the list.");
    }

    /**
     * Prints description of the new event task that has just been added.
     * @param newTask The new event task added by the user.
     */
    public static void printEvent(Task newTask) {
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask.output());
        System.out.println("Now you have " + Task.getNumberTasks() + " tasks in the list.");
    }

    /**
     * Prints description of the task that has been marked by user as well
     * as checking its box to show that it has been marked.
     * @param type The type of the task, "E", "D" or "T".
     * @param name The name of the task that has been marked by the user.
     */
    public static void printMark(String type, String name) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[" + type + "][X] " + name);
    }

    /**
     * Prints description of the task that has been unmarked by user as well
     * as unchecking its box to show that it has been unmarked.
     * @param type The type of the task, "E", "D" or "T".
     * @param name The name of the task that has been marked by the user.
     */
    public static void printUnmark(String type, String name) {
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println("[" + type + "][ ] " + name);
    }

    /**
     * Prints message displayed when the user tries to find a keyword in the TaskList.
     */
    public static void printFind() {
        System.out.println("Here are the matching tasks in your list:");
    }

    /**
     * Prints descriptions of tasks that contains the keyword the user is trying to find.
     */
    public static void printFindTasks(int index, String output) {
        System.out.println(index + ". " + output);
    }

    /**
     * Prints message displayed when there is an error saving the users input into a file.
     */
    public static void printFileSavingError() {
        System.out.println("Error saving file.");
    }

    /**
     * Prints message that will be displayed when the user exits duke.
     */
    public static void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

}