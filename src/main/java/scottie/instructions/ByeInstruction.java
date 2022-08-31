package scottie.instructions;

import java.util.Map;

import scottie.tasks.TaskList;
import scottie.ui.Ui;

/**
 * Encapsulates the bye instruction which is used to
 * exit the program.
 */
class ByeInstruction extends Instruction {
    /**
     * Constructs a ByeInstruction with the given arguments.
     *
     * @param mainArgument The main argument provided to this instruction.
     * @param flagArgumentsMap The map between flags and values provided to
     *                         this instruction.
     */
    ByeInstruction(String mainArgument, Map<String, String> flagArgumentsMap) {
        super(mainArgument, flagArgumentsMap);
    }

    /**
     * Displays a final closing message to the user.
     *
     * @param taskList Ignored for this instruction.
     * @param ui The Ui used to display messages to the user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.showMessages("Ok, see you next time!");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean endsProgram() {
        return true;
    }
}
