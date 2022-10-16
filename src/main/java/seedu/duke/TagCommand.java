package seedu.duke;

import java.util.ArrayList;

/**
 * Represents a legal Tag Command operation that can be performed by the user.
 */
public class TagCommand extends Command {

    public TagCommand() {

    }

    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage, String input) throws DukeException {
        ArrayList<Task> tasks = tasklist.getTasks();
        try {
            if (input.length() == 3) {
                throw new DukeException("Choose which task to tag!");
            }

            if (input.length() == 5) {
                throw new DukeException("Choose your tag for this task!");
            }
            int taskNumber = Integer.parseInt(input.substring(4, 5)) - 1;
            if (tasks.size() - 1 < taskNumber) {
                throw new DukeException("Please enter a valid task number!");
            }
            String tag = input.substring(6);
            String output = tasks.get(taskNumber).setTag(tag);
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
                || (other instanceof TagCommand);
    }
}

