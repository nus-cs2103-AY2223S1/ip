package seedu.duke;

import java.util.ArrayList;

/**
 * Represents a legal Mark Command operation that can be performed by the user.
 */
public class MarkCommand extends Command {

    public MarkCommand() {

    }

    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage, String input) throws DukeException {
        ArrayList<Task> tasks = tasklist.getTasks();
        try {
            if (input.length() == 4) {
                throw new DukeException("Choose which task to mark as done!");
            }
            int taskNumber = Integer.parseInt(input.substring(5)) - 1;
            if (tasks.size() - 1 < taskNumber) {
                throw new DukeException("Please enter a valid task number!");
            }
            String output = tasks.get(taskNumber).setDone();
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
                || (other instanceof MarkCommand);
    }
}
