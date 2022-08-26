package duke.command;

import duke.exception.DukeException;
import duke.task.EventTask;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class AddEventCommand extends Command {

    private EventTask task;

    public AddEventCommand(EventTask task) {
        super(CommandType.ADD_EVENT);
        this.task = task;
    }

    @Override
    protected void executeConcretely(Ui ui, TaskList taskList, Storage storage) {
        String output;

        try {
            output = taskList.addNewTask(task);
        } catch (DukeException exception) {
            output = exception.getMessage();
        }

        ui.printOutput(output);

        super.saveFile(ui, taskList, storage);
    }
}
