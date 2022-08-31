package blink.command;

import blink.BlinkException;
import blink.Storage;
import blink.TaskList;
import blink.Ui;
import blink.task.Deadlines;

/**
 * Deadline command to create a new deadline task.
 */
public class DeadlineCommand extends Command{

    private String desc;
    private String date;

    /**
     * Constructor for the DeadlineCommand which splits the input
     * to get the deadline description and date information.
     *
     * @param input Information of the Deadline object
     * @throws BlinkException Thrown if there is missing information
     * of Deadline object.
     */
    public DeadlineCommand(String input) {
        String[] info = input.split("/by");
        if (info.length != 2) {
            throw new BlinkException("Error in command for deadline");
        }
        this.desc = info[0];
        this.date = info[1];
    }

    /**
     * Causes for deadline object to be created in TaskList, then Ui will
     * display information of the deadline object which is then saved in
     * Storage.
     *
     * @param tasks TaskList object of current Blink object
     * @param ui Ui object of current Blink object
     * @param storage Storage object of current Blink object
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Deadlines event = tasks.addDeadline(this.desc, this.date);
        storage.save(tasks);
        return ui.showAddTask(tasks, event);
    }

    /**
     * Deadline command will not end the program.
     *
     * @return False
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
