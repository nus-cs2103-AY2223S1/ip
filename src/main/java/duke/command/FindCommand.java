package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.EmptyTaskException;
import duke.task.Task;

import java.util.LinkedList;
import java.util.List;

/**
 * A class for the find command.
 */
public class FindCommand extends Command {


    /** The phrase used to search through the list of tasks. */
    private final String query;

    /**
     * Constructor for the FindCommand class.
     *
     * @param query The phrase used to search through the list of tasks.
     */
    public FindCommand(String query) {
        this.query = query;
    }

    /**
     * Executes the "find" command.
     *
     * @param taskList The taskList storing all tasks.
     * @param ui       The ui responsible for interactions with the user.
     * @throws EmptyTaskException If the followup text after the command is empty.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws EmptyTaskException {
        if (this.query.trim().equals("")) {
            throw new EmptyTaskException("find");
        }

        List<Task> lst = taskList.getList();
        List<Task> result = new LinkedList<>();
        lst.forEach(task -> {
            if (task.doesDescriptionContain(this.query)) {
                result.add(task);
            }
        });
        if (result.size() > 0) {
            System.out.println("  Here are the tasks with \"" + this.query + "\" in the description!");
            ui.printTasks(result);
        } else {
            System.out.println("  There was no tasks found with \"" + this.query + "\" in the description!\n"
                    + "  Try entering \"list\" to see what your task list looks like!");
        }
    }
}
