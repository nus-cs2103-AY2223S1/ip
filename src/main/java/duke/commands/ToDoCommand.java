package duke.commands;

import java.util.Objects;

import duke.DukeException;
import duke.Message;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Command to create a new ToDo.
 */
public class ToDoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    public static final String MESSAGE_SUCCESS = "Got it. I've added this todo:\n"
            + "\t%1$s\nNow you have %2$d tasks in your list.";
    public static final String MESSAGE_FAILURE = "Unable to add task.";

    private final ToDo toAdd;

    public ToDoCommand(String description) {
        this.toAdd = new ToDo(description);
    }

    @Override
    public Message execute(TaskList tasks) throws DukeException {
        if (tasks.addTask(this.toAdd)) {
            return new Message(String.format(MESSAGE_SUCCESS, this.toAdd, tasks.size()), false, Message.User.DUKE);
        } else {
            throw new DukeException(MESSAGE_FAILURE);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ToDoCommand)) {
            return false;
        }
        ToDoCommand that = (ToDoCommand) o;
        return Objects.equals(toAdd, that.toAdd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(toAdd);
    }
}
