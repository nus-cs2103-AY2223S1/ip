package duke;

import java.util.Scanner;

/**
 * Ui of the program.
 *
 * @author Lai Han Wen
 */
public class Ui {

    private Scanner sc;

    private String command;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Displays welcome message to user.
     */
    public void showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm duke.Duke\n" +
                "What can I do for you?");
    }

    /**
     * Displays to user the added task and the updated number of tasks in
     * the current list.
     *
     * @param t The task added.
     * @param list The current list of tasks.
     */
    public void showTaskAdded(Task t, TaskList list) {
        String size = Integer.toString(list.size());
        System.out.println("Got it. I've added this task:\n" + "  " + t);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays to user the deleted task and the updated number of tasks in
     * the current list.
     *
     * @param taskNum Number of task to be deleted.
     * @param list The current list of tasks.
     */
    public void showTaskDeleted(int taskNum, TaskList list) {
        System.out.println("Noted. I've removed this task:\n" +
                "  " + list.getTask(taskNum));
        String size = Integer.toString(list.size() - 1);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays to user the task that is marked as done.
     *
     * @param t The task to be marked as done.
     */
    public void showTaskMarked(Task t) {
        System.out.println("Nice! I've marked this task as done:\n" +
                "  " + t);
    }

    /**
     * Prompts user command and reads the next line of command.
     *
     * @return The user's command.
     */
    public String promptUserCommand() {
        System.out.println("Enter command:");
        this.command = this.sc.nextLine();
        return this.command;
    }

    /**
     * Displays an error to the user if user input is unknown.
     */
    public void showUnknownInputError() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Displays goodbye message to user.
     */
    public void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays to user the current list of tasks.
     *
     * @param list The current list of tasks.
     */
    public void showList(TaskList list) {
        int count = 1;
        for (Task t : list.getList()) {
            String s = Integer.toString(count);
            System.out.println(s + ". " + t);
            count++;
        }
    }
}
