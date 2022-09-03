package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Todo;
import duke.Ui;

public class TodoCommand extends Command {
    /**
     * Command line input.
     */
    private String input;

    public TodoCommand (String input) {
        this.input = input;
    }

    /**
     * Creates a todo task and stores it.
     *
     * @param tasks List of tasks.
     * @param ui User interface for duke.
     * @param storage Storage information for tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Todo curr = new Todo(input);
        tasks.add(curr);
        StringBuilder toReturn = new StringBuilder();
        toReturn.append("Got it. I've added this task: \n");
        toReturn.append(curr + "\n");
        toReturn.append("Now you have " +tasks.getCount() + " tasks in the list.\n");
        return toReturn.toString();
    }


}
