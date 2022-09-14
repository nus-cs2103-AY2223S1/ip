package util;

import tasks.Task;
import tasks.TaskList;

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
        // At this point, reply should not be null
        assert reply != null : "Null reply was given to basic formatter";
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
        String tasksGrammar = listLength > 1 ? " tasks" : " task";
        return addSeparator("hoohoohaha, monke has added the following task to the list!\n  "
                + task + "\nu currently have " + listLength + tasksGrammar + ".");
    }

    /**
     * Formats a task list
     *
     * @param taskList Task list.
     * @return Formatted text.
     */
    public String list(List<Task> taskList) {
        StringBuilder reply = new StringBuilder();
        reply.append("u monke gotta do these:\n");
        int count = 1;
        for (Task task: taskList) {
            reply.append("  ").append(count++).append(". ").append(task.toString()).append("\n");
        }
        reply.setLength(reply.length() - 1);
        return addSeparator(reply.toString());
    }

    public String find(TaskList taskList, List<Task> result) {
        StringBuilder reply = new StringBuilder();
        reply.append("u monke has these monkey doos:\n");
        if (result.isEmpty()) {
            return addSeparator("u monke, u didn't add anything with that.");
        }
        for (Task task: result) {
            int index = taskList.getTaskList().indexOf(task) + 1;
            reply.append("  ").append(index).append(". ").append(task.toString()).append("\n");
        }
        return addSeparator(reply.toString());
    }

    /**
     * Formats mark.
     *
     * @param task Task.
     * @return Formatted text.
     */
    public String markDone(Task task) {
        return addSeparator( "HOOHOOHAHA! GOOD MONKE!!\nmonke has marked this task as done!\n" + task);
    }

    /**
     * Formats unmark
     *
     * @param task Task.
     * @return Formatted text.
     */
    public String markUndone(Task task) {
        return addSeparator("hoohoohaha, monke has marked this task as undone.\n" + task);
    }

    /**
     * Formats delete
     *
     * @param task Task.
     * @param listLength Length of list.
     * @return Formatted text.
     */
    public String delete(Task task, int listLength) {
        String tasksGrammar = listLength > 1 ? " tasks" : " task";
        return addSeparator("hoohoohaha, monke has removed the following task from the list!\n"
                + task + "\nu currently have " + listLength + tasksGrammar + ".");
    }

    public String akw(String kw, String commandkw) {
        return addSeparator("monke has assigned " + kw + " to " + commandkw + ".");
    }

    public String rkw(String kw) {
        return addSeparator("monke has removed " + kw + " as a keyword.");
    }

    /**
     * Formats invalid input.
     *
     * @return Error message.
     */
    public String invalid() {
        return addSeparator("hoohoohaha? monke dunno what dat is.\nsay \"help\" for the things I can do for u.");
    }

    /**
     * Adds separators
     *
     * @param reply The text to wrap.
     * @return Formatted text.
     */
    public String addSeparator(String reply) {
        // Deprecated because of change to GUI
        //String separator = "_________________________________________";
        //return separator + "\n" + reply + "\n" + separator;
        return reply;
    }
}
