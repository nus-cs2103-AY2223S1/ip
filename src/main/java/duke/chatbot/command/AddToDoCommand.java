package duke.chatbot.command;

import duke.chatbot.data.task.ToDo;

/**
 * A command that adds an instance of {@link ToDo} to the list of tasks stored in the Duke application instance.
 *
 * @author jq1836
 */
public class AddToDoCommand extends AddTaskCommand {
    /**
     * The command word to invoke this command
     */
    public static final String COMMAND_WORD = "todo";

    public AddToDoCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Adds an instance of ToDo to the list of tasks stored in the Duke application instance and returns an instance
     * of {@link CommandResult} which contains the ToDo added.
     *
     * @return The result after executing the command.
     */
    @Override
    public CommandResult execute() {
        ToDo task = new ToDo(arguments);
        addTask(task);
        return getResult(task);
    }
}
