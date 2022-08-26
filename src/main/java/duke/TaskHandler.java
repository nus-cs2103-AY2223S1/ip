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
     */
    static void addTask(String input) {
        TaskList.add(input);
    }

    /**
     * Method to delete an item from the list.
     *
     * @param input Input to be deleted from the list.
     */
    static void deleteTask(String input) {
        TaskList.delete(input);
    }

    /**
     * Method to mark an item in the list.
     *
     * @param input Input to be marked in the list.
     */
    public void markChild(String input) {
        int index = Integer.parseInt(input.substring(5)) - 1;
        taskList.markChild(index);
    }

    /**
     * Method to unmark an item in the list.
     *
     * @param input Input to be unmarked in the list.
     */
    public void unmarkChild(String input) {
        int index = Integer.parseInt(input.substring(7)) - 1;
        taskList.unmarkChild(index);
    }

    public void findTask(String input) {
        String formattedInput = input.substring(5);
        taskList.find(formattedInput);
    }
}
