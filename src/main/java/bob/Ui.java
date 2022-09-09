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
     * Reads the user input
     *
     * @return user input
     */
    public String getReply() {
        String reply = this.sc.nextLine();
        return reply;
    }

    /**
     * Displays goodbye message
     */
    public String sayGoodbye() {
        String response = this.printLine() + "bye\nsee you again!\n" + this.printLine();
        return response;
    }

    /**
     * Prints dashed line
     */
    public String printLine() {

        return "-------------------------------------\n";
    }

    /**
     * Displays list of tasks with header message
     *
     * @param taskList list of tasks
     * @param outputMessage header to be printed with tasks
     */
    public String displayTaskList(TaskList taskList, String outputMessage) {
        assert taskList.getLength() > 0 : "length of taskList should never be negative";
        if (taskList.getLength() == 0) {
            return this.displayError("you have no tasks!");
        }
        int index = 1;
        String list = "";
        while (index < (taskList.getLength() + 1)) {
            list = list + "\n" + (index) + ". " + taskList.getTask(index).toString();
            index += 1;
        }
        String response = this.printLine() + outputMessage + "\n" + list + "\n" + this.printLine();
        return response;
    }

    /**
     * Displays marked task
     *
     * @param tasks list of tasks
     * @param index index of marked task
     */
    public String displayMarked(TaskList tasks, int index) {
        assert tasks.getLength() > 0 : "length of taskList should never be negative";
        String response = this.printLine() + "yay! you've completed a task!\n"
                + tasks.getTask(index).toString() + "\n" + this.printLine();
        return response;
    }

    /**
     * Displays unmarked task
     *
     * @param tasks list of tasks
     * @param index index of unmarked task
     */
    public String displayUnmarked(TaskList tasks, int index) {
        assert tasks.getLength() > 0 : "length of taskList should never be negative";
        String response = this.printLine() + "aw...i guess there's another task.\n"
                + tasks.getTask(index).toString() + "\n" + this.printLine();
        return response;
    }

    /**
     * Displays removed task and number of remaining tasks
     *
     * @param tasks list of tasks
     * @param task task removed
     */
    public String displayRemoved(TaskList tasks, Task task) {
        assert tasks.getLength() > 0 : "length of taskList should never be negative";
        String response = this.printLine() + "that's one less task for you! removed:" + "\n  "
                + task.toString() + "\njust " + (tasks.getLength()) + " tasks left!\n" + this.printLine();
        return response;
    }

    /**
     * Displays added task
     *
     * @param tasks list of tasks
     * @param task task added
     */
    public String displayAddedTask(TaskList tasks, Task task) {
        assert tasks.getLength() > 0 : "length of taskList should never be negative";
        String response = this.printLine() + "okay! new task:" + "\n  " + task.toString()
                + "\njust " + tasks.getLength() + " tasks left!\n" + this.printLine();
        return response;
    }

    /**
     * Displays error message
     *
     * @param error the error message to be displayed
     */
    public String displayError(String error) {
        String response = this.printLine() + error + "\n" + this.printLine();
        return response;
    }
}
