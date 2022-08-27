package roofus;

import java.util.Scanner;

import roofus.task.Task;

/**
 * Ui handles interactions with the user.
 */
public class Ui {
    private static final String LINE_SEP = "****************************************";
    private Scanner sc;

    /**
     * Constructs an instance of Storage with a Scanner instance.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Formats and prints Roofus's greetings.
     */
    public void greet() {
        System.out.println(LINE_SEP);
        System.out.println("Hello I'm Roofus\n"
                + "What can I do for you?");
        System.out.println(LINE_SEP);
    }

    /**
     * Formats and prints the program's termination message.
     */
    public void signOff() {
        System.out.println(String.format("%s\nBye. Hope to see you again soon!\n%s",
                LINE_SEP, LINE_SEP));
    }

    /**
     * Formats and prints any error messages.
     *
     * @param message The error message to be formatted and printed.
     */
    public void printErrMessage(String message) {
        if (message.isEmpty()) {
            System.out.println("!!!!!");
        }
        System.out.println(LINE_SEP);
        System.out.println(message.toUpperCase());
        System.out.println(LINE_SEP);
    }

    /**
     * Formats and prints delete task message.
     *
     * @param task The specified task to be removed.
     * @param taskLength The length of TaskList after removal.
     */
    public void delete(String task, int taskLength) {
        System.out.println(LINE_SEP);
        System.out.println(String.format("Noted. I've removed this task:\n%s\n"
                + "Now you have %d tasks in the list.", task.toString(), taskLength));
        System.out.println(LINE_SEP);
    }

    /**
     * Formats and prints all tasks in TaskList.
     *
     * @param taskList A Tasklist that contains all tasks.
     */
    public void list(TaskList taskList) {
        System.out.println(LINE_SEP);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.length(); i++) {
            int index = i + 1;
            System.out.println(index + "." + taskList.getTask(i).toString());
        }
        System.out.println(LINE_SEP);
    }

    /**
     * Formats and prints all tasks that contains key in TaskList.
     *
     * @param taskList A Tasklist that contains all tasks.
     */
    public void filterList(TaskList taskList, String key) {
        System.out.println(LINE_SEP);
        System.out.println("Here are the matching tasks in your list:");
        int count = 1;
        for (int i = 0; i < taskList.length(); i++) {
            String taskString = taskList.getTask(i).toString();
            if (taskString.contains(key)) {
                System.out.println(count + "." + taskString);
                count++;
            }
        }
        System.out.println(LINE_SEP);
    }

    /**
     * Formats and prints mark task message.
     *
     * @param task The task to be marked as done.
     */
    public void mark(Task task) {
        System.out.println(LINE_SEP);
        System.out.println("Nice! I've marked this task as done:\n"
                + task.toString());
        System.out.println(LINE_SEP);
    }

    /**
     * Formats and prints unmark task message.
     *
     * @param task The task to be marked as not done.
     */
    public void unmark(Task task) {
        System.out.println(LINE_SEP);
        System.out.println("OK, I've marked this task as not done yet:\n"
                + task.toString());
        System.out.println(LINE_SEP);
    }

    /**
     * Formats and prints add task message.
     *
     * @param task The task to be added to TaskList.
     * @param taskLength The length of TaskList after the addition.
     */
    public void addTask(Task task, int taskLength) {
        String reply = String.format("%s\nGot it. I've added this task:\n%s\n"
                        + "Now you have %d tasks in the list.\n%s",
                            LINE_SEP, task.toString(), taskLength, LINE_SEP);
        System.out.println(reply);
    }

    /**
     * Formats and prints clear TaskList message.
     */
    public void clearStorage() {
        System.out.println("Storage has been cleared :)");
    }

    /**
     * Returns the next user input using Scanner.
     *
     * @return String A string that represents the next user input.
     */
    public String readCommand() {
        return sc.nextLine();
    }
}
