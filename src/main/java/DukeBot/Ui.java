package DukeBot;

import java.util.Scanner;

/**
 * Encapsulates the User Interface.
 */
public class Ui {

    private Scanner scanner;

    /**
     * Initialises the Ui object and a Scanner that reads the user's input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints a line onto the output.
     */
    public void showLine() {
        System.out.println("-----------------------------------------------");
    }

    /**
     * Prints the new task onto the output.
     *
     * @param newTask to be printed on the output.
     */
    public void showNewTask(Task newTask) {
        System.out.println("    Got it. I've added this task:");
        System.out.print("      ");
        System.out.println(newTask);
        System.out.println(String.format("    Now you have %d tasks in the list.", Task.getTaskCount()));
    }

    /**
     * Prints the welcome message onto the output.
     */
    public void showWelcome() {
        System.out.println("--------------------------------------------");
        System.out.println("| Hi this is Thesh. What can I do for you? |");
        System.out.println("--------------------------------------------");
    }

    /**
     * Prints the error onto the output.
     *
     * @param e to be printed on the output.
     */
    public void showError(Exception e) {
        System.out.println(e);
        showLine();
    }

    /**
     * Lists out the tasks onto the output.
     *
     * @param tasks
     */
    public void showList(TaskList tasks, boolean isFind) {
        if (isFind) {
            System.out.println("Here are the matching tasks in your list:");
        } else {
            System.out.println("    Here are the tasks in your list:");
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(String.format("      %d. %s", i + 1, tasks.get(i)));
        }
    }

    /**
     * Prints goodbye message onto output.
     */
    public void showBye() {
        System.out.println("    Bye. Hope to see you again soon!");
    }

    /**
     *
     */
    public void showDelete(Task deletedTask) {
        System.out.println("    Noted. I've removed this task:");
        System.out.println(String.format("      %s", deletedTask));
        System.out.println(String.format("    Now you have %d tasks in the list.", Task.getTaskCount()));
    }
    /**
     * Reads the user's next command.
     *
     * @return The user's command as a String.
     */
    public String readCommand() {
        return scanner.nextLine();
    }
}
