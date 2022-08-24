package duke.command;

/**
 * Command to exit the chatbot application.
 */
public class ByeCommand extends Command {

    /**
     * Constructor for {@code ByeCommand}.
     */
    public ByeCommand() {
        super.isExit = true;
    }

    /**
     * Adds the given task to the {@code TaskList}, and prints a success message.
     */
    @Override
    public void execute() {
        Command.ui.printMessages(new String[]{"Bye. Hope to see you again soon!"});
    }
}
