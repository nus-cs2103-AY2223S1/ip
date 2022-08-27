package roofus;

import java.util.Scanner;

import roofus.task.Task;

/**
 * Ui handles interactions with the user.
 */
public class Ui {

    /**
     * Constructs an instance of Storage with a Scanner instance.
     */
    public Ui() {
//        sc = new Scanner(System.in);
    }

    /**
     * Formats and prints Roofus's greetings.
     */
    public String greet() {
        return "Hello I'm Roofus\n"
                + "What can I do for you?";
    }

    /**
     * Formats and prints the program's termination message.
     */
    public String signOff() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Formats and prints any error messages.
     *
     * @param message The error message to be formatted and printed.
     */
    public String printErrMessage(String message) {
        if (message.isEmpty()) {
            return "!!!!!";
        }
        return message.toUpperCase();
    }

    /**
     * Formats and prints delete task message.
     *
     * @param task The specified task to be removed.
     * @param taskLength The length of TaskList after removal.
     */
    public String delete(String task, int taskLength) {
        return String.format("Noted. I've removed this task:\n%s\n"
                + "Now you have %d tasks in the list.", task.toString(), taskLength);
    }

    /**
     * Formats and prints all tasks in TaskList.
     *
     * @param taskList A Tasklist that contains all tasks.
     */
    public String list(TaskList taskList) {
        String finalString = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.length(); i++) {
            int index = i + 1;
            finalString += String.format("%d. %s\n", index, 
                    taskList.getTask(i).toString());
        }
        return finalString;
    }

    /**
     * Formats and prints all tasks that contains key in TaskList.
     *
     * @param taskList A Tasklist that contains all tasks.
     */
    public String filterList(TaskList taskList, String key) {
        String finalString = "Here are the matching tasks in your list:\n";
        int count = 1;
        for (int i = 0; i < taskList.length(); i++) {
            String taskString = taskList.getTask(i).toString();
            if (taskString.contains(key)) {
                finalString += String.format("%d. %s", count, taskString);
                count++;
            }
        }
        return finalString;
    }

    /**
     * Formats and prints mark task message.
     *
     * @param task The task to be marked as done.
     */
    public String mark(Task task) {
        return "Nice! I've marked this task as done:\n"
                + task.toString();
    }

    /**
     * Formats and prints unmark task message.
     *
     * @param task The task to be marked as not done.
     */
    public String unmark(Task task) {
        return "OK, I've marked this task as not done yet:\n"
                + task.toString();
    }

    /**
     * Formats and prints add task message.
     *
     * @param task The task to be added to TaskList.
     * @param taskLength The length of TaskList after the addition.
     */
    public String addTask(Task task, int taskLength) {
        String reply = String.format("Got it. I've added this task:\n%s\n"
                        + "Now you have %d tasks in the list.",
                            task.toString(), taskLength);
        return reply;
    }

    /**
     * Formats and prints clear TaskList message.
     */
    public String clearStorage() {
        return "Storage has been cleared :)";
    }
}
