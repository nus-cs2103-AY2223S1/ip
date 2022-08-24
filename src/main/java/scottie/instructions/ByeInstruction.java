package scottie.instructions;

import scottie.tasks.TaskList;
import scottie.ui.Ui;

import java.util.Map;

class ByeInstruction extends Instruction {
    ByeInstruction(String mainArgument, Map<String, String> flagArgumentsMap) {
        super(mainArgument, flagArgumentsMap);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.showMessages("Ok, see you next time!");
    }

    @Override
    public boolean endsProgram() {
        return true;
    }
}
