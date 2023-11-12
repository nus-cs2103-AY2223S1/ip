package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.task.Task;

/**
 * Representation of the add command.
 */
public class AddCommand extends Command {
    private final Task toAdd;

    /**
     * Constructor for AddCommand.
     * @param toAdd Task to add to the taskList.
     */
    public AddCommand(Task toAdd) {
        this.toAdd = toAdd;
    }

    /**
     * Checks if AddCommand is an exist command.
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the AddCommand.
     * @param taskList TaskList object to add to taskList.
     * @param ui UI object to print to user.
     * @param storage Storage object which saves and loads the taskList.
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        taskList.add(this.toAdd);
        String mess = "Got it. I've added this task:\n" + toAdd
                + "\nNow you have " + taskList.getSize() + " tasks in the list.";
        ui.printWithDivider(mess);
    }

    /**
     * Returns message from the AddCommand.
     * @param taskList TaskList object to add to taskList.
     * @param ui UI object to print to user.
     * @param storage Storage object which saves and loads the taskList.
     */
    @Override
    public String getResponse(TaskList taskList, UI ui, Storage storage) {
        taskList.add(this.toAdd);
        String responseMessage = "Got it. I've added this task:\n\t" + toAdd
                + "\nNow you have " + taskList.getSize() + " tasks in the list.";
        return responseMessage;
    }
}
