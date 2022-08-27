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

    private final static String WELCOME = "Hello! I'm Seaward,\n" +
            "your friendly neighbourhood chatbot.\n" +
            "Type something and I will reply!";
    private final static String BYE = "Seaward out!";
    private final static String EMPTY_TASK_MESSAGE = "You currently have no tasks. Add some!";
    private final static String MARKED_TASK_MESSAGE = "I have marked this task as done:\n";
    private final static String UNMARKED_TASK_MESSAGE = "I have marked this task as undone:\n";

    /**
     * Returns a welcome message when Seaward is first running.
     * Also, it prompts the user to enter a command in.
     * @return A welcome message.
     */
    public String getWelcome() {
        return WELCOME;
    }

    /**
     * Returns a goodbye message when a bye command is received by Seaward.
     * @return A goodbye message.
     */
    public String getByeMessage() {
        return BYE;
    }

    /**
     * Returns a message informing the user that there are no tasks in their list.
     * @return A empty list message.
     */
    public String getEmptyTaskMessage() {
        return EMPTY_TASK_MESSAGE;
    }

    /**
     * Returns a list containing all of the tasks that the user has given.
     * @param taskList A list containing all the tasks.
     * @return A list of the tasks.
     */
    public String getList(TaskList taskList) {
        String list = "";
        for (int i = 0; i < taskList.getNumOfTasks(); i++) {
            int index = i + 1;
            String taskDescription = taskList.readTask(i);
            if (index == taskList.getNumOfTasks()) {
                list = list + index + "." + taskDescription;
            } else {
                list = list + index + "." + taskDescription + "\n";
            }
        }
        return list;
    }

    /**
     * Returns a success message informing the user that the task has been marked.
     * @return A success message.
     */
    public String getMarkedTaskMessage() {
        return MARKED_TASK_MESSAGE;
    }

    /**
     * Returns a success message informing the user that the task has been unmarked.
     * @return A success message.
     */
    public String getUnmarkedTaskMessage() {
        return UNMARKED_TASK_MESSAGE;
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
     * @return A success mesaage.
     */
    public String getAddTaskMessage(String description, int numOfTasks) {
        return "Noted. I have added:\n" + description + "\n"
                + "Now you have "
                + numOfTasks + " task(s) in your list.";
    }
}
