package duke;

/**
 * Represents a Command to exit Duke.
 */
public class ExitCommand extends Command {
    /**
     * Run the ExitCommand, exit Duke.
     *
     * @param duke Duke instance to run the ExitCommand at.
     */
    @Override
    public void run(Duke duke) {
        duke.exit();
    }
}
