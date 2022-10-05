package ted.command;

import ted.Storage;
import ted.exception.TedException;
import ted.task.TaskList;
import ted.ui.UiController;

/**
 * A class that encapsulate a DeadlineCommand, to
 * show help message
 */
public class HelpCommand extends Command {

    /**
     * Construct a help command
     * @param args
     */
    public HelpCommand(String args) {
        super(args);
    }

    /**
     * Show help to user
     * @param tasks
     * @param ui
     * @param storage
     * @throws TedException
     */
    @Override
    public void run(TaskList tasks, UiController ui, Storage storage) throws TedException {
        ui.output("usage: <command> [<args>] \n"
                + "list      list out all the tasks\n\n"
                + "These are commands to be used for creating various types of task:\n"
                + "todo      create a simplest task\n"
                + "event     create an event with /at operator (event hello world /at 2022-03-12)\n"
                + "deadline  create a deadline tasks with /by operator (deadline /by yyyy-mm-dd)\n\n"
                + "These are commands to be used for modifying the task list:\n"
                + "mark      mark task as completed\n"
                + "unmark    mark task as incomplete\n"
                + "delete    delete a task from task list"
        );
    }
}
