package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class AddCommand extends Command {

    private String cmd;

    public AddCommand(String cmd) {
        this.cmd = cmd;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String[] split = cmd.split(" ", 2);
        String type = split[0];
        String desc = split[1];
        switch (type) {
        case "todo":
            new AddTodoCommand(desc).execute(tasks, ui, storage);
            break;

        case "deadline":
            new AddDeadlineCommand(desc).execute(tasks, ui, storage);
            break;

        case "event":
            new AddEventCommand(desc).execute(tasks, ui, storage);
            break;

        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        ui.showAddTask(tasks.getSize(), tasks.get(tasks.getSize() - 1).toString());
    }
}
