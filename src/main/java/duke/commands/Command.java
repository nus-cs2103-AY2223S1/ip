package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;

import java.time.format.DateTimeFormatter;

public abstract class Command {

    // Formatter to standardize the display of dates
    protected static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("MMM d yyyy");

    /**
     * Executes the given command.
     *
     * @param tasks the TaskList to be operated on
     * @param ui the ui to display messages
     * @param storage the Storage to store changes to the TaskList
     */
    public abstract void execute(TaskList tasks, UI ui, Storage storage);

    public abstract boolean isExit();

    /**
     * Checks if the input is a Date or a Day.
     *
     * @param input the input to be checked
     *
     * @return True if the input is a Date
     */
    public static boolean isDate(String input) {
        return input.matches(".[0-9].*");
    }

    /**
     * Checks that the input String is a valid command
     *
     * @param input the input String to be checked
     *
     * @return True if the input is valid
     */
    public static boolean checkValid(String input) {
        String[] str = input.split(" ");
        return ((str.length != 1) &&  (str.length != 0));
    }
}
