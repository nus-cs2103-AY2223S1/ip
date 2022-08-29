package duke.chatbot.command;

import java.util.List;

import duke.chatbot.data.exception.InvalidInputException;
import duke.chatbot.data.task.TaskList;

/**
 * A command to be executed and outputs a result.
 */
public abstract class Command {
    /** A list of tasks */
    protected TaskList taskList;

    /** A list of arguments */
    protected List<String> arguments;

    /**
     * Returns true if the command argument is an instance of ExitCommand
     * and false otherwise. Used to detect whether a command results in
     * the closing of the application.
     * @param command The command to be checked.
     * @return boolean
     */
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }

    /**
     * Returns a CommandResult instance after execution.
     * @return The result after executing the command.
     * @throws InvalidInputException If arguments passed to Command is invalid.
     */
    public abstract CommandResult execute() throws InvalidInputException;

    public void initData(TaskList taskList) {
        this.taskList = taskList;
    }
}
