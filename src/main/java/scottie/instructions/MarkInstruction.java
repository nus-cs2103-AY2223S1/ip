package scottie.instructions;

import java.util.Map;

import scottie.tasks.TaskList;
import scottie.ui.Ui;

/**
 * Encapsulates the mark instruction which is used to
 * mark a task as done.
 */
class MarkInstruction extends Instruction {
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
        if (this.hasMainArgument()) {
            int taskNum = Integer.parseInt(this.getMainArgument());
            taskList.markTask(taskNum - 1);
            ui.showFormattedMessage("Well done! I've marked task %d as done:%n", taskNum);
            ui.showMessages(taskList.getTask(taskNum - 1).toString());
        } else {
            ui.showMessages("Sorry, you need to tell me which task to mark.");
        }
    }
}
