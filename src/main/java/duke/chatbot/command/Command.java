package duke.chatbot.command;

import duke.chatbot.data.exception.InvalidInputException;
import duke.chatbot.data.task.TaskList;

import java.util.List;

public abstract class Command {
    protected TaskList taskList;
    protected List<String> arguments;

    public static boolean isExit(Command command) {
        return command != null && command instanceof ExitCommand;
    }

    // Always initData before execute
    public abstract CommandResult execute() throws InvalidInputException;

    public void initData(TaskList taskList) {
        this.taskList = taskList;
    }
}
