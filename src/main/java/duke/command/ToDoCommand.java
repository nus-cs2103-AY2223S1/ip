package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Todo;

/**
 * Encapsulates a class to create a new To-Do.
 */
public class ToDoCommand extends Command {
    /** Description of the To-Do. */
    String desc;

    /**
     * Constructor for ToDoCommand.
     * @param desc Description of To-Do
     */
    public ToDoCommand(String desc) {
        this.desc = desc;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getResponse(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Todo tmp = new Todo(desc);
        tasks.addTodo(tmp);
        storage.write(tasks.toStringWritable());
        return "Got it. I added this task:\n" +
            "\t" + tmp + "\n" +
            "Now you have " + tasks.getLength() + " tasks in the list.";
    }
}
