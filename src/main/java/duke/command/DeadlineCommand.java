package duke.command;

import java.time.LocalDate;

import duke.DukeException;
import duke.Ui;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;

/**
 * Adds Deadline task in duke's tasklist.
 */
public class DeadlineCommand extends Command {

    private String description;
    private LocalDate by;

    /**
     * Creates a Deadline command object for execution in Duke class.
     * @param description  details of task.
     * @param by date of when task needs to be completed.
     */
    public DeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Deadline deadline = new Deadline(description, by);
        taskList.addTask(deadline);
        ui.printMessage("+ Added this deadline:\n" + deadline + "\nNow you have " + taskList.listSize()
                + " tasks in the list\n");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
