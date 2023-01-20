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
                + displayCommands();
        return response;
    }

    /**
     * Displays list of commands
     *
     * @return list of commands
     */
    public String displayCommands() {
        String response = "    ↓ here's what you can do! ↓\n"
                + "    ---------------------------\n"
                + "1.    ADD A TODO TASK: todo <task>\n"
                + "2.    ADD A DEADLINE: deadline <task> /by <yyyy-mm-dd>\n"
                + "3.    ADD AN EVENT: event <task /at <yyyy-mm-dd>\n"
                + "4.    VIEW LIST OF TASKS: list\n"
                + "5.    MARK AS DONE: mark <task number>\n"
                + "6.    UNMARK TASK: unmark <task number>\n"
                + "7.    REMOVE TASK: remove <task number>\n"
                + "8.    FILTER TASKS OF SPECIFIC DATE: filter <yyyy-mm-dd>\n"
                + "9.    EDIT TASK: edit <task number> <name-to-be-edited OR /date-to-be-edited>\n"
                + "10.   DISPLAY COMMANDS: help\n"
                + "11.   TO END THE PROGRAM: bye\n";
        return response;
    }

    /**
     * Displays goodbye message
     *
     * @return string displaying goodbye message
     */
    public String sayGoodbye() {
        String response = "bye\nsee you again!";
        return print(response);
    }

    /**
     * Prints dashed line
     *
     * @return string displaying a dotted line
     */
    public String printLine() {

        return "-------------------------------------\n";
    }

    /**
     * Displays list of tasks with header message
     *
     * @param taskList list of tasks
     * @param outputMessage header to be printed with tasks
     * @return string displaying list of tasks
     */
    public String displayTaskList(TaskList taskList, String outputMessage) {
        assert taskList.getLength() > 0 : "length of TaskList should never be negative";
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
     * @return string displaying task marked
     */
    public String displayMarked(TaskList tasks, int index) {
        assert tasks.getLength() > 0 : "length of TaskList should never be negative";
        String response = "yay! you've completed a task!\n" + tasks.getTask(index).toString();
        return print(response);
    }

    /**
     * Displays unmarked task
     *
     * @param tasks list of tasks
     * @param index index of unmarked task
     * @return string displaying task unmarked
     */
    public String displayUnmarked(TaskList tasks, int index) {
        assert tasks.getLength() > 0 : "length of TaskList should never be negative";
        String response = "aw...i guess there's another task.\n" + tasks.getTask(index).toString();
        return print(response);

    }

    /**
     * Displays removed task and number of remaining tasks
     *
     * @param tasks list of tasks
     * @param task task removed
     * @return string displaying task removed and number of tasks left
     */
    public String displayRemoved(TaskList tasks, Task task) {
        assert tasks.getLength() > 0 : "length of TaskList should never be negative";
        String response = "that's one less task for you! removed:" + "\n  "
                + task.toString() + "\njust " + (tasks.getLength()) + " tasks left!";
        return print(response);
    }

    /**
     * Displays added task
     *
     * @param tasks list of tasks
     * @param task task added
     * @return string displaying task added and number of tasks
     */
    public String displayAddedTask(TaskList tasks, Task task) {
        assert tasks.getLength() > 0 : "length of TaskList should never be negative";
        String response = "okay! new task:" + "\n  " + task.toString()
                + "\njust " + tasks.getLength() + " tasks left!";
        return print(response);
    }

    /**
     * Displays updated task
     *
     * @param tasks list of tasks
     * @param task task updated
     * @param originalTask string value of original task
     * @return string displaying initial task and updated task
     */
    public String displayUpdateTask(TaskList tasks, Task task, String originalTask) {
        assert tasks.getLength() > 0 : "length of TaskList should never be negative";
        String response = "okay! task updated!\n initial: " + originalTask + "\n\nmodified: " + task.toString();
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
