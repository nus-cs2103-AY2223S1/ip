package duke.command;

import duke.DukeException;
import duke.task.TaskList;

public abstract class Command {
    private static final String WRONG_ARGS_COUNT = "Wrong number of arguments provided!";

    protected final CommandType command;
    protected final String[] args;

    protected Command(CommandType command, String[] args) throws DukeException {
        if (!command.isCompatible(args)) {
            throw new DukeException(WRONG_ARGS_COUNT);
        }
        this.command = command;
        this.args = args;
    }

    public static Command of(String input) throws DukeException {
        String[] splits = input.strip().split(" ", 2);
        String command = splits[0].toLowerCase();
        String[] args;

        if (splits.length > 1) {
            args = splits[1].split(" / ");
            for (int i = 0; i < args.length; ++i) {
                args[i] = args[i].strip();
            }
        } else {
            args = new String[0];
        }

        switch (command) {
        case "list":
            return new ListCommand(args);
        case "check":
            return new UpdateStatusCommand(args, true);
        case "uncheck":
            return new UpdateStatusCommand(args, false);
        case "todo":
            return new AddTaskCommand(CommandType.TODO, args);
        case "deadline":
            return new AddTaskCommand(CommandType.DEADLINE, args);
        case "event":
            return new AddTaskCommand(CommandType.EVENT, args);
        case "delete":
            return new DeleteTaskCommand(args);
        case "bye":
            return new ByeCommand(args);
        default:
            return new BadCommand(args);
        }
    }

    public boolean isExit() {
        return command == CommandType.BYE;
    }

    public abstract void execute(TaskList tasks) throws DukeException;

}
