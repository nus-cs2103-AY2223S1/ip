package duke.command.handler;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import duke.command.CommandException;
import duke.command.handler.base.CommandAddTaskHandler;
import duke.data.tasks.Task;
import duke.data.tasks.TaskTodo;

public class CommandTodoHandler extends CommandAddTaskHandler {

    protected static final String INVALID_FORMAT_MESSAGE = String.join("\n",
        "Invalid `todo` command format!",
        "Expected format: todo <task-title>",
        "Examples:",
        "\t- todo task-1"
    );
    private static final Pattern commandRegexPattern = Pattern.compile("^todo (.+)");

    /**
     * Constructor for CommandTodoHandler.
     *
     * @param commandStr input command string.
     * @throws CommandException if input command string does not meet format specifications.
     */
    public CommandTodoHandler(String commandStr) throws CommandException {
        super(commandStr, commandRegexPattern);
    }

    @Override
    protected String getInvalidFormatMessage() {
        return INVALID_FORMAT_MESSAGE;
    }

    @Override
    protected Task getTaskFromCommand() {
        MatchResult regexMatchResult = commandRegexMatcher.toMatchResult();
        return new TaskTodo(regexMatchResult.group(1));
    }
}
