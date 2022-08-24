package scottie.instructions;

import scottie.tasks.TaskList;
import scottie.ui.Ui;

import java.util.Map;

class ListInstruction extends Instruction {
    ListInstruction(String mainArgument, Map<String, String> flagArgumentsMap) {
        super(mainArgument, flagArgumentsMap);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        if (taskList.isEmpty()) {
            ui.showMessages("You have no tasks at the moment!");
        } else {
            ui.showOrderedList(taskList);
        }
    }
}
