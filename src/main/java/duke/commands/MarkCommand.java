package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.tasks.TaskList;

public class MarkCommand extends Command {
    public MarkCommand(String instruction) {
        super(instruction);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int index = Integer.parseInt(instruction.substring(5)) - 1;
        tasks.get(index).setDone();
        ui.printMarkMessage(tasks.get(index));
        storage.saveList(tasks);
    }
}