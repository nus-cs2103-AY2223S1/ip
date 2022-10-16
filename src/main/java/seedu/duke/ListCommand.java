package seedu.duke;

import java.util.ArrayList;

/**
 * Represents a legal List Command operation that can be performed by the user.
 */
public class ListCommand extends Command {

    public ListCommand() {

    }

    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage, String input) {
        String output = "Here are the tasks in your list:\n";
        ArrayList<Task> tasks = tasklist.getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            output += String.valueOf(i + 1) + "." + task + "\n";
        }
        return output;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ListCommand);
    }
}
