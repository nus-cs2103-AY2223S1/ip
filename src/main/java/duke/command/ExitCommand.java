package duke.command;

import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;

import duke.task.TaskList;
import duke.util.Storage;

/**
 * Encapsulates a command that ends interaction.
 */
public class ExitCommand extends Command {

    public static final String HELP_STRING = "- exit:\n"
            + "Exits the program.";
    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final int EXIT_DELAY = 1000;

    /**
     * Calls the {@code UI} to exit interaction.
     *
     * @param storage The {@code Storage} to use.
     * @param printer The {@code Consumer<String>} to use for printing.
     * @param tasks The {@code TaskList} to use.
     */
    @Override
    public void execute(Storage storage, Consumer<String> printer, TaskList tasks) {
        printer.accept(BYE_MESSAGE);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        }, EXIT_DELAY);
    }

    /**
     * Checks if an {@code Object} is same as this {@code ExitCommand}.
     *
     * @param o The {@code Object} to check.
     * @return {@code true} if the {@code Object} is same as this {@code ExitCommand}, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof ExitCommand;
    }
}
