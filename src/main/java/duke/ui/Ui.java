package duke.ui;

import java.util.List;

import duke.task.Task;

/**
 * An Ui class that encapsulates the ui of Duke.
 */
public class Ui {

    private StringBuilder response;

    public Ui() {
        response = new StringBuilder();
    }

    /**
     * cleans the response
     */
    public void resetResponse() {
        response = new StringBuilder();
    }

    /**
     * To get the response from Duke
     * @return Response
     */
    public String getResponse() {
        return response.toString();
    }

    /**
     * A simple method to append multiple string to StringBuilder
     * @param strings strings that need to be appended
     */
    public void appendToResponse(String... strings) {
        for (String message : strings) {
            response.append(message).append("\n");
        }
    }

    /**
     * Shows the termination message
     */
    public void showBye() {
        appendToResponse("Bye. See you next time.");
    }

    /**
     * Shows the error message
     *
     * @param errorMessage the error message
     */
    public void showError(String errorMessage) {
        appendToResponse(errorMessage);
    }

    /**
     * Shows the task that is being marked or unmarked
     *
     * @param task the task
     */
    public void showTask(Task task) {
        if (task.isDone()) {
            appendToResponse("Nice! I've marked this task as done:", task.toString());
        } else {
            appendToResponse("Nice! I've marked this task as not done yet:", task.toString());
        }
    }

    /**
     * Shows user has entered an out of bound index.
     * @param remainingTasks number of tasks in the list
     */
    public void showIndexOutOfBound(int remainingTasks) {
        appendToResponse("There are " + remainingTasks + " tasks in the list",
                    "The index you have entered is not within the range.");
    }

    /**
     * Shows the task that is being deleted
     *
     * @param task                the task
     * @param numOfRemainingTasks the number of remaining tasks in the list
     */
    public void showDeletedTask(Task task, Integer numOfRemainingTasks) {
        String remainder = String.format("Now you have %d tasks in the list.", numOfRemainingTasks);
        appendToResponse("Noted. I've removed this task:", task.toString(), remainder);
    }

    /**
     * Shows the task that is being added.
     *
     * @param task                the task
     * @param numOfRemainingTasks the number if tasks in the list
     */
    public void showAddTask(Task task, Integer numOfRemainingTasks) {
        String remainder = String.format("Now you have %d tasks in the list.", numOfRemainingTasks);
        appendToResponse("Got it. I've added this task:", task.toString(), remainder);
    }

    /**
     * Shows the command the Duke support
     */
    public void showHelpMenu() {
        appendToResponse("List of commands available:\n",
                    "todo <description>",
                    "deadline <description> /by <date and time>",
                    "event <description> /by <date and time>",
                    "find <keyword>",
                    "mark <index>",
                    "unmark <index>",
                    "delete <index>",
                    "edit <index> <new task>",
                    "list",
                    "help",
                    "bye");
    }

    /**
     * Shows all the tasks in the list
     * @param taskList the list
     */
    public void showList(List<Task> taskList) {
        String[] strArr = new String[taskList.size() + 1];
        strArr[0] = "Here are the tasks in your list:";
        for (int i = 1; i < strArr.length; i++) {
            strArr[i] = i + ". " + taskList.get(i - 1);
        }
        appendToResponse(strArr);
    }

    /**
     * Shows all the tasks that has been found
     * @param taskList all the tasks
     */
    public void showFindTask(List<Task> taskList) {
        String[] strArr = new String[taskList.size() + 1];
        strArr[0] = "Here are the matching tasks in your list:";
        for (int i = 1; i < strArr.length; i++) {
            strArr[i] = i + ". " + taskList.get(i - 1);
        }
        appendToResponse(strArr);
    }

    /**
     * Shows successful reschedule message
     * @param beingRescheduled task being rescheduled
     * @param rescheduledTask the new task
     */
    public void showEditedMessage(Task beingRescheduled, Task rescheduledTask) {
        appendToResponse("Nice! I've edited this task",
                    beingRescheduled.toString(),
                    "To",
                    rescheduledTask.toString());
    }
}
