package duke;

/**
 * Represents a Command to find free times (the nearest date without task).
 */
public class FreeCommand extends Command {
    /**
     * Run the FreeCommand, prints the nearest date without task in Duke.
     *
     * @param duke Duke instance to run the Command at.
     */
    @Override
    public void run(Duke duke) {
        duke.findFreeTimes();
    }
}
