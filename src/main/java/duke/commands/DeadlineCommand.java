package duke.commands;

import duke.tasks.Deadlines;
import duke.ui.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.DukeException;

import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {

    private String input;
    private Ui ui;
    private TaskList taskList;

    public DeadlineCommand(String input, Ui ui, TaskList taskList) {
        this.input = input;
        this.ui = ui;
        this.taskList = taskList;
    }

    public String execute() throws DukeException, DateTimeParseException {
        String[] splitInput = input.split(" ");
        if (splitInput.length == 1) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        }
        String desc = input.substring(9);
        if (!desc.contains("/by")) {
            throw new DukeException("OOPS!!! /by keyword not found!");
        }
        String[] taskNameBy = desc.split("/by ");
        if (taskNameBy.length == 1) {
            throw new DukeException("OOPS!!! Date of deadline cannot be empty.");
        }
        String taskName = taskNameBy[0];
        String by = taskNameBy[1];
        Task newTask = new Deadlines(taskName, by);
        taskList.add(newTask);
        return ui.printOnAdd(newTask, taskList);
    }
}
