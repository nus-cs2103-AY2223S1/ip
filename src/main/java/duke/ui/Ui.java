package duke.ui;

import duke.task.Task;

/**
 * Ui class that handles showing the user texts.
 *
 * @author Elgin
 */
public class Ui {
    /**
     * Gets a goodbye message.
     *
     * @return Goodbye message.
     */
    public String getByeMsg() {
        return "Bye. Hope to see you again soon!\n" + "Application closing in 3 seconds...";
    }

    /**
     * Gets a message saying that task is successfully added.
     *
     * @param newTaskCount The number of tasks after addition.
     * @param task The task that is added by Duke.
     * @return Message conveying that task has successfully been added, and the current number of tasks.
     */
    public String getTaskAddedMsg(int newTaskCount, Task task) {
        return "Got it. I've added this task:\n" + "  " + task + "\n" + "Now you have "
                + newTaskCount + " tasks in the list.";
    }

    /**
     * Gets a message for successfully deleting a task.
     *
     * @param newTaskCount The number of tasks left after deletion.
     * @param taskDescription The string representation of the task that is deleted.
     * @return Message conveying that user that has successfully deleted a task, and the number of tasks left
     */
    public String getTaskDeletedMsg(int newTaskCount, String taskDescription) {
        return "Noted. I've removed this task:\n  " + taskDescription + "\n" + "Now you have "
                + newTaskCount + " tasks in the list";
    }

    /**
     * Gets a message signaling a task has been successfully marked.
     *
     * @param taskDescription The description of the task marked.
     * @return Message saying that a task has been marked.
     */
    public String getTaskMarkedMsg(String taskDescription) {
        return "Nice! I've marked this task as done:\n" + taskDescription;
    }

    /**
     * Gets a message for a successful task unmarked.
     *
     * @param taskDescription The description of the task unmarked.
     * @return Message signaling a successful task unmarked.
     */
    public String getTaskUnmarkedMsg(String taskDescription) {
        return "OK, I've marked this task as not done yet:\n" + taskDescription;
    }

    /**
     * Gets a message that lists all tasks for Duke.
     *
     * @param allTasks String representation of allTasks.
     * @return Message of all tasks.
     */
    public String getAllTasksMsg(String allTasks) {
        if (allTasks.trim().isEmpty()) {
            return "There are no tasks yet...";
        }

        assert !allTasks.isEmpty() : "Tasks should not be empty";

        return "Here are the tasks in your list\n" + allTasks;
    }

    /**
     * Gets task file loading error.
     *
     * @return String representation of loading error.
     */
    public String getTaskLoadingErrorMsg() {
        return "OOPS!!! Failed to load tasks because file cannot be opened!";
    }

    /**
     * Gets loanbook file loading error.
     *
     * @return String representation of loading error.
     */
    public String getLoanbookLoadingErrorMsg() {
        return "OOPS!!! Failed to load loanbook because file cannot be opened!";
    }

    /**
     * Formats a Duke error message.
     *
     * @param error Error message.
     * @return Formatted duke error message.
     */
    public String formatDukeErrorMsg(String error) {
        return "OOPS!!! " + error;
    }

    /**
     * Gets a date parsing error message (Invalid date format).
     *
     * @return Formatted invalid date error message.
     */
    public String getDateErrorMsg() {
        return "OOPS!!! Your date format has to be in the form 'yyyy-mm-dd', and time has to be in the form '2359'";
    }

    /**
     * Gets String to Number cast error message (because user did not input a number).
     *
     * @return Formatted invalid number error message.
     */
    public String getNumberCastErrorMsg() {
        return "OOPS!!! Please input a valid index (i.e. a number)";
    }

    /**
     * Gets all search results message for Duke.
     *
     * @param searchResults String representation of all string results.
     * @return String representation of all search results.
     */
    public static String getSearchResultsMsg(String searchResults) {
        return "Here are the matching tasks in your list:\n" + searchResults;
    }

    /**
     * Gets a contact added message for Duke.
     *
     * @param addedContact The contact that is added.
     * @return Message representing contact has been successfully added.
     */
    public static String getContactAddedMsg(String addedContact) {
        return "Yes sir! I have added " + addedContact + " into the loanbook";
    }

    /**
     * Gets a string representation of all contacts in Loanbook.
     *
     * @param loanbookContacts String representation of all contacts in Loanbook.
     * @return Message to the user (everything in the loanbook).
     */
    public static String getLoanbookContacts(String loanbookContacts) {
        if (loanbookContacts.isEmpty()) {
            return "No records are found in the loanbook...";
        }

        return "These are all the results! It is very accurate\n"
                + loanbookContacts;
    }

    /**
     * Gets contact deleted successful message.
     *
     * @param nameToRemove The name of the contact to remove.
     * @return Message for successful delete.
     */
    public static String getContactDeletedMsg(String nameToRemove) {
        return "Alright! I've just removed this contact: \n"
                + nameToRemove;
    }
}
