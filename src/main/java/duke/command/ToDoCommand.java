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
    private String note;

    /**
     * Public constructor for a ToDoCommand.
     *
     * @param description the Description of the Todo
     */
    public ToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Public constructor for a ToDoCommand.
     *
     * @param description the Description of the Todo
     * @param note Optional note of the Todo
     */
    public ToDoCommand(String description, String note) {
        this.description = description;
        this.note = note;
    }

    /**
     * Executes the ToDoCommand by creating a new Todo and adding it to the TaskList.
     *
     * @param taskList A list of tasks
     * @param ui An ui responsible for printing output to the user
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        return ui.printAddTask(taskList.addToDo(this.description, this.note), taskList.size());
    }
}
