package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.tasks.TaskList;

public class ListCommand extends Command {
    public ListCommand(String instruction) {
        super(instruction);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printArray(tasks);
    }
}
