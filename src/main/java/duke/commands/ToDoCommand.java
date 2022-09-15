package duke.commands;

import duke.*;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDos;
import duke.ui.Ui;

public class ToDoCommand extends Command {

    private String input;
    private Ui ui;
    private TaskList taskList;

    public ToDoCommand(String input, Ui ui, TaskList taskList) {
        this.input = input;
        this.ui = ui;
        this.taskList = taskList;
    }

    public String execute() throws DukeException{
        String[] splitInput = input.split(" ");
        if (splitInput.length == 1) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        String taskName = input.substring(5);
        Task newTask = new ToDos(taskName);
        taskList.add(newTask);
        return ui.printOnAdd(newTask, taskList);
    }
}
