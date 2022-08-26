package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

public class AddCommand implements ICommand {
    private final CommandType type;
    private final String description;
    private final String dateString;

    public AddCommand(CommandType type, String description) {
        this.type = type;
        this.description = description;
        this.dateString = "";
    }
    public AddCommand(CommandType type, String description, String dateString) {
        this.type = type;
        this.description = description;
        this.dateString = dateString;
    }
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        switch (type) {
        case TODO:
            Ui.showMsg(taskList.add(new ToDo("0", description)));
            break;
        case DEADLINE:
            Ui.showMsg(taskList.add(new Deadline("0", description, dateString)));
            break;
        case EVENT:
            Ui.showMsg(taskList.add(new Event("0", description, dateString)));
            break;
        default:
            return;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

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
