package scottie.instructions;

import java.util.Map;

import scottie.tasks.TaskList;
import scottie.ui.Ui;

/**
 * Encapsulates the unmark instruction which is used to
 * mark a task as not done.
 */
class UnmarkInstruction extends Instruction {
    private static final String MISSING_TASK_NUMBER_MESSAGE = "Um... which task did you wanna unmark?";
    private static final String INVALID_TASK_NUMBER_MESSAGE = "Hahaha very funny buddy. %s is not a legit task number.";
    private static final String TASK_NUMBER_OUT_OF_RANGE_MESSAGE = "Um... there's no task number %d... is there?";
    private static final String TASK_ALREADY_UNMARKED_MESSAGE = "Um... task %d is already marked as not done, buddy.";
    private static final String TASK_UNMARKED_MESSAGE = "Aww man, ok guess I'll mark task %d as not done:";

    /**
     * Constructs a UnmarkInstruction with the given arguments.
     *
     * @param mainArgument The main argument provided to this instruction.
     * @param flagArgumentsMap The map between flags and values provided to
     *                         this instruction.
     */
    UnmarkInstruction(String mainArgument, Map<String, String> flagArgumentsMap) {
        super(mainArgument, flagArgumentsMap);
    }

    /**
     * Marks a task in the given TaskList as not done.
     * The main argument of this instruction is interpreted as
     * the index of the task to unmark.
     *
     * @param taskList The TaskList containing the task to unmark.
     * @param ui The Ui used to display messages to the user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        if (!this.hasMainArgument()) {
            ui.showError(MISSING_TASK_NUMBER_MESSAGE);
            return;
        }
        int taskNum;
        int taskId;
        try {
            taskNum = Integer.parseInt(this.getMainArgument());
            taskId = taskNum - 1;
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
        if (!taskList.isMarked(taskId)) {
            ui.showFormattedError(TASK_ALREADY_UNMARKED_MESSAGE, taskNum);
            return;
        }

        taskList.unmarkTask(taskId);
        ui.showFormattedMessage(TASK_UNMARKED_MESSAGE, taskNum);
        ui.showMessages(taskList.getTask(taskId).toString());
    }
}
