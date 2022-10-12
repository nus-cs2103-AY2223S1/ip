package duke.commands;

import duke.DukeException;
import duke.TaskList;
import duke.Storage;
import duke.task.Todo;

import java.util.Arrays;

public class AddTodoCommand extends Command {
    private final String[] arguments;

    public AddTodoCommand(String[] args) {
        this.arguments = args;
    }

    @Override
    public String execute(Storage storage, TaskList tl) throws DukeException {
        if (arguments.length < 2) {
            throw new DukeException("The description of the task cannot be empty!");
        }
        String[] taskDesc = Arrays.copyOfRange(arguments, 1, arguments.length);
        String description = String.join(" ", taskDesc);
        Todo newToDo = new Todo(description);
        tl.addTask(newToDo);
        String response = "Got it! I have added this task to your list:\n  "
                + newToDo.toString()
                + "\nNow you have " + tl.getSize() + " tasks in the list.";
        storage.refreshList(tl.getTasks());
        return response;
    }
}
