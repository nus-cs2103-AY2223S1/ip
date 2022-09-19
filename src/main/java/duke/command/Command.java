package duke.command;

import duke.DukeException;
import duke.common.AnomaliesManager;
import duke.storage.TaskList;
import duke.ui.BotUI;

/**
 * Encapsulates the user Command.
 * It stores a command (String) of the user input (the first word of the input)
 * ,and it provides the skeletal implementation of all user command.
 * Every user command should be the child-class of this abstract class.
 */
public abstract class Command {

    private final String command;

    Command(String command) {
        this.command = command;
    }

    String getCommand() {
        return this.command;
    }

    public abstract String execute(TaskList taskList, BotUI ui, AnomaliesManager anomaliesManager) throws DukeException;

    public abstract boolean isExit();

    public abstract Command resolveAnomaly();
}
