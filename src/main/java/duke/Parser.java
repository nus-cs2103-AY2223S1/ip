package duke;

import duke.command.*;
import duke.exception.InvalidCommandException;
import duke.command.Command;

/**
 * Parser classes parse user's input of commands
 */
public class Parser {

    /**
     * Check the legitimacy of user's input for command
     *
     * @param command input command
     * @throws InvalidCommandException if command < 1 or command > 8
     */
    private void checkCommand(int command) throws InvalidCommandException {
        if (command < 1 || command > 8) {
            throw new InvalidCommandException("ERROR");
        }
    }

    /**
     * Parses user's input of commands and returns a Duke command
     * @param commandText user's input
     * @param ui Duke Ui object
     * @return corresponding Duke command
     */
    public Command parse(String commandText, Ui ui) {
        Command command = null;
        try {
            int commandNumber = Integer.parseInt(commandText);
            checkCommand(commandNumber);
            switch (commandNumber) {
                case 1:
                    command = new CreateToDoCommand();
                    break;
                case 2:
                    command = new CreateEventCommand();
                    break;
                case 3:
                    command = new CreateDeadlineCommand();
                    break;
                case 4:
                    command = new ShowTasksCommand();
                    break;
                case 5:
                    command = new MarkTaskCommand();
                    break;
                case 6:
                    command = new UnmarkTaskCommand();
                    break;
                case 7:
                    command = new DeleteTaskCommand();
                    break;
                case 8:
                    command = new ExitCommand();
                    break;
            }
        } catch (InvalidCommandException | NumberFormatException ice) {
            ui.reportError("Your command is invalid! Please try again...");
        }
        return command;
    }
}
