package duke.command;

import duke.command.handler.CommandDeadlineHandler;
import duke.command.handler.CommandDeleteHandler;
import duke.command.handler.CommandEventHandler;
import duke.command.handler.CommandHandler;
import duke.command.handler.CommandListHandler;
import duke.command.handler.CommandMarkHandler;
import duke.command.handler.CommandTerminateHandler;
import duke.command.handler.CommandTodoHandler;

public class CommandFactory {

    public Command parseCommand(String commandStr) throws CommandException {
        String commandToken = commandStr.split(" ")[0];
        try {
            return Command.valueOf(commandToken.toUpperCase());
        } catch (IllegalArgumentException error) {
            throw new CommandException("Unknown command!");
        }
    }

    public CommandHandler getCommandHandler(Command command, String commandStr)
            throws CommandException {
        switch (command) {
        case LIST:
            return new CommandListHandler(commandStr);
        case MARK:
        case UNMARK:
            return new CommandMarkHandler(commandStr);
        case TODO:
            return new CommandTodoHandler(commandStr);
        case DEADLINE:
            return new CommandDeadlineHandler(commandStr);
        case EVENT:
            return new CommandEventHandler(commandStr);
        case DELETE:
            return new CommandDeleteHandler(commandStr);
        case BYE:
            return new CommandTerminateHandler(commandStr);
        default:
            return null;
        }
    }
}
