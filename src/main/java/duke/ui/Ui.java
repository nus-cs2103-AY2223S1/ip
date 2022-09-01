package duke.ui;

import duke.tasklist.TaskList;

/**
 * Represents the interactions between the user and the chatbot Seaward.
 * A <code>Ui</code> object corresponds to a user interface that
 * interacts with the user input through replying on the user interface
 * with the corresponding commands given in the input.
 * The Ui's name is Seaward.
 */

public class Ui {
    /**
     * Returns a message informing the user that there are no tasks in their list.
     * @return A empty list message.
     */
    public String getEmptyTaskMessage() {
        return "You currently have no tasks. Add some!";
    }

    /**
     * Returns a list containing all of the tasks that the user has given.
     * @param taskList A list containing all the tasks.
     * @return A list of the tasks.
     */
    public String getList(TaskList taskList) {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < taskList.getNumOfTasks(); i++) {
            int index = i + 1;
            String taskDescription = taskList.readTask(i);
            if (index == taskList.getNumOfTasks()) {
                list.append(index).append(".").append(taskDescription);
            } else {
                list.append(index).append(".").append(taskDescription).append("\n");
            }
        }
        return list.toString();
    }

    /**
     * Returns a success message informing the user that the task has been marked.
     * @return A success message.
     */
    public String getMarkedTaskMessage() {
        return "I have marked this task as done:\n";
    }

    /**
     * Returns a success message informing the user that the task has been unmarked.
     * @return A success message.
     */
    public String getUnmarkedTaskMessage() {
        return "I have marked this task as undone:\n";
    }

    /**
     * Returns a success message informing the user that they have deleted a task.
     * @param description Description of the task
     * @param numOfTasks Number of tasks remaining in the list.
     * @return A success message.
     */
    public String getDeleteMessage(String description, int numOfTasks) {
        return "Noted. I have removed this task:\n" + description
                + "\n" + "Now you have "
                + numOfTasks + " task(s) in your list.";
    }

    /**
     * Returns a success message informing the user that they have added a task.
     * @param description Description of the task.
     * @param numOfTasks Number of tasks in the list after adding the task.
     * @return A success message.
     */
    public String getAddTaskMessage(String description, int numOfTasks) {
        return "Noted. I have added:\n" + description + "\n"
                + "Now you have "
                + numOfTasks + " task(s) in your list.";
    }

    /**
     * Returns a failure message informing the user that there are no matching tasks in the tasklist.
     * @return A failure message.
     */
    public String getNoMatchingTaskMessage() {
        return "Sorry! There are no matching tasks that contains ";
    }

    /**
     * Returns a success message informing the user that there is a matching task in the tasklist.
     * @return A success message.
     */
    public String getMatchingTaskMessage() {
        return "Here are the matching tasks in your list:";
    }
}
