package duke.commands;

import duke.*;
import duke.task.Task;
import duke.task.ToDo;

/**
 * The TodoCommand class represents user command todo.
 */
public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";

    private String userDescription;

    /**
     * Constructor for ToDoCommand that takes in
     * user input as a String.
     * @param userDescription Specified user input.
     */
    public TodoCommand(String userDescription) {
        this.userDescription = userDescription;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = new ToDo(userDescription);
        tasks.addTask(t);
        storage.save(tasks.getTaskList());
        ui.showAddTask(t, tasks);
    }
}
