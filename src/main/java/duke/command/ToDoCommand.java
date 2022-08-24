package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

public class ToDoCommand extends Command {

    public static final String COMMAND_WORD = "todo";

    private String description;

    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList task, Ui ui, Storage storage) {
        ToDo currToDo = new ToDo(this.description);
        task.addTask(currToDo);
        ui.displayAddTask(currToDo);
        ui.displayNumOfTasks(task.getTaskSize());
        ui.displaySeparator();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
