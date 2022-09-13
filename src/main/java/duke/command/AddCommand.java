package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * AddCommand is a Command when the user wants to add a Task.
 */
public class AddCommand extends Command {
    private static final String UNKNOWN_COMMAND = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private String cmd;

    /**
     * Initializes an AddCommand object.
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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String[] split = cmd.split(" ", 2);
        String type = split[0];
        String description = split[1];
        String taskDescription;
        switch (type) {
        case TODO:
            taskDescription = new AddTodoCommand(description).execute(tasks, ui, storage);
            break;

        case DEADLINE:
            taskDescription = new AddDeadlineCommand(description).execute(tasks, ui, storage);
            break;

        case EVENT:
            taskDescription = new AddEventCommand(description).execute(tasks, ui, storage);
            break;

        default:
            throw new DukeException(UNKNOWN_COMMAND);
        }
        return ui.showAddTask(tasks.getSize(), taskDescription);
    }
}
