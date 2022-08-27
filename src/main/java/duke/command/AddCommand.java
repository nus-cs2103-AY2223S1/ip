package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

public class AddCommand implements Command{
    String[] inputs;
    String commandType;

    public AddCommand(String[] inputs) {
        this.inputs = inputs;
        commandType = inputs[0].toUpperCase();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task;
        switch(commandType) {
            case "TODO":
                task = new ToDo(Parser.getDescription(inputs, null), false);
                break;
            case "DEADLINE":
                task = new Deadline(Parser.getDescription(inputs, "/by"),
                        false,
                        Parser.getTime(inputs, "/by"));
                break;
            case "EVENT":
                task = new Deadline(Parser.getDescription(inputs, "/at"),
                        false,
                        Parser.getTime(inputs, "/at"));
                break;
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        Ui.dukePrint(tasks.add(task));
        storage.addTaskToStorage(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
