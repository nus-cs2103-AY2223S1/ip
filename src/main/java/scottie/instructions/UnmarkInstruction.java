package scottie.instructions;

import scottie.tasks.TaskList;
import scottie.ui.Ui;

import java.util.Map;

class UnmarkInstruction extends Instruction {
    UnmarkInstruction(String mainArgument, Map<String, String> flagArgumentsMap) {
        super(mainArgument, flagArgumentsMap);
    }

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
