package duke.command;

import java.time.LocalDate;

import duke.DukeException;
import duke.Response;
import duke.Ui;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;

/**
 * Adds Deadline task in tasklist.
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
        String message = "+ Added this deadline:\n" + deadline + "\n You have " + taskList.listSize()
                + " tasks in the list NOW \n";
        Response response = new Response(message, false, false);
        ui.setResponse(response);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
