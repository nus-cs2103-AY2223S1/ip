package duke;

import java.util.ArrayList;

/**
 * Represents the Ui to deal with interactions with the user.
 */
public class Ui {
    private TaskList tasks;
    private final static String HOR_LINE = "------------------------------------------------------";

    public Ui() {

    }

    /**
     * Displays to the user the message when no saved tasks are found.
     */
    public void showLoadingError() {
        String msg = ("No saved tasks were found.");
        System.out.println(msg);
    }

    /**
     * Displays to the user the confirmatory message after saved tasks are loaded.
     *
     * @param size number of tasks loaded.
     */
    public void tasksLoadedMsg(int size) {
        String msg = (size + " tasks were loaded.");
        System.out.println(msg);
    }

    /**
     * Displays to the user the welcome message on running Duke.
     *
     * @return the welcome message.
     */
    public String welcomeMessage() {
        String msg = ("Hello! I'm Duke\n" +
                "What can I do for you?");
        printMsgWithLine(msg);
        return msg;
    }

    /**
     * Displays to the user the exit message on closing duke.
     */
    public void exitMessage() {
        String msg = ("\tBye. Hope to see you again soon!");
        printMsgWithLine(msg);
    }

    /**
     * Displays to the user the confirmatory message when a task is deleted
     *
     * @param task deleted task.
     * @param size number of tasks in the list after given tasks is deleted.
     */
    public void deleteTaskMsg(Task task, int size) {
        String msg = ("\tNoted, I have removed this task: \n" +
                "\t\t" + task.toString() + "\n" + "\tNow you have " +
                size + " tasks in the list.");
        printMsgWithLine(msg);
    }

    /**
     * Displays to the user the confirmatory message after a task is marked as done.
     *
     * @param task task marked as done.
     * @return the confirmatory message
     */
    public String taskDoneMsg(Task task) {
        String msg = ("\tNice! I've marked this task as done:\n" +
                "\t\t" + task.toString());
        printMsgWithLine(msg);
        return msg;
    }

    /**
     * Displays to the user the confirmatory message after a task is marked as undone.
     *
     * @param task task marked as undone.
     */
    public void taskUndoneMsg(Task task) {
        String msg = ("\tOK, I've marked this task as not done yet:\n" +
                "\t\t" + task.toString());
        printMsgWithLine(msg);
    }

    public void viewListMsg(ArrayList<Task> tasks) {
        String msg = ("\tHere are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            int num = i + 1;
            msg += ("\t" + num + "." + tasks.get(i).toString() + "\n");
        }
        printMsgWithLine(msg);
    }


    public String taskAddMsg(Task task, int size) {
        String msg = ("\tGot it. I've added this task:\n" + "\t" + task.toString() +
                "\n\tNow you have " + size + " tasks in the list.");
        printMsgWithLine(msg);
        return msg;
    }

    public void viewFoundTasks(String keyword, ArrayList<Task> tasks) {
        String msg = ("\tHere are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).taskName.contains(keyword)) {
                int num = i + 1;
                msg += ("\t" + num + "." + tasks.get(i).toString() + "\n");
            }
        }
        printMsgWithLine(msg);
    }


    public static void drawLine() {
        System.out.println(HOR_LINE);
    }

    public static void printMsgWithLine(String msg) {
        drawLine();
        System.out.println("\n" + msg + "\n");
        drawLine();
    }

}
