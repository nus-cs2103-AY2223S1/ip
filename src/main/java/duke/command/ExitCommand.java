package duke.command;

import duke.Duke;
import duke.util.MessagePrinter;

/**
 * Represents a Command to terminates the Duke.
 */
public class ExitCommand extends Command {
    /**
     * Constructs the class.
     */
    public ExitCommand() {
        super(Action.EXIT);
    }

    /**
     * Executes the Command with given Duke.
     * @param duke The target duke that the command takes effect.
     * @return The response of Duke.
     */
    @Override
    public String execute(Duke duke) {
        MessagePrinter messagePrinter = duke.getMessagePrinter();
        String farewellMsg = "Bye. Hope to see you again soon!";
        return messagePrinter.getPrintMessage(farewellMsg);
    }

    /**
     * Returns whether this command terminates Duke.
     * @return Returns whether this command terminates Duke.
     */
    @Override
    public boolean isTerminating() {
        return true;
    }
}
