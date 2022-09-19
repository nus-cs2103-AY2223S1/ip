package duke;

/**
 * Represents a Command to print all tasks in Duke to the UI.
 */
public class ListCommand extends Command {
    /**
     * Run the ListCommand, print all tasks in Duke to the UI.
     *
     * @param duke Duke instance to run the ListCommand at.
     */
    @Override
    public void run(Duke duke) {
        duke.printTasks();
    }
}
