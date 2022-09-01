package duke.command;

/**
 * The Ui Class encapsulates all the input and output interaction between Duke and user.
 */
public class ResponseWrapper {

    /**
     * Initializes an instance of Ui.
     */
    protected ResponseWrapper() {
    }

    /**
     * Returns the response of list command using the list of task in the taskList.
     *
     * @param taskList Tasks to be displayed.
     */
    protected String getListResponse(String taskList) {
        return "Here are the tasks in your list:\n" + taskList;
    }

    protected String getFindResponse(String taskList) {
        return "Here are the matching tasks in your list:\n" + taskList;
    }

    /**
     * Returns the response after successfully adding a task to the list of task.
     *
     * @param taskDesc String representation of the task.
     * @param totalTask Number of saved task.
     */
    protected String getAddTaskResponse(String taskDesc, int totalTask) {
        return "Got it. I've added this task:\n\t" + taskDesc
                + "\nNow you have " + totalTask + " tasks in the list.";
    }

    /**
     * Returns the response after successfully marking a task as done.
     *
     * @param taskDesc String representation of the task.
     */
    protected String getMarkResponse(String taskDesc) {
        return "Nice! I've marked this task as done:\n\t" + taskDesc;
    }

    /**
     * Returns the response after successfully marking a task as not done.
     *
     * @param taskDesc String representation of the task.
     */
    protected String getUnmarkResponse(String taskDesc) {
        return "OK, I've marked this task as not done yet:\n\t" + taskDesc;
    }

    /**
     * Returns the response after successfully deleting a task.
     *
     * @param taskDesc String representation of the task.
     */
    protected String getDeleteResponse(String taskDesc, int totalTask) {
        return "Noted. I've removed this task:\n\t" + taskDesc +
                "\nNow you have " + totalTask + " tasks in the list.";
    }

}
