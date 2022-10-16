package seedu.duke;

import java.util.ArrayList;

/**
 * Represents a legal Find Command operation that can be performed by the user.
 */
public class FindCommand extends Command {

    public FindCommand() {

    }

    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage, String input) throws DukeException {
        try {
            if (input.length() == 4) {
                throw new DukeException("Input your search keyword!");
            }
            String output = "Here are the matching tasks in your list:\n";
            String keyword = input.substring(5);
            int listValue = 1;
            ArrayList<Task> tasks = tasklist.getTasks();

            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                if (task.toString().indexOf(keyword) != -1) {
                    output += (String.valueOf(listValue) + "." + task + "\n");

                    listValue++;
                }
            }
            return output;

        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof FindCommand);
    }
}
