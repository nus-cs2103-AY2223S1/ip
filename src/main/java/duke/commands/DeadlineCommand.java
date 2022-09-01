package duke.commands;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.ui.Ui;


/**
 * This class represents a command to add a deadline to the task list
 */
public class DeadlineCommand extends Command {

    private String description;
    private String by;

    /**
     * Construct a new Deadline Command
     * @param description A description of the deadline
     * @param by Date to complete the task by
     */
    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Checks if the command is an Exit Command
     * @return True if it is an Exit Command
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the Command
     * @param ui Prints the messages based on the type of Command
     * @param storage Saves the modified list of tasks
     * @param taskList List of tasks
     * @throws DukeException if invalid inputs are provided
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        Deadline deadline = new Deadline(description, by);
        taskList.addToList(deadline);
        storage.save(taskList);
        return ui.printAddTask(deadline, taskList.getSize());
    }
}
