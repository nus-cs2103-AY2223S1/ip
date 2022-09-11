package duke;

import java.util.List;

/**
 * Ui class allows Duke to interact/print messages
 * to the user when changes are made to the user's task list
 *
 * @author  Gerald Teo Jin Wei
 * @version 0.1
 * @since   2022-08-28
 */
public class Ui {
    static final String GREET_MESSAGE = "Hello I'm Bert.\nWhat can I do for you?\n";
    static final String QUIT_MESSAGE = "Bye. Hope to see you again soon!";
    static final String MARK_TASK_MESSAGE =  "Nice! I've marked this task as done:\n  ";
    static final String UNMARK_TASK_MESSAGE = "OK, I've marked this task as not done yet:\n  ";

    /**
     * Prints out the quitting message
     * when the user exits the Duke program
     */
    public String quit() {
        return QUIT_MESSAGE;
    }

    /**
     * Lists out the tasks
     * in the user's task list
     * @param tasks List of tasks to be printed out
     */
    public String listOutTasks(TaskList tasks) {
        return tasks.printList();
    }

    /**
     * Marks the task as completed and
     * prints out a message
     * @param taskToMark The task from the list to be marked
     */
    public String markTask(Task taskToMark) {
        taskToMark.mark();
        return MARK_TASK_MESSAGE + taskToMark;
    }

    /**
     * Marks the task as uncompleted
     * and prints out a message
     * @param taskToMark The task from the list to be unmarked
     */
    public String unmarkTask(Task taskToMark) {
        taskToMark.unmark();
        return UNMARK_TASK_MESSAGE + taskToMark;
    }

    /**
     * Adds the new task to the user's task list
     * and prints out a message
     * @param taskList TaskList of the user
     * @param taskToAdd The new task to be added to user's task list
     */
    public String addTask(TaskList taskList, Task taskToAdd) {
        taskList.addTask(taskToAdd);
        return "Got it. I've added this task:\n  " + taskToAdd + "\nNow you have "
                + taskList.getSize() + " tasks in the list.";
    }

    /**
     * Deletes the task from the user's task list
     * and prints out a message
     * @param taskList TaskList of the user
     * @param taskToDelete The task from the user's task list to be deleted
     */
    public String deleteTask(TaskList taskList, Task taskToDelete) {
        taskList.deleteTask(taskToDelete);
        return "Noted. I've removed this task:\n  " + taskToDelete + "\nNow you have "
                + taskList.getSize() + " tasks in the list";
    }

    /**
     * Prints out all the tasks with the user's search keyword
     * @param taskListWithKeyword List of tasks containing user's search keyword
     */
    public String printTasksWithKeyword(List<Task> taskListWithKeyword) {
        if (taskListWithKeyword == null) {
            return "There are no tasks that match the keyword \n";
        }
        String str = "Here are the matching tasks in your list:\n";
        for (int i = 1; i <= taskListWithKeyword.size(); i++) {
            str += i + "." + taskListWithKeyword.get(i - 1) + "\n";
        }
        return str;
    }
}
