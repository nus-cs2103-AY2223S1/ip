package duke;

import java.util.ArrayList;

/**
 * Ui class that deals with the interaction with the users.
 */
public class Ui {
    private final String LINE_BREAK = "____________________________________________________________";

    /**
     * Displays a welcome message.
     */
    public void hello() {
        System.out.println(LINE_BREAK + "\nHello! I'm Donovan\nWhat can I do for you?\n" + LINE_BREAK);
    }

    /**
     * Displays a goodbye message.
     */
    public void bye() {
        System.out.println(LINE_BREAK + "\n\tBye! Hope to see you again soon!\n" + LINE_BREAK);
    }

    /**
     * Displays a file loading error message.
     */
    public void showLoadingError() {
        System.out.println("OOPS! I have difficulty loading your file!");
    }

    /**
     * Displays the todo task added with its corresponding index.
     *
     * @param todo The todo task specified by user.
     * @param size The number of tasks in the list.
     */
    public void printTodo(Task todo, int size) {
        System.out.printf(LINE_BREAK
                + "\n\tGot it. I've added this task:\n\t%s\n\tNow you have %d tasks in the list.\n"
                + LINE_BREAK + "\n", todo, size);
    }

    /**
     * Displays the deadline task added with its corresponding index.
     *
     * @param deadline The deadline task specified by user.
     * @param size The number of tasks in the list.
     */
    public void printDeadline(Task deadline, int size) {
        System.out.printf(LINE_BREAK
                + "\n\tGot it. I've added this task:\n\t%s\n\tNow you have %d tasks in the list.\n"
                + LINE_BREAK + "\n", deadline, size);
    }

    /**
     * Displays the event task added with its corresponding index.
     *
     * @param event The event task specified by user.
     * @param size The number of tasks in the list.
     */
    public void printEvent(Task event, int size) {
        System.out.printf(LINE_BREAK
                + "\n\tGot it. I've added this task:\n\t%s\n\tNow you have %d tasks in the list.\n"
                + LINE_BREAK + "\n", event, size);
    }

    /**
     * Displays the list of tasks the user has inputted thus far.
     *
     * @param tasks The list of tasks user has inputted.
     */
    public void listTasks(TaskList tasks) {
        System.out.println(LINE_BREAK);
        System.out.println("\tHere are the tasks in your list.");
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            System.out.printf("\t%d. %s\n", i + 1, task.toString());
        }
        System.out.println(LINE_BREAK);
    }

    /**
     * Displays the message that the specified task has been deleted.
     *
     * @param task The task to be deleted.
     * @param size The number of tasks left after deletion.
     */
    public void printDelete(Task task, int size) {
        System.out.printf(LINE_BREAK
                + "\n\tNoted. I've removed this task:\n\t%s\n" + "\tNow you have %d tasks in the list.\n"
                + LINE_BREAK + "\n", task, size);
    }

    /**
     * Displays the message that the specified task has been marked.
     *
     * @param task The task to be marked as done.
     */
    public void printMarkedTask(Task task) {
        System.out.printf(LINE_BREAK
                + "\n\tNice! I've marked this task as done:\n\t%s\n"
                + LINE_BREAK + "\n", task);
    }

    /**
     * Displays the message that the specified task has been unmarked.
     *
     * @param task The task to be marked as undone.
     */
    public void printUnmarkedTask(Task task) {
        System.out.printf(LINE_BREAK
                + "\n\tOkay, I've marked this task as not done yet:\n\t%s\n"
                + LINE_BREAK + "\n", task);
    }

    /**
     * Displays the list of tasks based on the keyword user specify.
     *
     * @param lst An ArrayList of the tasks filtered by keyword.
     */
    public void printFind(ArrayList<Task> lst) {
        System.out.println(LINE_BREAK);
        System.out.println("\tHere are the matching tasks in your list.");
        for (int i = 0; i < lst.size(); i++) {
            Task task = lst.get(i);
            System.out.printf("\t%d. %s\n", i+1, task.toString());
        }
        System.out.println(LINE_BREAK);
    }


}
