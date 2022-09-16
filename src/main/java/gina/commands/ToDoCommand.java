package gina.commands;

import gina.GinaException;
import gina.Storage;
import gina.TaskAndContactList;
import gina.Ui;
import gina.task.ToDo;

/**
 * Represents the command to create a to-do.
 */
public class ToDoCommand extends Command {
    private String input;

    /**
     * Constructs command to create a new To-Do task with the specified description input.
     *
     * @param input The user input for task description.
     */
    public ToDoCommand(String input) {
        this.input = input;
        assert(input != null);
    }

    /**
     * {@inheritDoc}
     */
    public String execute(TaskAndContactList taskAndContactList, Ui ui, Storage storage) throws GinaException {
        if (input.isBlank()) {
            throw new GinaException("Hold your horses! The description can't be empty!");
        }
        ToDo newTodo = new ToDo(input);
        taskAndContactList.addTask(newTodo);
        storage.save(taskAndContactList);
        return ui.showAddTask(newTodo, taskAndContactList);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return false;
    }
}
