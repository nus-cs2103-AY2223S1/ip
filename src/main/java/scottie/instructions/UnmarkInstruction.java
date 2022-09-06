package scottie.instructions;

import java.util.Map;

import scottie.tasks.TaskList;
import scottie.ui.Ui;

/**
 * Encapsulates the unmark instruction which is used to
 * mark a task as not done.
 */
class UnmarkInstruction extends Instruction {
    private static final String MISSING_TASK_NUMBER_MESSAGE = "Sorry, you need to tell me which task to unmark.";
    private static final String INVALID_TASK_NUMBER_MESSAGE = "Sorry, %s is not a valid task number.%n";
    private static final String TASK_NUMBER_OUT_OF_RANGE_MESSAGE = "Sorry, there is no task number %d.%n";
    private static final String TASK_UNMARKED_MESSAGE = "Sure, I've marked task %d as not done:%n";

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
            ui.showMessages(MISSING_TASK_NUMBER_MESSAGE);
            return;
        }
        int taskNum;
        try {
            taskNum = Integer.parseInt(this.getMainArgument());
        } catch (NumberFormatException e) {
            ui.showFormattedMessage(INVALID_TASK_NUMBER_MESSAGE, this.getMainArgument());
            return;
        }
        if (taskNum <= 0 || taskNum > taskList.size()) {
            ui.showFormattedMessage(TASK_NUMBER_OUT_OF_RANGE_MESSAGE, taskNum);
            return;
        }

        taskList.unmarkTask(taskNum - 1);
        ui.showFormattedMessage(TASK_UNMARKED_MESSAGE, taskNum);
        ui.showMessages(taskList.getTask(taskNum - 1).toString());
    }
}
