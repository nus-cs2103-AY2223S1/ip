package blink.command;

import blink.BlinkException;
import blink.Storage;
import blink.TaskList;
import blink.Ui;
import blink.task.ToDos;

/**
 * ToDo command to create a new ToDo task.
 */
public class TodoCommand extends Command {

    private String desc;

    /**
     * Constructor that takes in description information
     * of ToDo object.
     *
     * @param input Description of the ToDO object
     * @throws BlinkException Thrown if information of ToDo
     * object is missing
     */
    public TodoCommand(String input) {
        if (input.isBlank()) {
            throw new BlinkException("Missing description for Todo");
        }
        this.desc = input;
    }

    /**
     * Causes for ToDo object to be created in TaskList, then Ui will
     * display information of the ToDo object which is then saved in
     * Storage.
     *
     * @param tasks TaskList object of current Blink object
     * @param ui Ui object of current Blink object
     * @param storage Storage object of current Blink object
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ToDos event = tasks.addTodo(this.desc);
        storage.save(tasks);
        return ui.showAddTask(tasks, event);
    }

    /**
     * Todo command does not end the program thus returns false.
     *
     * @return False
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
