package duke;

/**
 * Represents the parser to handle commands.
 */
public class TaskHandler {
    private TaskList taskList;
    private Ui ui;

    /**
     * Default constructor for TaskHandler.
     *
     * @param taskList TaskList to edit the list.
     */

    public TaskHandler(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Method to add an item to the list.
     *
     * @param input Input to be added to the list.
     * @return
     */
    static String addTask(String input) {
        return TaskList.add(input);
    }

    /**
     * Method to delete an item from the list.
     *
     * @param input Input to be deleted from the list.
     */
    static String deleteTask(String input) {
        return TaskList.delete(input);
    }

    /**
     * Method to mark an item in the list.
     *
     * @param input Input to be marked in the list.
     */
    public String markChild(String input) {
        int index = Integer.parseInt(input.substring(5)) - 1;
        return taskList.markChild(index);
    }

    /**
     * Method to unmark an item in the list.
     *
     * @param input Input to be unmarked in the list.
     */
    public String unmarkChild(String input) {
        int index = Integer.parseInt(input.substring(7)) - 1;
        return taskList.unmarkChild(index);
    }

    public String findTask(String input) {
        String formattedInput = input.substring(5);
        return taskList.find(formattedInput);
    }
}
