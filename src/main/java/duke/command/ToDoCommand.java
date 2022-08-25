package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

/**
 * Command to add a todo task to the task list.
 */
public class ToDoCommand extends Command {

    public static final String COMMAND_WORD = "todo";

    private String description;

    /**
     * Constructor for the ToDoCommand Object.
     * @param description
     */
    public ToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Creates a todo task and add it into the task list and display a message
     * that the todo task has been added.
     * @param task The TaskList object of the chatbot.
     * @param ui The Ui object of the chatbot.
     * @param storage The storage object of the chatbot.
     */
    @Override
    public void execute(TaskList task, Ui ui, Storage storage) {
        ToDo currToDo = new ToDo(this.description);
        task.addTask(currToDo);
        ui.displayAddTask(currToDo);
        ui.displayNumOfTasks(task.getTaskSize());
        ui.displaySeparator();
    }

    /**
     * Return true if the command is exit command.
     * @return Return true if the command is an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
