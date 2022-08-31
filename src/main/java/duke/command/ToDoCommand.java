package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * The ToDoCommand class adds a ToDo task into taskList.
 */
public class ToDoCommand extends Command {
    private String description;

    /**
     * Constructor for a ToDoCommand.
     *
     * @param description description of Todo.
     */
    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Task todo = new ToDo(description);
        taskList.add(todo);

        String message = ui.printAddTask(todo) + '\n';
        message += ui.printSizeOfList(taskList.size());
        storage.save(taskList);
        return message;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
