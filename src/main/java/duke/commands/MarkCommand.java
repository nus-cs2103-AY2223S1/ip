package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Command to mark a task as done/undone.
 */
public class MarkCommand extends Command {

    private boolean mark;

    /**
     * Constructor for a Mark Command.
     * @param mark a flag that determines whether the commmand marks
     *             or unmarks a task.
     */
    public MarkCommand(boolean mark) {
        this.mark = mark;
    }

    /**
     * Executes the command.
     * @param taskList
     * @param ui User Interface of the to-do-list.
     * @param storage Storage option.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String inputString = ui.userString();
        int index = Integer.parseInt(inputString.split(" ")[1]) - 1;
        if (this.mark) {
            taskList.get(index).completed();
            System.out.println("Nice! I've marked this task as done:");
        } else {
            taskList.get(index).uncompleted();
            System.out.println("OK, I've marked this task as not done yet:");
        }

        System.out.printf("\t%s\n", taskList.get(index));
        storage.save(taskList);
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
