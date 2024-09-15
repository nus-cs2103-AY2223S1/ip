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
     * Returns quitting message as a String
     * when the user exits the Duke program
     * @return quit message as a String
     */
    public String quit() {
        return QUIT_MESSAGE;
    }

    /**
     * Returns String containing the tasks
     * in the user's task list
     * @param tasks user's current tasklist
     * @return all tasks as a String
     */
    public String listAllTasks(TaskList tasks) {
        return tasks.printList();
    }

    /**
     * Returns marked task message as a String
     * @param taskToMark task from user's current tasklist to be marked
     * @return mark task message as a String
     */
    public String markTask(Task taskToMark) {
        return MARK_TASK_MESSAGE + taskToMark;
    }

    /**
     * Returns unmarked task message as a String
     * @param taskToUnmark task from user's current tasklist to be unmarked
     * @return unmark task message as a String
     */
    public String printUnmarkTaskMessage(Task taskToUnmark) {
        return UNMARK_TASK_MESSAGE + taskToUnmark;
    }

    /**
     * Returns add task message as a String
     * @param taskList user's current tasklist
     * @param taskToAdd new task to be added to user's task list
     * @return add task message as a String
     */
    public String printAddTaskMessage(TaskList taskList, Task taskToAdd) {
        return "Got it. I've added this task:\n  " + taskToAdd + "\nNow you have "
                + taskList.getSize() + " tasks in the list.";
    }

    /**
     * Returns the delete task message as a String
     * @param taskList user's current tasklist
     * @param taskToDelete task from the user's task list that was just deleted
     * @return delete task message as a String
     */
    public String printDeleteTaskMessage(TaskList taskList, Task taskToDelete) {
        return "Noted. I've removed this task:\n  " + taskToDelete + "\nNow you have "
                + taskList.getSize() + " tasks in the list";
    }

    /**
     * Returns all the tasks with the user's search keyword as a String
     * @param taskListWithKeyword List of tasks containing user's search keyword
     * @return List of tasks with user's search keyword as a String
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
