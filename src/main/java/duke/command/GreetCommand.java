package duke.command;

import duke.Duke;
import duke.util.MessagePrinter;

/**
 * Represents a Command to greet the user in Duke.
 */
public class GreetCommand extends Command {
    /**
     * Constructs the class.
     */
    public GreetCommand() {
        super(Action.GREET);
    }

    /**
     * Executes the Command with given Duke.
     * @param duke The target duke that the command takes effect.
     * @return The response of Duke.
     */
    @Override
    public String execute(Duke duke) {
        MessagePrinter messagePrinter = duke.getMessagePrinter();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String welcomeMsg = "Hello! I'm Duke \n" + "What can I do for you?";
        return messagePrinter.getPrintMessage("Hello from\n" + logo + "\n" + welcomeMsg);
    }

    /**
     * Returns whether this command terminates Duke.
     * @return Returns whether this command terminates Duke.
     */
    @Override
    public boolean isTerminating() {
        return false;
    }
}
