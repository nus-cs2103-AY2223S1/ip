package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.task.ToDo;
import duke.Ui;

public class ToDoCommand extends Command {
    private String command;
    private TaskList tasks;
    private Ui ui;

    public ToDoCommand(String command, TaskList tasks, Ui ui) {
        this.command = command;
        this.tasks = tasks;
        this.ui = ui;
    }

    @Override
    public void execute() throws DukeException {
        String[] returnedArray = command.split(" ");
        if (returnedArray.length == 1) {
            throw new DukeException("your [todo] duke.command is empty." +
                    "\nPlease use the [help] duke.command to check the proper usage of [todo].");
        }
        ToDo toDo = new ToDo(command);
        tasks.add(toDo);
        ui.addTask(toDo, tasks.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
