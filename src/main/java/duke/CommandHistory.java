package duke;

import java.util.ArrayList;

import duke.command.Command;

/**
 * The CommandHistory class represents the list of commands made by the
 * user previously.
 */
public class CommandHistory {
    private final ArrayList<Command> commands = new ArrayList<>();

    /**
     * Adds command to the command list.
     *
     * @param command Command to be added.
     */
    public void addCommand(Command command) {
        commands.add(command);
    }

    /**
     * Returns the last command in the list.
     *
     * @return The last command in the list.
     */
    public Command getLastCommand() {
        if (commands.size() == 0) {
            return null;
        }
        Command lastCommand = commands.get(commands.size() - 1);
        commands.remove(commands.size() - 1);
        return lastCommand;
    }
}
