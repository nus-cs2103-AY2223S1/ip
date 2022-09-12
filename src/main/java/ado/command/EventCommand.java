package ado.command;

import java.time.LocalDateTime;

import ado.AdoException;
import ado.Response;
import ado.Ui;
import ado.storage.Storage;
import ado.task.Event;
import ado.task.TaskList;

/**
 * Adds Event task in tasklist.
 */
public class EventCommand extends Command {
    private String description;
    private LocalDateTime at;

    /**
     * Creates an Event command object for execution in Ado class.
     * @param description details of task.
     * @param at date/time of when task occurs.
     */
    public EventCommand(String description, LocalDateTime at) {
        this.description = description;
        this.at = at;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AdoException {
        Event event = new Event(description, at);
        taskList.addTask(event);
        String message = "+ Added this event:\n" + event + "\nNow you have " + taskList.listSize()
                + " tasks in the list\n";
        Response response = new Response(message, false, false);
        ui.setResponse(response);
        storage.saveTasksInStorage(taskList.getList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
