package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;
import exception.DukeException;
import task.Task;

/**
 * Represents a Delete Command that inherits from
 * the abstract class Command.
 */
public class DeleteCommand extends Command {
    protected String commandLine;

    /**
     * Constructor for this DeleteCommand that takes in a
     * commandLine as a String.
     *
     * @param commandLine Command from the user.
     */
    public DeleteCommand(String commandLine) {
        this.commandLine = commandLine;
    }

    /**
     * Returns a boolean false to show that this
     * is not the last command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command to delete a task that the user
     * wants to remove.
     *
     * @param taskList The ArrayList that contains all the tasks.
     * @param ui The Ui that interacts with the next command from the user.
     * @param storage The Storage used to store the tasks.
     * @throws DukeException If there are no such task in the taskList.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        int index = Integer.parseInt(commandLine.substring(7)) - 1;
        try {
            if (index > taskList.size() - 1) {
                throw new DukeException("You have no such tasks.");
            } else {
                Task task = taskList.get(index);
                taskList.remove(index);
                storage.updateData(taskList);
                System.out.println("Noted. I've removed this task:\n" +
                        " " + task.toString() + "\n" + "Now you have " +
                        taskList.size() + " tasks in the list.\n");
            }
        } catch (DukeException e) {
            System.out.println(e.toString());
        }
    }
}
