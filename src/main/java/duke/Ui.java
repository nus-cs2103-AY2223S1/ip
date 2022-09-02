package duke;

/**
 * The Class that deals with user interactions.
 *
 * @author Denzel Tan
 */
public class Ui {
    /**
     * Prints the end message.
     */
    public static String sayGoodbye() {
        return "Bye. Hope to see you again soon!";
    }


    /**
     * Prints out the message when a task has been marked as done.
     *
     * @param task the task to be marked as done
     */
    public static String markedDoneMessage(Task task) {
        return "Nice! I've marked this task as done:\n  " + task.toString();
    }


    /**
     * Prints out the message when a task has been marked as undone.
     *
     * @param task the task to be marked as undone
     */
    public static String markUndoneMessage(Task task) {
        return "OK, I've marked this task as not done yet:\n  " + task.toString();
    }

    /**
     * Prints the current task.
     *
     * @param tasks the list of tasks to print
     */
    public static String printList(TaskList tasks) {
        return "Here is your current task list:\n" + tasks;
    }

    /**
     * Print the starting message output when finding tasks.
     */
    public static String printFoundTasksStart(String str, TaskList tasks) {
        StringBuilder res = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            if (currTask.toString().contains(str)) {
                res.append(currTask).append("\n");
            }
        }
        return res.toString();
    }
}
