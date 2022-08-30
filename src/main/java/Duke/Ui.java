package duke;

import java.util.Scanner;

/**
 * User interface for Duke application.
 */
public class Ui {
    private Scanner sc;

    /**
     * Constructor for the user interface.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    public String getUserCommand() {
        return sc.nextLine();
    }

    /**
     * Returns boolean indicating whether there is further input from the user.
     *
     * @return true if there is further input, else false.
     */
    public boolean hasNextLine() {
        return sc.hasNextLine();
    }

    /**
     * Greets user with opening statements.
     */
    public void greet() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * Says bye to user with closing statement.
     */
    public void sayBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Tells user that file is not found.
     */
    public void fileNotFound() {
        System.out.println("File not Found!");
    }

    /**
     * Outputs list of tasks in Duke application.
     *
     * @param tasklist tasklist from Duke application.
     */
    public void list(TaskList tasklist) {
        System.out.println("Here are the tasks in your list:");
        for (int x = 0; x < tasklist.size(); x++) {
            System.out.println(String.format("%s.%s", x + 1, tasklist.get(x).toString()));
        }
    }

    /**
     * Outputs response if user adds task to tasklist.
     *
     * @param addedTask task added to tasklist.
     * @param tasklist tasklist from Duke application.
     */
    public void addResponse(Task addedTask, TaskList tasklist) {
        System.out.println("Got it. I've added this task:");
        System.out.println(addedTask);
        System.out.println(String.format("Now you have %s task(s) in the list", tasklist.size()));
    }

    /**
     * Outputs response if user deletes task from tasklist.
     *
     * @param tasklist tasklist from Duke application.
     * @param index index of task to be deleted.
     */
    public void deleteResponse(TaskList tasklist, int index) {
        System.out.println("Noted, I've removed this task:");
        System.out.println(tasklist.get(index).toString());
        System.out.println(String.format("Now you have %s task(s) in the list", tasklist.size() - 1));
    }

    /**
     * Outputs response if user marks task on tasklist.
     *
     * @param tasklist tasklist from Duke application.
     * @param index index of task to be marked.
     */
    public void markResponse(TaskList tasklist, int index) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(String.format("%s.%s", index + 1, tasklist.get(index).toString()));
    }

    /**
     * Outputs response if user unmarks task on tasklist.
     *
     * @param tasklist tasklist from Duke application.
     * @param index index of task to be unmarked.
     */
    public void unmarkResponse(TaskList tasklist, int index) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(String.format("%s.%s", index + 1, tasklist.get(index).toString()));
    }

    /**
     * Finds tasks in list with matching description.
     *
     * @param tasklist tasklist from duke application.
     * @param word word to be matched.
     */
    public void find(TaskList tasklist, String word) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasklist.size(); i++) {
            if (tasklist.get(i).toString().contains(word)) {
                System.out.println(String.format("%s.%s", i + 1, tasklist.get(i).toString()));
            }
        }
    }
}
