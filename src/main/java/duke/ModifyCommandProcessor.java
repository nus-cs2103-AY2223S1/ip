package duke;

public class ModifyCommandProcessor {
    private final static String TASK_COMPLETE = "done";
    private final static String TASK_INCOMPLETE = "not done";

    private static int getContentId(String content) {
        return Integer.parseInt(content);
    }

    /**
     * Processes a given Modify Command based on its keyword and performs the corresponding action to the keyword.
     *
     * @param keyword keyword of Modify Command.
     * @param content body of Modify Command.
     * @param tasks current TaskList of tasks.
     * @param ui Ui of Duke.
     * @return String result which describes how a task was modified.
     * @throws DukeException Task not found within current TaskList of tasks.
     */
    protected static String processModifyCommand(
            String keyword, String content, TaskList tasks, Ui ui) throws DukeException {
        int index = getContentId(content) - 1;
        try {
            if (keyword.equals("mark")) {
                Task updatedTask = tasks.markTask(index);
                return ui.updateTask(updatedTask, TASK_COMPLETE);
            } else if (keyword.equals("unmark")) {
                Task updatedTask = tasks.unmarkTask(index);
                return ui.updateTask(updatedTask, TASK_INCOMPLETE);
            } else {
                Task deletedTask = tasks.delete(index);
                return ui.deleteTaskConfirmation(deletedTask, tasks.getSize());
            }
        } catch (IndexOutOfBoundsException ex) {
                throw new DukeException(String.format("The task at position %s does not exist.", content));
        }
    }
}
