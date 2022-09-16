package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.task.Todo;
import duke.utility.Storage;
import duke.utility.Ui;

/**
 * Represents command for Todo keyword which creates
 * a Todo task
 */
public class TodoCommand extends Command {

    /**
     * Instantiates a new Todo command
     */
    public TodoCommand(String input) {
        super(input);
    }

    /**
     * Executes the Todo command which creates
     * a Todo task
     *
     * @param tasks The list containing all the tasks
     * @param ui User interface for printing the message
     * @param storage To write and read from a text file
     * @return Returns String that contains message to be printed by gui
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            //create new task here
            Task newTask = new Todo(super.getInput());
            String message = tasks.addTask(newTask);
            String output = ui.add(tasks.numOfTasks(), message);
            storage.save(newTask);
            return output;
        } catch (DukeException e) {
            return ui.showLoadingError(e.getMessage());
        }

    }

}
