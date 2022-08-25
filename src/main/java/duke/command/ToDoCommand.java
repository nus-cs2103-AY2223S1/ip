package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.task.ToDo;
import duke.Ui;

/**
 * ToDoCommand adds a ToDo to tasks.
 */
public class ToDoCommand extends Command {
    private String toDoDescription;

    /**
     * Constructor for ToDoCommand.
     * @param toDoDescription ToDo description.
     */
    public ToDoCommand(String toDoDescription) {
        super();
        this.toDoDescription = toDoDescription;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ToDo toDo = new ToDo(toDoDescription);
        taskList.addToTaskList(toDo);
        ui.showAddTaskMessage(taskList, toDo);
    }
}
