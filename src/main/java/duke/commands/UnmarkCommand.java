package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.tasks.TaskList;

public class UnmarkCommand extends Command {
    public UnmarkCommand(String instruction) {
        super(instruction);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int index = Integer.parseInt(instruction.substring(7)) - 1;
            tasks.get(index).setUndone();
            ui.printUnmarkMessage(tasks.get(index));
            storage.saveList(tasks);
        } catch (DukeException e) {
            ui.printError(e);
        }
    }
}
