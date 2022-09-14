package scottie.instructions;

import java.util.Map;

import scottie.tasks.TaskList;
import scottie.ui.Ui;

/**
 * Encapsulates the mark instruction which is used to
 * mark a task as done.
 */
class MarkInstruction extends Instruction {
    private static final String MISSING_TASK_NUMBER_MESSAGE = "Um... which task did you wanna mark?";
    private static final String INVALID_TASK_NUMBER_MESSAGE = "Hahaha very funny buddy. %s is not a legit task number.";
    private static final String TASK_NUMBER_OUT_OF_RANGE_MESSAGE = "Um... there's no task number %d... is there?";
    private static final String TASK_ALREADY_MARKED_MESSAGE = "Um... task %d is already marked as done, buddy.";
    private static final String TASK_MARKED_MESSAGE = "Nice! I'll mark task %d as done:";

    /**
     * Constructs a MarkInstruction with the given arguments.
     *
     * @param mainArgument The main argument provided to this instruction.
     * @param flagArgumentsMap The map between flags and values provided to
     *                         this instruction.
     */
    MarkInstruction(String mainArgument, Map<String, String> flagArgumentsMap) {
        super(mainArgument, flagArgumentsMap);
    }

    /**
     * Marks a Task in the given TaskList as done.
     * The main argument of this instruction is interpreted as
     * the index of the task to mark.
     *
     * @param taskList The TaskList containing the Task to mark.
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
        if (taskList.isMarked(taskId)) {
            ui.showFormattedError(TASK_ALREADY_MARKED_MESSAGE, taskNum);
            return;
        }

        taskList.markTask(taskId);
        ui.showFormattedMessage(TASK_MARKED_MESSAGE, taskNum);
        ui.showMessages(taskList.getTask(taskId).toString());
    }
}
