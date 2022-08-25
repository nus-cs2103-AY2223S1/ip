package duke.command;

import duke.command.Command;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
       ui.list(taskList);
    }
}
