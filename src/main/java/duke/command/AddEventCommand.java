package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class AddEventCommand extends Command {

    String argument;

    AddEventCommand(String argument) {
        super(CommandType.ADD_EVENT);
        this.argument = argument;
    }

    @Override
    protected void executeConcretely(Ui ui, TaskList taskList, Storage storage) {
        String output;
        try {
            output = taskList.addNewTask(Task.createFromCommand(argument));
        } catch (DukeException exception) {
            output = exception.getMessage();
        }
    }
}
