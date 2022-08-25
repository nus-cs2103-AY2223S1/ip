package duke.command.handler;

import java.util.regex.Pattern;

import duke.command.CommandException;
import duke.command.response.CommandResponse;
import duke.data.TaskList;

public class CommandTerminateHandler extends CommandHandler {

    protected static final String INVALID_FORMAT_MSG = String.join("\n",
        "Invalid `bye` command format!",
        "Expected format: bye"
    );
    private static final Pattern commandRegexPattern = Pattern.compile("^bye$");

    public CommandTerminateHandler(String commandStr) throws CommandException {
        super(commandStr, commandRegexPattern);
    }

    @Override
    protected String getInvalidFormatMessage() {
        return INVALID_FORMAT_MSG;
    }

    @Override
    public CommandResponse run(TaskList taskList) {
        return new CommandResponse("Bye. Hope to see you again soon!", false, true);
    }
}
