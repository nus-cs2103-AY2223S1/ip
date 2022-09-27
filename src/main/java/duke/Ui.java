package duke;

import duke.task.Task;

/**
 * The Ui class for Duke.
 *
 * It consists of methods that deals with interaction with users.
 *
 * CS2103T IP
 * AY22/23 Semester 1
 * @author Tan Jia Rong
 */
public class Ui {
    /**
     * Returns greetings message.
     *
     * @return greetings message.
     */
    public String greet() {
        return "Hello! I'm the Magical ChatBot, Duke!\n"
                + "What can I help you with today?";
    }

    /**
     * Returns farewell message.
     *
     * @return Farewell message.
     */
    public String bidFarewell() {
        return "Bye Bye! Hope to see you again soon!\n";
    }

    /**
     * Prints message on task completion.
     *
     * @param task Task that is completed.
     * @return Message on task completion.
     */
    public String printMarkTask(Task task) {
        assert isValidTask(task) : "printMarkTask Failed: Task is null";
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Prints message on task completion undo-ed.
     *
     * @param task Completed Task that is undo-ed.
     * @return Message on task completion undo-ed.
     */
    public String printUnmarkTask(Task task) {
        assert isValidTask(task) : "printUnmarkTask Failed: Task is null";
        return "Ok! I've marked this task as not done yet:\n" + task;
    }

    /**
     * Prints message on size of list.
     *
     * @param size The size of the list.
     * @return Message on size of list
     */
    public String printSizeOfList(int size) {
        return String.format("Now you have %d tasks in the list.\n", size);
    }

    /**
     * Prints message on current TaskList.
     *
     * @param tasks a string of tasks.
     * @return message on current TaskList.
     */
    public String printList(String tasks) {
        assert isValidTasks(tasks) : "printList Failed: List of tasks is empty";
        return "Here are the tasks in your list: \n" + tasks;
    }

    /**
     * Prints message of the list of matching tasks.
     *
     * @param tasks a string of tasks.
     * @return message on matching tasks.
     */
    public String printMatchedList(String tasks) {
        assert isValidTasks(tasks) : "printMatchedTask Failed: List of tasks is empty";
        return "Here are the matching tasks in your list: \n" + tasks;
    }

    /**
     * Prints message on task addition.
     *
     * @param task Task added.
     * @return Task addition message.
     */
    public String printAddTask(Task task) {
        assert isValidTask(task) : "printAddTask Failed: Task is null";
        return "Got it. I've added this task:\n" + task;
    }

    /**
     * Prints message on task deletion.
     *
     * @param task Task deleted.
     * @return Task deletion message.
     */
    public String printDeleteTask(Task task) {
        assert isValidTask(task) : "printDeleteTask Failed: Task is null";
        return "Noted. I've removed this task:\n" + task;
    }

    /**
     * Prints help message.
     *
     * @return List of available commands.
     */
    public String printHelpList() {
        String listHelpMessage = "List: List all tasks\n"
                + "Command: List\n";

        String findHelpMessage = "Find: Find tasks with matching keyword\n"
                + "Command: Find (keyword)\n";

        String markHelpMessage = "Mark: Set selected task as completed\n"
                + "Command: Mark (index of task)\n";

        String unmarkHelpMessage = "Unmark: Set selected task as not completed\n"
                + "Command: Unmark (index of task)\n";

        String deleteHelpMessage = "Delete: Delete selected task\n"
                + "Command: Delete (index of task)\n";

        String batchTypeDeleteHelpMessage = "BatchTypeDelete: Delete all tasks of type specified\n"
                + "Command: Batchtypedelete (type of task: todo/deadline/event)\n";

        String batchDescDeleteHelpMessage = "BatchTypeDelete: Delete all tasks with matching keyword\n"
                + "Command: Batchdescdelete (keyword)\n";

        String todoHelpMessage = "ToDo: Adds a Todo Task\n"
                + "Command: Todo (description)\n";

        String deadlineHelpMessage = "Deadline: Adds a Deadline Task\n"
                + "Command: Deadline (description) /by (YYYY-MM-DD 24hr)\n";

        String eventHelpMessage = "Event: Adds a Event Task\n"
                + "Command: Event (description) /at (YYYY-MM-DD 24hr)\n";

        String byeHelpMessage = "Bye: Exits the program\n"
                + "Command: Bye\n";

        StringBuilder sb = new StringBuilder();

        sb.append("Here are the list of available commands\n" + '\n');
        sb.append(listHelpMessage).append('\n');
        sb.append(markHelpMessage).append('\n');
        sb.append(unmarkHelpMessage).append('\n');
        sb.append(deleteHelpMessage).append('\n');
        sb.append(batchTypeDeleteHelpMessage).append('\n');
        sb.append(batchDescDeleteHelpMessage).append('\n');
        sb.append(todoHelpMessage).append('\n');
        sb.append(deadlineHelpMessage).append('\n');
        sb.append(eventHelpMessage).append('\n');
        sb.append(findHelpMessage).append('\n');
        sb.append(byeHelpMessage).append('\n');

        return sb.toString();
    }

    /**
     * Prints error message.
     *
     * @param message Error message to be printed
     * @return Error message
     */
    public String printErr(String message) {
        assert isValidMessage(message) : "printErr Failed: Message is empty";
        return message;
    }

    /**
     * Returns validity of a task
     *
     * @param task The specified task
     * @return true if valid task, false otherwise.
     */
    private boolean isValidTask(Task task) {
        return task != null;
    }

    /**
     * Returns validity of the list of tasks
     *
     * @param tasks The specified list of tasks
     * @return true if valid list of tasks, false otherwise.
     */
    private boolean isValidTasks(String tasks) {
        return tasks != null;
    }

    /**
     * Returns validity of the message
     *
     * @param message The specified message
     * @return true if valid message, false otherwise.
     */
    private boolean isValidMessage(String message) {
        return !message.isEmpty();
    }
}
