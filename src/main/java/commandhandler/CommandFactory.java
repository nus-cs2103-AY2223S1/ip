package commandhandler;

import data.TaskList;

public class CommandFactory {

    public Command parseCommand(String commandStr) {
        try {
            return Command.valueOf(commandStr.toUpperCase());
        } catch (IllegalArgumentException error) {
            return Command.UNKNOWN;
        }
    }

    public CommandHandler getCommandHandler(Command command, TaskList taskList) {
        switch (command) {
            case LIST:
                return new CommandListHandler(taskList);
            case MARK:
            case UNMARK:
                return new CommandMarkHandler(taskList);
            case TODO:
                return new CommandTodoHandler(taskList);
            case DEADLINE:
                return new CommandDeadlineHandler(taskList);
            case EVENT:
                return new CommandEventHandler(taskList);
        }

        return null;
    }
}
