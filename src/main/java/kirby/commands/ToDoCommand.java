package kirby.commands;

import java.io.IOException;

import kirby.Storage;
import kirby.TaskList;
import kirby.exceptions.KirbyMissingArgumentException;
import kirby.tasks.Todo;
import kirby.ui.Ui;

/**
 * ToDoCommand class handles the command to create a Todo task.
 */
public class ToDoCommand extends Command {
    private final Todo todo;

    /**
     * Constructor for the class ToDoCommand.
     *
     * @param argument arguments of the command.
     * @throws KirbyMissingArgumentException If arguments are not followed with the respective commands.
     *
     */
    public ToDoCommand(String argument) throws KirbyMissingArgumentException {
        if (argument == null) {
            throw new KirbyMissingArgumentException("todo");
        }
        this.todo = new Todo(argument);
    }
    /**
     * {@inheritDoc}
     * Creates a ToDo task if arguments are valid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(todo);
        try {
            storage.writeTask(tasks.getList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks.addTaskString(todo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
