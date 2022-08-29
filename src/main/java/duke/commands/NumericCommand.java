package duke.commands;
import duke.TaskList;
import duke.Storage;
import duke.DukeException;

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
     * @throws DukeException If any error occur.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        if (index < 0 || index >= tasks.getSize()) {
            throw new DukeException("Please enter between 1 to the last element of the list");
        }

        switch (command) {
        case "mark":
            if (tasks.get(index).isMarked()) {
                throw new DukeException("That task is already marked!");
            }

            tasks.get(index).markAsDone();
            System.out.println("Nice! I've marked this task as done");
            System.out.println(tasks.get(index));
            storage.writeAll(tasks);
            break;
        case "unmark":
            if (!tasks.get(index).isMarked()) {
                throw new DukeException("That task is already unmarked!");
            }

            tasks.get(index).unmark();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tasks.get(index));
            storage.writeAll(tasks);
            break;
        case "delete":
            System.out.println("Noted. I've removed this task:");
            System.out.println(tasks.get(index));
            tasks.remove(index);
            System.out.println("Now you have " + tasks.getSize() + " tasks in the list");
            storage.writeAll(tasks);
            break;
        }

    }
}
