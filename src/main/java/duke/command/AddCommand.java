package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.controller.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

/**
 * Represents a command to add new task to the list.
 */
public class AddCommand implements ICommand {
    private final CommandType type;
    private final String description;
    private final String dateString;

    /**
     * Returns an instance of AddCommand for todo type.
     * @param type Type of task.
     * @param description Description of task.
     */
    public AddCommand(CommandType type, String description) {
        this.type = type;
        this.description = description;
        this.dateString = "";
    }

    /**
     * Returns an instance of AddCommand for deadline and event types.
     * @param type Type of task.
     * @param description Description of task.
     * @param dateString Date of task.
     */
    public AddCommand(CommandType type, String description, String dateString) {
        this.type = type;
        this.description = description;
        this.dateString = dateString;
    }

    /**
     * Executes the command by adding the task to the list.
     * @param storage Storage object for I/O operations.
     * @param taskList TaskList object for operations on the list of tasks.
     * @param ui Ui object for displaying messages.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        switch (type) {
        case TODO:
            return taskList.add(new ToDo("0", description));
        case DEADLINE:
            return taskList.add(new Deadline("0", description, dateString));
        case EVENT:
            return taskList.add(new Event("0", description, dateString));
        default:
            return "";
        }
    }

    /**
     * Returns if command is an ExitCommand.
     * @return True if command is an ExitCommand. Else false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Returns if two AddCommands are equal in type, description, date.
     * @param obj Other command.
     * @return True if two AddCommands are equal in type, description, date. Else false.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AddCommand) {
            AddCommand otherCmd = (AddCommand) obj;
            return this.type == otherCmd.type
                    && this.description.equals(otherCmd.description)
                    && this.dateString.equals(otherCmd.dateString);
        } else {
            return false;
        }
    }
}
