package duke.commands;

import duke.*;
import duke.ui.Ui;

public class EventCommand extends Command {

    private String input;
    private Ui ui;
    private TaskList taskList;

    public EventCommand(String input, Ui ui, TaskList taskList) {
        this.input = input;
        this.ui = ui;
        this.taskList = taskList;
    }

    public String execute() throws DukeException {
        String[] breakitdown = input.split(" ");
        if (breakitdown.length == 1) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        }
        String desc = input.substring(6);
        if (!desc.contains("/at")) {
            throw new DukeException("OOPS!!! /at keyword not found!");
        }
        String[] taskNameLocation = desc.split("/at ");
        if (taskNameLocation.length == 1) {
            throw new DukeException("OOPS!!! Location of event cannot be empty.");
        }
        String taskName = taskNameLocation[0];
        String location = taskNameLocation[1];
        Task newTask = new Events(taskName, location);
        taskList.add(newTask);
        return ui.printOnAdd(newTask, taskList);
    }
}
