package duke.command;

import duke.exception.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;
import duke.task.ToDo;

/**
 * TodoCommand class to execute the todo command.
 */
public class TodoCommand extends Command {
    /** The input given by the user. */
    private String input;

    /**
     * Instantiates the TodoCommand object.
     *
     * @param input The input given by the user.
     */
    public TodoCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        String[] taskArray = input.split(" ", 2);

        if (taskArray[1].isEmpty()) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.\n");
        } else {
            Task todo = new ToDo(taskArray[1]);
            int index = taskList.size() + 1;
            taskList.add(todo);
            this.response = "Got it. I've added this task:\n" + todo.toString() + "\n" + "Now you have "
                    + index + " tasks in this list.\n";
        }
    }
}
