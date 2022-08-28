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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Todo curr = new Todo(input);
        tasks.add(curr);
        System.out.println("Got it. I've added this task: ");
        System.out.println(curr);
        System.out.println("Now you have " +tasks.getCount() + " tasks in the list.");
    }


}
