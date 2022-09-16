package duke.command;

import duke.task.TaskList;
import duke.Ui;

/**
 * Class which inherits the Command class for a ToDoCommand
 *
 * @author kaij77
 * @version 0.1
 */
public class ToDoCommand extends Command {
    private String description;

    /**
     * Public constructor for a ToDoCommand.
     *
     * @param description the Description of the Todo
     */
    public ToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the ToDoCommand by creating a new Todo and adding it to the TaskList.
     *
     * @param taskList A list of tasks
     * @param ui An ui responsible for printing output to the user
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.printAddTask(taskList.addToDo(this.description));
        ui.printSizeOfList(taskList.size());
    }
}
