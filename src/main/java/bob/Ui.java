package bob;

import java.util.Scanner;

/**
 * Represents Ui object, which stores all text responses to user
 */
//referenced https://github.com/Donovan9617/ip/blob/master/src/main/java/Duke/Ui.java for structure
public class Ui {
    private Scanner sc;

    /**
     * Constructor for Ui object,
     * initializes a scanner to read user input
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Displays welcome message with list of commands
     *
     * @return welcome message
     */
    public String displayWelcomeMessage() {
        String response = "hey, i'm bob!\ndo you need help?\n"
                + this.printLine()
                + "    ↓ here's what you can do! ↓\n"
                + "    ---------------------------\n"
                + "1.    ADD A TODO TASK: todo <task>\n"
                + "2.    ADD A DEADLINE: deadline <task> /by <yyyy-mm-dd>\n"
                + "3.    ADD AN EVENT: event <task /at <yyyy-mm-dd>\n"
                + "4.    VIEW LIST OF EVENTS: list\n"
                + "5.    MARK AS DONE: mark <task number in list>\n"
                + "6.    UNMARK TASK: unmark <task number in list>\n"
                + "7.    REMOVE TASK: remove <task number in list>\n"
                + "8.    FILTER TASKS OF SPECIFIC DATE: filter <yyyy-mm-dd>\n"
                + "9.    TO END THE PROGRAM: bye\n";
        return response;
    }

    /**
     * Displays goodbye message
     *
     * @return goodbye message
     */
    public String sayGoodbye() {
        String response = "bye\nsee you again!";
        return print(response);
    }

    /**
     * Prints dashed line
     *
     * @return a dotted line
     */
    public String printLine() {

        return "-------------------------------------\n";
    }

    /**
     * Displays list of tasks with header message
     *
     * @param taskList list of tasks
     * @param outputMessage header to be printed with tasks
     * @return list of tasks
     */
    public String displayTaskList(TaskList taskList, String outputMessage) {
        if (taskList.getLength() == 0) {
            return this.displayError("you have no tasks!");
        }
        int index = 1;
        String list = "";
        while (index < (taskList.getLength() + 1)) {
            list = list + "\n" + (index) + ". " + taskList.getTask(index).toString();
            index += 1;
        }
        String response = outputMessage + "\n" + list;
        return print(response);
    }

    /**
     * Displays marked task
     *
     * @param tasks list of tasks
     * @param index index of marked task
     * @return task marked
     */
    public String displayMarked(TaskList tasks, int index) {
        String response = "yay! you've completed a task!\n" + tasks.getTask(index).toString();
        return print(response);
    }

    /**
     * Displays unmarked task
     *
     * @param tasks list of tasks
     * @param index index of unmarked task
     * @return task unmarked
     */
    public String displayUnmarked(TaskList tasks, int index) {
        String response = "aw...i guess there's another task.\n" + tasks.getTask(index).toString();
        return print(response);
    }

    /**
     * Displays removed task and number of remaining tasks
     *
     * @param tasks list of tasks
     * @param task task removed
     * @return task removed and number of tasks left
     */
    public String displayRemoved(TaskList tasks, Task task) {
        String response = "that's one less task for you! removed:" + "\n  "
                + task.toString() + "\njust " + (tasks.getLength()) + " tasks left!";
        return print(response);
    }

    /**
     * Displays added task
     *
     * @param tasks list of tasks
     * @param task task added
     * @return task added and number of tasks
     */
    public String displayAddedTask(TaskList tasks, Task task) {
        String response = "okay! new task:" + "\n  " + task.toString()
                + "\njust " + tasks.getLength() + " tasks left!";
        return print(response);
    }

    /**
     * Displays error message
     *
     * @param error the error message to be displayed
     * @return error message
     */
    public String displayError(String error) {
        return print(error);
    }

    /**
     * Formats and returns string input
     *
     * @param response string to be formatted
     * @return formatted string
     */
    public String print(String response) {
        return this.printLine() + response + "\n" + this.printLine();
    }
}
