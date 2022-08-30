package duke.command;

import duke.exception.DukeException;
import duke.task.TasksList;
import duke.task.Todo;

/**
 * Represents a command to add a Todo to the tasksList.
 */
public class ToDoCommand extends Command {
    private static final String TO_DO_MSG = "Got it. I've added this task:\n";
    private String[] inputArray;
    private TasksList tasksList;

    /**
     * Creates a new ToDo instance.
     * @param tasksList The TasksList to add the ToDo to.
     * @param inputArray The array that represents the user input.
     */
    public ToDoCommand(TasksList tasksList, String[] inputArray) {
        this.tasksList = tasksList;
        this.inputArray = inputArray;
    }

    /**
     * Adds the ToDo to the TasksList.
     * @return The message to be displayed upon the execution of the command.
     * @throws DukeException If the ToDo cannot be added to the TasksList.
     */
    @Override
    public String execute() throws DukeException {
        if (this.inputArray.length < 2) {
            throw new DukeException("The description of a todo cannot be empty!");
        }

        String description = this.inputArray[1];
        // create a ToDo object
        Todo todo = new Todo(description);

        this.tasksList.addToList(todo);
        StringBuilder sb = new StringBuilder();
        sb.append(ToDoCommand.TO_DO_MSG + todo + "\n" + "Now you have ");
        if (this.tasksList.getLength() <= 1) {
            sb.append(this.tasksList.getLength() + " task in the list.\n");
        } else {
            sb.append(this.tasksList.getLength() + " tasks in the list.\n");
        }
        return sb.toString();
    }
}
