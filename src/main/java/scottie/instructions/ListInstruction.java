package scottie.instructions;

import java.util.Map;

import scottie.tasks.TaskList;
import scottie.ui.Ui;

/**
 * Encapsulates the list instruction which is used to
 * display the list of tasks.
 */
class ListInstruction extends Instruction {
    /**
     * Constructs a ListInstruction with the given arguments.
     *
     * @param mainArgument The main argument provided to this instruction.
     * @param flagArgumentsMap The map between flags and values provided to
     *                         this instruction.
     */
    ListInstruction(String mainArgument, Map<String, String> flagArgumentsMap) {
        super(mainArgument, flagArgumentsMap);
    }

    /**
     * Displays the list of tasks in the given TaskList to the user.
     *
     * @param taskList The TaskList to display to the user.
     * @param ui The Ui used to display messages to the user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        if (taskList.isEmpty()) {
            ui.showMessages("You have no tasks at the moment!");
        } else {
            ui.showOrderedList(taskList);
        }
    }
}
