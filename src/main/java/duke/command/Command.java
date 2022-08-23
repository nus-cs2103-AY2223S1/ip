package duke.command;

import duke.storage.TaskRecords;
import duke.ui.BotUI;
import duke.DukeException;
public abstract class Command {

    private final String command;

    Command(String command) {
        this.command = command;
    }

    String getCommand() {
        return this.command;
    }

    public abstract void execute(TaskRecords taskList, BotUI ui) throws DukeException;

    public abstract boolean isExit();
}
