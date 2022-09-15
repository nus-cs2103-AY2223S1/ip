package duke;

import duke.command.Command;
import duke.command.ListCommand;

/**
 * Class that stores a command to undo the previous list-changing command.
 */
public class StoreUndo {
    private static Command undo = new ListCommand();

    /**
     * Updates the stored undo.
     * @param newUndo Updates undo.
     */
    public static void updateUndo(Command newUndo) {
        undo = newUndo;
    }

    /**
     * Returns undo.
     * @return command to undo the previous list-changing command.
     */
    public static Command getUndo() {
        Command temp = undo;
        undo = new ListCommand();
        return temp;
    }
}
