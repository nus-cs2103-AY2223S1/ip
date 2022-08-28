package Duke.commands;

import Duke.storage.Storage;
import Duke.tasks.TaskList;
import Duke.ui.UI;

import java.time.format.DateTimeFormatter;

public abstract class Command {

    protected static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("MMM d yyyy");

    public abstract void execute(TaskList tasks, UI ui, Storage storage);

    public abstract boolean isExit();

    // Check if date entered is Day or Date
    public static boolean isDate(String input) {
        return input.matches(".[0-9].*");
    }

    public static boolean checkValid(String input) {
        String[] str = input.split(" ");
        return ((str.length != 1) &&  (str.length != 0));
    }
}
