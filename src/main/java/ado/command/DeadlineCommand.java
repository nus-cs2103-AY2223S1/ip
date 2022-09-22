package ado.command;

import java.time.LocalDate;

import ado.AdoException;
import ado.Response;
import ado.Ui;
import ado.storage.Storage;
import ado.task.Deadline;
import ado.task.TaskList;

/**
 * Adds Deadline task in tasklist.
 */
public class DeadlineCommand extends Command {

    private String description;
    private LocalDate by;

    /**
     * Creates a Deadline command object for execution in Ado class.
     * @param description  details of task.
     * @param by date of when task needs to be completed.
     */
    public DeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AdoException {
        Deadline deadline = new Deadline(description, by);
        taskList.addTask(deadline);
        String message = "+ Added this deadline:\n" + deadline + "\n You have " + taskList.listSize()
                + " tasks in the list NOW \n";
        Response response = new Response(message, false, false);
        ui.setResponse(response);
        storage.saveTasksInStorage(taskList.getList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
