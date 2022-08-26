package util;

import tasks.Task;

import java.util.List;

/**
 * This class is used to format outputs to the command line.
 */
public class Ui {
    /**
     * Formats a basic reply
     *
     * @param reply Text.
     * @return Formatted text.
     */
    public String basic(String reply) {
        return addSeparator(reply);
    }

    /**
     * Formats a task addition
     *
     * @param task Task.
     * @param listLength Length of List.
     * @return Formatted text.
     */
    public String addTask(Task task, int listLength) {
        String taskss = listLength > 1 ? " tasks" : " task";
        return addSeparator("Alright, I have added the following task to the list!\n  "
                + task + "\nYou currently have " + listLength + taskss + ".");
    }

    /**
     * Formats a task list
     *
     * @param taskList Task list.
     * @return Formatted text.
     */
    public String list(List<Task> taskList) {
        StringBuilder reply = new StringBuilder();
        reply.append("Here are the tasks you have added:\n");
        int count = 1;
        for (Task task: taskList) {
            reply.append("  ").append(count++).append(". ").append(task.toString()).append("\n");
        }
        reply.setLength(reply.length() - 1);
        return addSeparator(reply.toString());
    }

    /**
     * Formats mark.
     *
     * @param task Task.
     * @return Formatted text.
     */
    public String markDone(Task task) {
        return addSeparator( "Yay! I have marked this task as done!\n  " + task);
    }

    /**
     * Formats unmark
     *
     * @param task Task.
     * @return Formatted text.
     */
    public String markUndone(Task task) {
        return addSeparator("Got it, I have marked this task as undone.\n  " + task);
    }

    /**
     * Formats delete
     *
     * @param task Task.
     * @param listLength Length of list.
     * @return Formatted text.
     */
    public String delete(Task task, int listLength) {
        String taskss = listLength > 1 ? " tasks" : " task";
        return addSeparator("Alright, I have removed the following task from the list!\n  "
                + task + "\nYou currently have " + listLength + taskss + ".");
    }

    /**
     * Formats invalid input.
     *
     * @return Error message.
     */
    public String invalid() {
        return addSeparator("I'm afraid i can't help you with that.\nType \"help\" for the list of things I can do for you.");
    }

    /**
     * Adds separators
     *
     * @param reply The text to wrap.
     * @return Formatted text.
     */
    public String addSeparator(String reply) {
        String separator = "_________________________________________";
        return separator + "\n" + reply + "\n" + separator;
    }
}
