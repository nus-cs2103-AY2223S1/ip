package roofus;

import roofus.task.Task;

import java.util.Scanner;

/**
 * Ui handles interactions with the user.
 */
public class Ui {
    private static String LINESEP = "****************************************";
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
        System.out.println(LINESEP);
        System.out.println("Hello I'm Roofus\n" + "What can I do for you?");
        System.out.println(LINESEP);
    }

    /**
     * Formats and prints the program's termination message.
     */
    public void signOff() {
        System.out.println(String.format("%s\nBye. Hope to see you again soon!\n%s",
                LINESEP, LINESEP));
    }

    /**
     * Formats and prints any error messages.
     * 
     * @param message The error message to be formatted and printed.
     */
    public void errMessage(String message) {
        System.out.println(LINESEP);
        System.out.println(message.toUpperCase());
        System.out.println(LINESEP);
    }

    /**
     * Formats and prints delete task message.
     * 
     * @param task The specified task to be removed.
     * @param taskLength The length of TaskList after removal.
     */
    public void delete(String task, int taskLength) {
        System.out.println(LINESEP);
        System.out.println(String.format("Noted. I've removed this task:\n%s\n" +
                        "Now you have %d tasks in the list.", task.toString(), taskLength));
        System.out.println(LINESEP);
    }

    /**
     * Formats and prints all tasks in TaskList.
     * 
     * @param taskList A Tasklist that contains all tasks.
     */
    public void list(TaskList taskList) {
        System.out.println(LINESEP);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.length(); i++) {
            int index = i + 1;
            System.out.println(index + "." + taskList.getTask(i).toString());
        }
        System.out.println(LINESEP);
    }
    
    public void filterList(TaskList taskList, String key) {
        System.out.println(LINESEP);
        System.out.println("Here are the matching tasks in your list:");
        int count = 1;
        for (int i = 0; i < taskList.length(); i++) {
            String taskString = taskList.getTask(i).toString();
            if (taskString.contains(key)) {
                System.out.println(count + "." + taskString);
                count++;
            }
        }
        System.out.println(LINESEP);
    }
    

    /**
     * Formats and prints mark task message.
     * 
     * @param task The task to be marked as done.
     */
    public void mark(Task task) {
        System.out.println(LINESEP);
        System.out.println("Nice! I've marked this task as done:\n" +
                task.toString());
        System.out.println(LINESEP);
    }

    /**
     * Formats and prints unmark task message.
     * 
     * @param task The task to be marked as not done.
     */
    public void unmark(Task task) {
        System.out.println(LINESEP);
        System.out.println("OK, I've marked this task as not done yet:\n" +
                task.toString());
        System.out.println(LINESEP);
    }

    /**
     * Formats and prints add task message.
     * 
     * @param task The task to be added to TaskList.
     * @param taskLength The length of TaskList after the addition.
     */
    public void addTask(Task task, int taskLength) {
        String reply = String.format("%s\nGot it. I've added this task:\n%s\n" +
                        "Now you have %d tasks in the list.\n%s", LINESEP, task.toString(),
                taskLength, LINESEP);
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
