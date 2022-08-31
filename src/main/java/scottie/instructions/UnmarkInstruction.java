package scottie.instructions;

import java.util.Map;

import scottie.tasks.TaskList;
import scottie.ui.Ui;

/**
 * Encapsulates the unmark instruction which is used to
 * mark a task as not done.
 */
class UnmarkInstruction extends Instruction {
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
        if (this.hasMainArgument()) {
            int taskNum = Integer.parseInt(this.getMainArgument());
            taskList.unmarkTask(taskNum - 1);
            ui.showFormattedMessage("Sure, I've marked task %d as not done:%n", taskNum);
            ui.showMessages(taskList.getTask(taskNum - 1).toString());
        } else {
            ui.showMessages("Sorry, you need to tell me which task to unmark.");
        }
    }
}
