package duke.command;

import duke.DukeException;
import duke.storage.TaskRecords;
import duke.ui.BotUI;

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
