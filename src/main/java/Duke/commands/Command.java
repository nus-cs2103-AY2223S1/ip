package Duke.commands;

import Duke.exceptions.DukeException;
import Duke.storage.Storage;
import Duke.tasks.TaskList;
import Duke.ui.UI;

public abstract class Command {

    public abstract void execute(TaskList tasks, UI ui, Storage storage);

    public abstract boolean isExit();

    public static boolean checkValid(String input) {
        String[] str = input.split(" ");
        return ((str.length != 1) &&  (str.length != 0));
    }
}
