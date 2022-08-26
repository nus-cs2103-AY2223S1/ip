package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

public class FindCommand extends Command {
    private String input;

    public FindCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        for (int i = 0; i < taskList.length(); i++) {
            String taskString = taskList.getTask(i).toString();
            if (taskString.contains(input)) {
                ui.repeater(taskString);
            }
        }
    }
}
