package duke;

import java.util.ArrayList;

/**
 * Ui of the program.
 *
 * @author Lai Han Wen
 */
public class Ui {

    /**
     * Returns a welcome message to the user.
     *
     * @return Welcome message.
     */
    public String getWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello from\n" + logo + "What can I do for you?";
    }

    /**
     * Returns a reply with the added task and the updated number of tasks in
     * the current list.
     *
     * @param t The task added.
     * @param list The current list of tasks.
     * @return Reply when task is added.
     */
    public String getTaskAddedReply(Task t, TaskList list) {
        String taskAdded = "Got it. I've added this task:\n" + "  " + t;
        String size = Integer.toString(list.size());
        String numOfTasks = "\nNow you have " + size + " tasks in the list.";
        return taskAdded + numOfTasks;
    }

    /**
     * Returns a reply with the deleted task and the updated number of tasks in
     * the current list.
     *
     * @param t Task to be deleted.
     * @param list The current list of tasks.
     * @return Reply when task is deleted.
     */
    public String getTaskDeletedReply(Task t, TaskList list) {
        String taskDeleted = "Noted. I've removed this task:\n" +
                "  " + t;
        String size = Integer.toString(list.size());
        String numOfTasks = "\nNow you have " + size + " tasks in the list.";
        return taskDeleted + numOfTasks;
    }

    /**
     * Returns a reply when a task is marked as done.
     *
     * @param t The task to be marked as done.
     * @return Reply when a task is marked as done.
     */
    public String getTaskMarkedReply(Task t) {
        return "Nice! I've marked this task as done:\n" + "  " + t;
    }

    /**
     * Returns an error to the user if user input is unknown.
     *
     * @return An error when user input is unknown.
     */
    public String getUnknownInputError() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    /**
     * Returns a goodbye message to user.
     *
     * @return Goodbye message.
     */
    public String getGoodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns a String of the current list of tasks.
     *
     * @param list The current list of tasks.
     * @return The current list of tasks, as a String.
     */
    public String getList(TaskList list) {
        ArrayList<Task> tasks = list.getList();

        int count = 1;
        String strList = "";
        for (Task t : tasks) {
            String num = Integer.toString(count);
            strList = strList + num + ". " + t +"\n";
            count++;
        }

        if (tasks.isEmpty()) {
            strList = "List is empty!";
        }

        return strList;
    }

    /**
     * Returns the list of tasks that matches a specific keyword.
     *
     * @param keyword The keyword in a task.
     * @param list The current list of tasks.
     * @return List of tasks with a specific keyword.
     */
    public String getTasksWithKeyword(String keyword, TaskList list) {
        ArrayList<Task> tempList = new ArrayList<>(); // stores matching tasks

        for (Task t : list.getList()) {
            if (t.toString().contains(keyword)) {
                tempList.add(t);
            }
        }

        int count = 1;
        String tasks = "";
        for (Task t : tempList) {
            String num = Integer.toString(count);
            tasks = tasks + num + ". " + t + "\n";
            count++;
        }

        if (tempList.isEmpty()) {
            tasks = "No matching tasks!";
        }

        return "Here are the matching tasks in your list:\n" + tasks;
    }
}
