package seedu.duke;

import java.util.ArrayList;

/**
 * Represents a legal Unmark Command operation that can be performed by the user.
 */
public class UnmarkCommand extends Command {

    public UnmarkCommand() {

    }

    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage, String input) throws DukeException {
        ArrayList<Task> tasks = tasklist.getTasks();
        try {
            if (input.length() == 6) {
                throw new DukeException("Choose which task to mark as undone!");
            }
            int taskNumber = Integer.parseInt(input.substring(7)) - 1;
            if (tasks.size() - 1 < taskNumber) {
                throw new DukeException("Please enter a valid task number!");
            }
            String output = tasks.get(taskNumber).setUndone();
            return output;

        } catch (DukeException e) {
            return e.getMessage();
        } finally {
            storage.save(tasks);
        }

    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof UnmarkCommand);
    }
}
