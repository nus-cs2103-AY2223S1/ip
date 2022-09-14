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
    static final String MARK_TASK_MESSAGE = "Nice! I've marked this task as done:\n  ";
    static final String UNMARK_TASK_MESSAGE = "OK, I've marked this task as not done yet:\n  ";

    /**
     * Prints out the quitting message
     * when the user exits the Duke program
     */
    public String printQuitMessage() {
        return QUIT_MESSAGE;
    }

    /**
     * Prints out the tasks
     * in the user's task list
     * @param tasks user's current tasklist
     */
    public String printOutAllTasks(TaskList tasks) {
        return tasks.printList();
    }

    /**
     * Prints out the marked task message
     * @param taskToMark task from user's current tasklist to be marked
     */
    public String printMarkTaskMessage(Task taskToMark) {
        return MARK_TASK_MESSAGE + taskToMark;
    }

    /**
     * Prints out the unmarked task message
     * @param taskToUnmark task from user's current tasklist to be unmarked
     */
    public String printUnmarkTaskMessage(Task taskToUnmark) {
        return UNMARK_TASK_MESSAGE + taskToUnmark;
    }

    /**
     * Prints out the add task message
     * @param taskList user's current tasklist
     * @param taskToAdd new task to be added to user's task list
     */
    public String printAddTaskMessage(TaskList taskList, Task taskToAdd) {
        return "Got it. I've added this task:\n  " + taskToAdd + "\nNow you have "
                + taskList.getSize() + " tasks in the list.";
    }

    /**
     * Prints out the delete task message
     * @param taskList user's current tasklist
     * @param taskToDelete task from the user's task list that was just deleted
     */
    public String printDeleteTaskMessage(TaskList taskList, Task taskToDelete) {
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
