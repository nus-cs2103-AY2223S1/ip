package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

/**
 * Command that represents a command with a numeric argument, mark, unmark and delete.
 */
public class NumericCommand implements Command {
    private String command;
    private int index;

    /**
     * Default constructor of the Numeric Command.
     * Command can be either mark, unmark or delete.
     *
     * @param command Command that the numeric command represents
     * @param index Numerical argument of the command.
     */
    public NumericCommand(String command, int index) {
        this.command = command;
        this.index = index;
    }

    /**
     * Runs the numeric command with the task at specified index.
     * If the command is mark, the task at specified index is marked.
     * If the command is unmark, the task at the specified index is unmarked.
     * If the command is delete, the task at the specified index is deleted.
     *
     * @param tasks TaskList that contains the temporary tasks.
     * @param storage Storage that the tasks are saved at.
     * @return String output of executing the task.
     * @throws DukeException If any error occurs.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        if (index < 0 || index >= tasks.getSize()) {
            throw new DukeException("Please enter between 1 to the last element of the list");
        }
        String output = "";
        switch (command) {
        case "mark":
            if (tasks.get(index).isMarked()) {
                throw new DukeException("That task is already marked!");
            }
            tasks.get(index).markAsDone();
            storage.writeAll(tasks);
            output += "Nice! I've marked this task as done\n";
            output += tasks.get(index) + "\n";
            break;
        case "unmark":
            if (!tasks.get(index).isMarked()) {
                throw new DukeException("That task is already unmarked!");
            }

            tasks.get(index).unmark();
            storage.writeAll(tasks);
            output += "OK, I've marked this task as not done yet:\n";
            output += tasks.get(index) + "\n";
            break;
        case "delete":
            output += "Noted. I've removed this task:\n";

            tasks.remove(index);
            output += tasks.get(index) + "\n";
            output += "Now you have " + tasks.getSize() + " tasks in the list" + "\n";
            storage.writeAll(tasks);
            break;
        default:
            throw new DukeException("Unable to parse that numeric command");
        }
        return output;
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
