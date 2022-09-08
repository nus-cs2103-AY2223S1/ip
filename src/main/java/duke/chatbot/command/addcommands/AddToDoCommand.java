package duke.chatbot.command.addcommands;

import duke.chatbot.data.task.Task;
import duke.chatbot.data.task.ToDo;

/**
 * A command that adds an instance of {@link ToDo} to the list of tasks stored in the Duke application instance.
 *
 * @author jq1836
 */
public class AddToDoCommand extends AddTaskCommand {
    /**
     * The command word to invoke this command.
     */
    public static final String COMMAND_WORD = "todo";

    public AddToDoCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    protected Task supplyTask() {
        return new ToDo(arguments);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && obj instanceof AddToDoCommand;
    }
}
