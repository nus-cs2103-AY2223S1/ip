package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * AddCommand is a Command when the user wants to add a Task.
 */
public class AddCommand extends Command {

    private String cmd;

    /**
     * Constructor for AddCommand.
     *
     * @param cmd The command inputted by the user.
     */
    public AddCommand(String cmd) {
        this.cmd = cmd;
    }

    /**
     * Decides what type of task to be stored.
     * Adds the task with the proper type.
     *
     * @param tasks The list of tasks.
     * @param ui The class that deals with interactions with the user.
     * @param storage The class that deals with loading and storing tasks.
     */
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
