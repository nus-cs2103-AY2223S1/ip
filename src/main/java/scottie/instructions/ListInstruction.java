package scottie.instructions;

import java.util.Map;

import scottie.tasks.TaskList;
import scottie.ui.Ui;

/**
 * Encapsulates the list instruction which is used to
 * display the list of tasks.
 */
class ListInstruction extends Instruction {
    private static final String NO_TASKS_MESSAGE = "Wow! Looks like you have no tasks at the moment!\n"
            + "I wish I had no tasks to do too...";
    private static final String LIST_TASKS_MESSAGE = "Ok, here's your list of tasks at the moment!";

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
            ui.showMessages(NO_TASKS_MESSAGE);
        } else {
            ui.showMessages(LIST_TASKS_MESSAGE);
            ui.showOrderedList(taskList);
        }
    }
}
