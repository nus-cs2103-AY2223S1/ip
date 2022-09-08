package duke;

import duke.command.Command;
import duke.command.ListCommand;

public class StoreUndo {
    private static Command undo = new ListCommand();

    public static void updateUndo(Command newUndo) {
        undo = newUndo;
    }

    public static Command getUndo() {
        Command temp = undo;
        undo = new ListCommand();
        return temp;
    }
}
