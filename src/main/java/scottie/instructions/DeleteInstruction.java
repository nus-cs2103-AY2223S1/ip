package scottie.instructions;

import java.util.Map;

import scottie.tasks.Task;
import scottie.tasks.TaskList;
import scottie.ui.Ui;

/**
 * Encapsulates the delete instruction which is used to
 * delete a task from the task list.
 */
class DeleteInstruction extends Instruction {
    private static final String MISSING_TASK_NUMBER_MESSAGE = "Um... which task did you wanna delete?";
    private static final String INVALID_TASK_NUMBER_MESSAGE = "Hahaha very funny buddy. %s is not a legit task number.";
    private static final String TASK_NUMBER_OUT_OF_RANGE_MESSAGE = "Um... there's no task number %d... is there?";
    private static final String TASK_DELETED_MESSAGE = "Ok got it, I've deleted this task:";
    private static final String NO_MORE_TASKS_MESSAGE = "Wow! you have no more tasks left!";
    private static final String TASKS_LEFT_MESSAGE = "Looks like you have %d task%s left in your list.";

    /**
     * Constructs a DeleteInstruction with the given arguments.
     *
     * @param mainArgument The main argument provided to this instruction.
     * @param flagArgumentsMap The map between flags and values provided to
     *                         this instruction.
     */
    DeleteInstruction(String mainArgument, Map<String, String> flagArgumentsMap) {
        super(mainArgument, flagArgumentsMap);
    }

    /**
     * Deletes a task from the given TaskList.
     * The main argument of this instruction is interpreted as
     * the index of the Task to delete.
     *
     * @param taskList The TaskList to delete the task from.
     * @param ui The Ui used to display messages to the user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        if (!this.hasMainArgument()) {
            ui.showError(MISSING_TASK_NUMBER_MESSAGE);
            return;
        }
        int taskNum;
        try {
            taskNum = Integer.parseInt(this.getMainArgument());
        } catch (NumberFormatException e) {
            ui.showFormattedError(INVALID_TASK_NUMBER_MESSAGE, this.getMainArgument());
            return;
        }
        if (taskNum < 0) {
            ui.showFormattedError(INVALID_TASK_NUMBER_MESSAGE, this.getMainArgument());
            return;
        }
        if (taskNum == 0 || taskNum > taskList.size()) {
            ui.showFormattedError(TASK_NUMBER_OUT_OF_RANGE_MESSAGE, taskNum);
            return;
        }

        int taskId = taskNum - 1;
        Task task = taskList.getTask(taskId);
        ui.showMessages(TASK_DELETED_MESSAGE, task.toString());
        taskList.deleteTask(taskId);
        if (taskList.isEmpty()) {
            ui.showMessages(NO_MORE_TASKS_MESSAGE);
        } else {
            ui.showFormattedMessage(TASKS_LEFT_MESSAGE, taskList.size(), taskList.size() == 1 ? "" : "s");
        }
    }
}
