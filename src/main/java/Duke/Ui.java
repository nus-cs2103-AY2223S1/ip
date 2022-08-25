package Duke;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    /**
     * Creates an Ui instance.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Gets input from user.
     * @return user input.
     */
    public String getInput() {
        return scanner.nextLine();
    }

    /**
     * Prints welcome.
     */
    public void printWelcome() {
        System.out.println("Hello! I'm Duke \n What can I do for you?");
    }

    /**
     * Prints bye.
     */
    public void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }

    /**
     * Prints the added task.
     * @param t the task to print.
     * @param size size of Tasklist.
     */
    public void printAddTask(Task t, int size) {
        System.out.printf("Got it. I've added this task: \n  %s \n Now you have %d tasks in the list.\n",
                t, size);
    }

    /**
     * Prints the deleted task.
     * @param t the task to print.
     * @param size size of Tasklist.
     */
    public void printDeleteTask(Task t, int size) {
        System.out.printf("Noted. I've removed this task: \n  %s \nNow you have %d tasks in the list.\n",
                t, size);
    }

    /**
     * Prints the marked task.
     * @param t the task to print.
     */
    public void printMark(Task t) {
        System.out.printf("Nice! I've marked this task as done: \n  %s \n", t);
    }

    /**
     * Prints the unmarked task.
     * @param t the task to print.
     */
    public void printUnMark(Task t) {
        System.out.printf("OK, I've marked this task as not done yet: \n %s \n", t);
    }

    /**
     * Prints the message.
     * @param s message.
     */
    public void printMsg(String s) {
        System.out.println(s);
    }

    /**
     * Prints  List message.
     */
    public void printList() {
        System.out.println("Here are the tasks in your list:");
    }

    /**
     * Prints Result List message.
     */
    public void printResultList() {
        System.out.println("Here are the matching tasks in your list:");
    }
}