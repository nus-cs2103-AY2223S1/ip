package duke.command;

import duke.Response;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * ToDoCommand represents a command to add a ToDo to the TaskList.
 */
public class ToDoCommand extends Command {
    private TaskList taskList;
    private String[] inputArr;

    /**
     * Creates a ToDoCommand to add a ToDo to the TaskList.
     *
     * @param taskList The TaskList to add the ToDo to.
     * @param inputArr The input String array.
     */
    public ToDoCommand(TaskList taskList, String[] inputArr) {
        this.taskList = taskList;
        this.inputArr = inputArr;
    }

    /**
     * Adds the ToDo to the TaskList.
     *
     * @return The Response to be displayed.
     * @throws DukeException If the input array is invalid.
     */
    @Override
    public Response execute() throws DukeException {
        if (this.inputArr.length < 2) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        ToDo toDo = new ToDo(this.inputArr[1]);
        this.taskList.addTask(toDo);
        return new Response("Got it. I've added this task: \n"
                + toDo + "\n"
                + "Now you have " + this.taskList.getSize() + " tasks in the list.\n");
    }
}
