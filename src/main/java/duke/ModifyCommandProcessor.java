package duke;

public class ModifyCommandProcessor {
    private final static String TASK_COMPLETE = "done";
    private final static String TASK_INCOMPLETE = "not done";

    private static int getContentId(String content) {
        int index = content.indexOf(' ');
        if (index > 0) {
            return Integer.parseInt(content.substring(0, index));
        }
        return Integer.parseInt(content);
    }

    protected static String processModifyCommand(String keyword, String content, TaskList tasks, Ui ui) {
        int index = getContentId(content) - 1;
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
    }
}
