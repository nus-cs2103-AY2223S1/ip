package chatbot.ui;

import chatbot.exceptions.DukeException;
import chatbot.tasks.Task;

import java.util.List;
import java.util.Scanner;

/**
 * The class is responsible for displaying messages to the user
 * whether it is query or action results or conversation elements.
 */
public class UI {
    private static final String EMOJI = "<_>";
    private final Scanner reader;

    public UI() {
        this.reader = new Scanner(System.in);
    }

    public String getUserInput() {
        return reader.nextLine();
    }
    /**
     * Greets the user.
     */
    public void greet() {
        System.out.print("Yes? I'm Zlimez~~ \nWhat can I possibly do for you?\n >>>^<<<\n\n");
    }

    /**
     * Displays all the tasks in the todo list.
     * @param tasks The list containing all the tasks.
     */
    public void listAll(List<Task> tasks) {
        System.out.println("\tReally? If you are so forgetful...");
        list(tasks);
    }

    /**
     * Displays the tasks in the todo list on the specified date.
     * @param tasks The list containing the relevant tasks.
     */
    public void listTaskOn(List<Task> tasks) {
        if (tasks != null) {
            System.out.println("\tThese are your tasks for that day");
            list(tasks);
        } else {
            System.out.println("\tWell you are a lazy bum, you have nothing on the day");
        }
    }

    /**
     * Displays the tasks that contain a given keyword.
     * @param tasks The list containing the relevant tasks.
     */
    public void listFound(List<Task> tasks) {
        if (!tasks.isEmpty()) {
            System.out.println("\tThese are the tasks that match your description");
            list(tasks);
        } else {
            System.out.println("\t You like to search for nothing and waste time huh?");
        }
    }

    private void list(List<Task> tasks) {
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println("\t" + i + ". " + tasks.get(i - 1));
        }
    }

    /**
     * Informs user that the given task has been added and the new todo list status.
     *
     * @param target The task added.
     * @param numberOfTasks The number of tasks after the addition.
     */
    public void add(Task target, int numberOfTasks) {
        System.out.println("\tLazily added this task for you " + EMOJI);
        System.out.println("\t\t" + target);
        System.out.println("\tWala now you have " + numberOfTasks + " tasks in the list.");
    }

    /**
     * Informs user that the given task has been removed and the new todo list status.
     *
     * @param target The task removed.
     * @param numberOfTasks The number of tasks after the deletion.
     */
    public void delete(Task target, int numberOfTasks) {
        System.out.println("\tYES, I've removed this task for YOU:");
        System.out.println("\t\t" + target);
        System.out.println("\tWala now you have " + numberOfTasks + " tasks in the list.");
    }

    /**
     * Informs user that the given task has been marked.
     *
     * @param target The task marked as complete.
     */
    public void unmark(Task target) {
        System.out.println("\t-_-, I've unmarked this task for YOU AGAIN:");
        System.out.println("\t\t" + target);
    }

    /**
     * Informs user that the given task has been unmarked.
     *
     * @param target The task marked as incomplete.
     */
    public void mark(Task target) {
        System.out.println("\tWellz, I've marked this task for YOU:");
        System.out.println("\t\t" + target);
    }

    /**
     * Displays the exception that has been raised.
     *
     * @param e The exceptions raised.
     */
    public void reprimand(DukeException e) {
        System.out.println("\t" + e.getMessage());
    }

    /**
     * Says goodbye to the user.
     */
    public void bye() {
        System.out.println("\tBye. zzz FINALLY~~" + " " + EMOJI);
        reader.close();
    }
}
