package cinnamon.command.add;

import cinnamon.Exception.DukeException;
import cinnamon.Handler.Ui;
import cinnamon.Storage.Storage;
import cinnamon.Tasks.TaskList;
import cinnamon.Tasks.Event;
import cinnamon.Tasks.Task;
import cinnamon.command.Command;

public class AddEvent extends Command {

    String input;
    String taskName;
    String taskLocation;
    String taskDate;
    String taskTime;

    /**
     * Constructor of an add event command
     *
     * @param input A string containing task information
     */
    public AddEvent(String input) {
        this.input = input;
        String[] parts = input.split(" ");
        this.taskLocation = parts[parts.length - 3];
        this.taskDate = parts[parts.length - 2];
        this.taskTime = parts[parts.length - 1];
        this.taskName = input.substring(input.indexOf(" ") + 1, input.indexOf("/") - 1).trim();
    }

    /**
     * Adds a event task
     *
     * @param taskList containing all tasks added
     * @param ui gives messages
     * @param storage stores added tasks into a txt file
     * @return response of cinnamon
     * @throws DukeException specific exceptions
     */
    public String execute (TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Event event = new Event(taskName, taskLocation, taskDate, taskTime);
        if (input.length() <= 6) {
            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
        }
        taskList.add(event);
        assert (ui != null);
        return ui.addTask() + ui.printEvent(event);
    }
}
