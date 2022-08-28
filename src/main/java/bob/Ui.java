package bob;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.LocalDate;

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
    public void displayWelcomeMessage() {
        System.out.println("hey, i'm bob!\ndo you need help?");
        System.out.println("here's what you can do!\n" +
                "    ❤️    ADD A TODO TASK: todo <task>\n" +
                "    🌸    ADD A DEADLINE: deadline <task> /by <yyyy-mm-dd>\n" +
                "    ✨    ADD AN EVENT: event <task /at <yyyy-mm-dd>\n" +
                "    💕    VIEW LIST OF EVENTS: list\n" +
                "    🌼    MARK AS DONE: mark <task number in list>\n" +
                "    ❣️    UNMARK TASK: unmark <task number in list>\n" +
                "    🌟    REMOVE TASK: remove <task number in list>\n" +
                "    💞    TO END THE PROGRAM: bye\n" +
                "hope this helps!");
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
    public void sayGoodbye() {
        System.out.println("bye\nsee you again!");
    }

    /**
     * Displays list of tasks
     *
     * @param taskList list of tasks
     */
    public void displayTaskList(TaskList taskList) {
        int index = 1;
        String list = "";
        while (index < (taskList.getLength() + 1)) {
            list = list + "\n" + (index) + ". " + taskList.getTask(index).toString();
            index += 1;
        }
        System.out.println("here are all your tasks!" + list);
    }

    /**
     * Displays marked task
     *
     * @param tasks list of tasks
     * @param index index of marked task
     */
    public void displayMarked(TaskList tasks, int index) {
        System.out.println("yay! you've completed a task!\n" + tasks.getTask(index).toString());
    }

    /**
     * Displays unmarked task
     *
     * @param tasks list of tasks
     * @param index index of unmarked task
     */
    public void displayUnMarked(TaskList tasks, int index) {
        System.out.println("aw...i guess there's another task.\n" + tasks.getTask(index).toString());
    }

    /**
     * Displays removed task and number of remaining tasks
     *
     * @param tasks list of tasks
     * @param task task removed
     */
    public void displayRemoved(TaskList tasks, Task task) {
        System.out.println("that's one less task for you! removed:" + "\n  "
                + task.toString() + "\njust " + (tasks.getLength()) + " tasks left!");
    }

    /**
     * Displays filtered tasks based on input date
     *
     * @param date date of tasks to be filtered out
     * @param list string format of filtered tasks
     */
    public void displayFiltered(LocalDate date, String list) {
        System.out.print("here are your tasks on " +
                date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + "\n" + list);
    }

    /**
     * Displays added task
     *
     * @param tasks list of tasks
     * @param task task added
     */
    public void displayAddedTask (TaskList tasks, Task task) {
        System.out.println("okay! new task:" + "\n  " + task.toString()
                + "\njust " + tasks.getLength() + " tasks left!");
    }

    /**
     * Displays error message
     *
     * @param error the error message to be displayed
     */
    public void displayError(String error) {
        System.out.println(error);
    }
}
