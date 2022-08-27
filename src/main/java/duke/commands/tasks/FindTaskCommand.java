package duke.commands.tasks;

import duke.commands.CommandResult;

/**
 * Find Task Command Class
 */
public class FindTaskCommand extends BaseTaskCommand {
    private final String searchTerm;
    private String successMessage = "Here are the matching tasks:\n";

    /**
     * Find Task Command Constructor
     *
     * @param searchTerm
     */
    public FindTaskCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    /**
     * The execute function is the main function of a command. It is called when
     * the user calls a command and returns a CommandResult object that contains
     * information about whether or not the command was successful, and if it was,
     * what to display to the user. In this case, we are using it to search for
     * tasks in our task list by name. We will be searching for all tasks with names
     * that contain our search term (case insensitive). If there are no matching
     * tasks found then we will return an error message saying so; otherwise we'll
     * return successMessage which contains all of our matching task names separated
     * by newlines.
     *
     * @return A command result
     */
    @Override
    public CommandResult execute() {
        successMessage = String.format(
                "%s%s",
                successMessage,
                taskList.searchTasks(this.searchTerm));
        return new CommandResult(super.formatOutputString(successMessage));
    }
}
