package seedu.duke;

import java.util.ArrayList;

/**
 * Represents a legal Delete Command operation that can be performed by the user.
 */
public class DeleteCommand extends Command {

    public DeleteCommand() {

    }

    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage, String input) throws DukeException {
        ArrayList<Task> tasks = tasklist.getTasks();
        try {

            if (input.length() == 6) {
                throw new DukeException("Choose which task to delete!");
            }

            int taskNumber = Integer.parseInt(input.substring(7)) - 1;
            if (tasks.size() - 1 < taskNumber) {
                throw new DukeException("Please enter a valid task number!");
            }
            Task task = tasks.get(taskNumber);
            tasks.remove(taskNumber);
            ui.remove(task);
            String output = "Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks")
                + " in the list.";
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
                || (other instanceof DeleteCommand);
    }

}

