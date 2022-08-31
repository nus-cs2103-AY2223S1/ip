package kirby.commands;

import java.io.IOException;

import kirby.Storage;
import kirby.TaskList;
import kirby.ui.Ui;
import kirby.exceptions.KirbyMissingArgumentException;
import kirby.tasks.Todo;

/**
 * ToDoCommand class handles the command to create a Todo task.
 */
public class ToDoCommand extends Command {
    private String inputString;

    /**
     * Constructor for the class DeadlineCommand.
     *
     * @param inputString arguments of a command.
     */
    public ToDoCommand(String inputString) {
        this.inputString = inputString;
    }
    /**
     * {@inheritDoc}
     * Creates a ToDo task if arguments are valid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws KirbyMissingArgumentException {
        if (inputString.length() <= 5) {
            throw new KirbyMissingArgumentException("todo");
        }
        String taskName = inputString.substring(inputString.indexOf(' ') + 1);
        Todo todo = new Todo(taskName);
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
