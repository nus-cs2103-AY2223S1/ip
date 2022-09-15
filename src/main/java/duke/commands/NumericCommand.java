package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

/**
 * Command that represents a command with numeric arguments: mark, unmark and delete.
 */
public class NumericCommand implements Command {
    private String command;

    private int[] indexList;

    /**
     * Default constructor of the Numeric Command.
     * Command can be either mark, unmark or delete.
     *
     * @param command Command that the numeric command represents
     * @param indexList Numerical arguments of the command.
     */
    public NumericCommand(String command, int... indexList) {
        this.command = command;
        this.indexList = indexList;
    }

    /**
     * Runs the numeric command at the specified indexes.
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
        if (!validateIndexes(indexList, tasks)) {
            throw new DukeException("Please enter between 1 to the last element of the list");
        }
        String output = "";

        switch (command) {
        case "mark":
            output += "Nice! I've marked the following task(s) as done\n";
            for (int index : indexList) {
                if (tasks.get(index).isMarked()) {
                    throw new DukeException("Task " + index + "is already marked!");
                }
                tasks.get(index).markAsDone();
                output += tasks.get(index) + "\n";
            }
            storage.writeAll(tasks);
            break;
        case "unmark":
            output += "OK, I've marked the following task(s) not done yet:\n";
            for (int index : indexList) {
                if (!tasks.get(index).isMarked()) {
                    throw new DukeException("Task " + index + "is already unmarked!");
                }
                tasks.get(index).unmark();
                output += tasks.get(index) + "\n";
            }
            storage.writeAll(tasks);
            break;
        case "delete":
            output += "Noted. I've removed the following task(s):\n";
            for (int i = 0; i < indexList.length; i++) {
                int index = indexList[i] - i;
                output += tasks.get(index) + "\n";
                tasks.remove(index);
            }
            output += "Now you have " + tasks.getSize() + " tasks in the list" + "\n";
            storage.writeAll(tasks);
            break;
        default:
            throw new DukeException("Unable to parse that numeric command");
        }
        return output;
    }

    private boolean validateIndexes(int[] indexList, TaskList tasks) {
        for (int index : indexList) {
            if (index < 0 || index >= tasks.getSize()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
