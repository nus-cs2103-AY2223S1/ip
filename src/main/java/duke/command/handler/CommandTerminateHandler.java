package duke.command.handler;

import duke.command.CommandException;
import duke.command.response.CommandResponse;

import duke.data.TaskList;

import java.util.regex.Pattern;

public class CommandTerminateHandler extends CommandHandler {

    private static final Pattern commandRegexPattern = Pattern.compile("^bye$");

    public CommandTerminateHandler(String commandStr) throws CommandException {
        super(commandStr, commandRegexPattern);
        if (!isCommandValid()) {
            throw new CommandException(String.join("\n",
                "Invalid `bye` command format!",
                "Expected format: bye"));
        }
    }

    @Override
    public CommandResponse run(TaskList taskList) {
        return new CommandResponse("Bye. Hope to see you again soon!", false, true);
    }
}
