package amanda.ui;

import amanda.manager.TaskList;
import amanda.task.Tag;
import amanda.task.Task;
import amanda.task.TaskState;

/**
 * Ui handles interactions with the user.
 */
public class Ui {

    private static String amandaResponse = "";

    /**
     * Print AddCommand interactions.
     * @param task current task list.
     */
    public static void addTaskResponse(Task task) {
        amandaResponse += "Let's see if you will actually get this done:\n"
                + task + "\n" +  "Now you have " + TaskList.size() + " tasks in the list.";
    }

    /**
     * Print ListCommand interactions.
     */
    public static void listResponse() {
        amandaResponse += "Here are the tasks in your list, now stop disturbing me:\n";
    }

    public static void findResponse() {
        amandaResponse += "Here are the tasks I found in your list, now stop disturbing me:";
    }

    /**
     * Print MarkCommand interactions.
     * @param task current task list.
     */
    public static void markResponse(Task task, TaskState state) {
        if (state == TaskState.DONE) {
            amandaResponse += "You already finished this. Why are you bothering me?\n";
        } else {
            amandaResponse += "Wow! You actually finished the task, I didn't think you have it in you.\n";
        }
    }

    /**
     * Print UnmarkCommand interactions.
     * @param task current task list.
     */
    public static void unMarkResponse(Task task, TaskState state) {
        if (state == TaskState.DONE) {
            amandaResponse += "I knew it! you didn't actually finish it.\n";
        } else {
            amandaResponse += "You haven't done this task yet.\n";
        }
    }

    /**
     * Print the DeleteCommand interactions.
     */
    public static void deleteResponse(Task task) {
        amandaResponse += "It's fine! Out of sight, out of mind. Am i right?\n"
                + task + "\nNow you have " + TaskList.getList().size() + " tasks in the list.";
    }


    public static void tagResponse(Task task, Tag tag) {
        amandaResponse += "Tagging " + task + " as " + tag.getDesc() + " is not going to help you finish it faster.";
    }

    public static void addResponse(String input) {
        amandaResponse += input;
    }

    public static void resetResponse() {
        amandaResponse = "";
    }

    public static String getAmandaResponse() {
        return amandaResponse;
    }
}
