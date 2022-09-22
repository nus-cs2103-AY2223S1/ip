package cinnamon.command.add;

import cinnamon.Exception.DukeException;
import cinnamon.Handler.Ui;
import cinnamon.Storage.Storage;
import cinnamon.Tasks.TaskList;
import cinnamon.Tasks.Deadline;
import cinnamon.Tasks.Task;
import cinnamon.command.Command;

public class AddDeadline extends Command{

    String input;
    String taskName;
    String taskDate;
    String taskTime;

    /**
     * Constructor of an add deadline command
     *
     * @param input A string containing task information
     */
    public AddDeadline(String input) {
        this.input = input;
        String[] parts = input.split(" ");
        this.taskDate = parts[parts.length - 2];
        this.taskTime = parts[parts.length - 1];
        this.taskName = input.substring(input.indexOf(" ") + 1, input.indexOf("/") - 1).trim();
    }

    /**
     * Adds a deadline task
     *
     * @param taskList containing all tasks added
     * @param ui gives messages
     * @param storage stores added tasks into a txt file
     * @return response of cinnamon
     * @throws DukeException specific exceptions
     */
    public String execute (TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Deadline dl = new Deadline(taskName, taskDate, taskTime);
        if (input.length() <= 9) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        taskList.add(dl);
        assert (ui != null);
        return ui.addTask() + ui.printDeadline(dl);
    }
}
