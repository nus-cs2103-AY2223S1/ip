package command;

import data.TaskList;
import java.util.List;

public abstract class CommandHandler {
    protected final TaskList taskList;

    public CommandHandler(TaskList taskList) {
        this.taskList = taskList;
    }

    protected static String gatherCommandTokens(List<String> tokens, int start, int end,
        String delimiter) {
        return String.join(delimiter, tokens.subList(start, end));
    }

    abstract public boolean validateCommand(List<String> commandTokens);

    abstract public List<String> run(List<String> commandTokens);
}
