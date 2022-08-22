package command;

import data.TaskList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class CommandHandler {

    protected final String commandStr;
    protected final Pattern commandRegexPattern;
    protected final Matcher commandRegexMatcher;

    public CommandHandler(String commandStr, Pattern commandRegexPattern) {
        this.commandStr = commandStr;
        this.commandRegexPattern = commandRegexPattern;
        this.commandRegexMatcher = commandRegexPattern.matcher(commandStr);
    }

    protected boolean isCommandValid() {
        return commandRegexMatcher.find();
    }

    abstract public List<String> run(TaskList taskList) throws CommandException;
}
