package uwu.uwu;

import uwu.task.Task;
import uwu.task.TaskList;

/**
 * Text UI of the application.
 */
public class Ui {
    /**
     * Prints greetings to users.
     */
    public String greetUsers() {
        String greetings = "hellu!\ni am oo woo <:\nhow can i be of service today?";
        return greetings;
    }

    /**
     * Prints farewell words to users.
     */
    public String leaveChat() {
        String farewellWords = "good work today!\nhope to see you again soon~";
        return farewellWords;
    }

    /**
     * Tells user that their task has been added to the task list.
     *
     * @param task The task to be added.
     * @param tasksLength The length of the task list.
     */
    public String addTask(Task task, int tasksLength) {
        String addToDoString = "oo new task! ^^"
                + "\n added: " + task.toString()
                + "\nyou have " + String.valueOf(tasksLength) + " task(s) <:";
        return addToDoString;
    }

    /**
     * Displays the tasks in the task list.
     *
     * @param tasks The stored TaskList.
     */
    public String listTasks(TaskList tasks) {
        if (tasks.size() == 0) {
            return "looks like there are no tasks on your list uwu"
                    + "\nfeed me a task to get started~ <:";
        } else {
            return "here are your tasks~ you've got this!" + tasks.taskListToString();
        }
    }

    /**
     * Tells user that their task has been marked.
     *
     * @param task The marked task.
     */
    public String markTask(Task task) {
        String markedAsDone = "yey! good job~ keep it up <3";
        return markedAsDone + "\n\t" + task.toString();
    }

    /**
     * Tells user that their task has been unmarked.
     *
     * @param task The unmarked task.
     */
    public String unmarkTask(Task task) {
        String unmarked = "keep going~";
        return unmarked + "\n\t" + task.toString();
    }

    /**
     * Tells user that their task has been deleted.
     *
     * @param task The deleted task.
     * @param tasksLength The length of the task list.
     */
    public String deleteTask(Task task, int tasksLength) {
        return "removing this task from your list...\n\t"
                + task.toString()
                + "\ntask removed~! now you have " + String.valueOf(tasksLength) + " task(s) <:";
    }

    /**
     * Displays the error encountered during execution.
     *
     * @param e The exception message.
     */
    public String showError(String e) {
        return e;
    }

    /**
     * Displays the tasks found containing the keyword.
     *
     * @param tasks The tasks containing the keyword.
     * @return The list of tasks containing the keyword.
     */
    public String findTask(TaskList tasks) {
        if (tasks.size() == 0) {
            return "hm...looks like there are no tasks matching the keyword ><\ntry another one~";
        } else {
            return "here are the tasks i found that match your keyword~ <:" + tasks.taskListToString();
        }
    }
}
