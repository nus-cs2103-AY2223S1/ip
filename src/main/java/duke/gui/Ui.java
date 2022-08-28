package duke.gui;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents the Ui object responsible for printing messages to the graphic user interface.
 *
 * @author njxue
 * @version v0.1
 */
public class Ui {
    private enum Divider {
        THICK_DIVIDER("===========================\n"),
        THIN_DIVIDER("___________________________________\n");

        private String divider;

        /**
         * Returns a divider.
         * @param divider String representation of the divider.
         */
        private Divider(String divider) {
            this.divider = divider;
        }

        /**
         * Returns the string representation of the divider.
         * @return String representation of the divider.
         */
        @Override
        public String toString() {
            return divider;
        }
    }

    /** Left padding to a string for pretty printing. */
    private String leftPadding = "   ";

    /**
     * Returns the greeting message when the application is launched.
     */
    public String greetingMessage() {
        String logo = "    __                    \n"
                + ".--|  |.-----.-----.-----.\n"
                + "|  _  ||  _  |  _  |  -__|\n"
                + "|___||___|__  |___|\n"
                + "         |______|      \n";
        String greeting = "Henlo! I'm \n" + addBetweenDividers(Divider.THICK_DIVIDER, logo) + "How may I assist you?";
        return greeting;
    }

    /**
     * Returns the success message when a task has been successfully added.
     *
     * @param task Newly added Task object.
     * @param tasks TaskList to add the new Task into.
     */
    public String taskAddedMessage(Task task, TaskList tasks) {
        return String.format("Got it. I've added this task ^_^: \n%s\n%s",
                addBetweenDividers(Divider.THIN_DIVIDER, getIndentedTask(task)), getNumTasks(tasks));
    }

    /**
     * Returns the success message when a task has been successfully deleted.
     *
     * @param task Removed Task object.
     * @param tasks TaskList to remove the new Task from.
     */
    public String taskDeletedMessage(Task task, TaskList tasks) {
        return String.format("Okie. I've deleted this task >_>: \n%s\n%s",
                addBetweenDividers(Divider.THIN_DIVIDER, getIndentedTask(task)), getNumTasks(tasks));
    }

    /**
     * Returns the success message when a task has been successfully marked.
     *
     * @param task Marked Task object.
     * @param tasks TaskList containing the target Task to mark.
     */
    public String taskMarkedMessage(Task task, TaskList tasks) {
        return String.format("Sure! I've marked this task as done ^O^: \n%s\n%s",
                addBetweenDividers(Divider.THIN_DIVIDER, getIndentedTask(task)), getNumTasks(tasks));
    }

    /**
     * Returns the success message when a task has been successfully unmarked.
     *
     * @param task Unmarked Task object.
     * @param tasks TaskList containing the target Task to unmark.
     */
    public String taskUnmarkedMessage(Task task, TaskList tasks) {
        return String.format("Sure! I've unmarked this task as done ^O^: \n%s\n%s",
                addBetweenDividers(Divider.THIN_DIVIDER, getIndentedTask(task)), getNumTasks(tasks));
    }

    /**
     * Returns the number of tasks in a TaskList.
     *
     * @param tasks Target TaskList
     */
    private String getNumTasks(TaskList tasks) {
        return "You have [" + tasks.size() + "] tasks in the list O_O";
    }

    /**
     * Returns the string representation of a Task, left padded with three spaces.
     */
    private String getIndentedTask(Task task) {
        return leftPadding + task;
    }

    /**
     * Returns the string representation of the TaskList.
     *
     * @param tasks Target TaskList
     * @return String representation of the TaskList.
     */
    public String getPrettyTaskList(TaskList tasks) {
        return addBetweenDividers(Divider.THICK_DIVIDER, tasks.toString());
    }

    /**
     * Returns a string that is between two upper and lower dividers.
     *
     * @param divider Divider sandwiching the given string.
     * @param string String to be put between the dividers.
     * @return String that is between two upper and lower dividers.
     */
    public String addBetweenDividers(Divider divider, String string) {
        return String.format("%s\n%s\n%s", divider, string, divider);
    }
}
