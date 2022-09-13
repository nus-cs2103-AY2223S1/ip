package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Command to execute tagging a task
 *
 * @author Nephelite
 * @version 0.3
 */
public class TagCommand extends Command {
    /**
     * Tag to add to task
     */
    private String tag;

    /**
     * TaskList that Duke is using
     */
    private TaskList tasks;

    /**
     * Task to add tag to
     */
    private Task task;

    /**
     * Ui that Duke is using
     */
    private Ui ui;

    /**
     * Constructor for a command to tag a task
     *
     * @param command
     * @param tasks
     * @param ui
     * @throws DukeException
     * @since 0.3
     */
    public TagCommand(String command, TaskList tasks, Ui ui) throws DukeException {
        assert (command != null && tasks != null && ui != null);
        String[] returnedArray = command.split(" ", 3);
        if (returnedArray.length < 3) {
            throw new DukeException("your <tag> command is incomplete. Please use"
                    + "<help> to check on the proper usage of <tag>.");
        }
        int taskId = Parser.parseTaskId(returnedArray[1]);
        if (taskId < 0 || taskId >= tasks.size()) {
            throw new DukeException("the task you are attempting to tag does not exist.");
        }
        assert(taskId >= 0 && taskId < tasks.size());
        this.tag = returnedArray[2];
        this.tasks = tasks;
        this.task = tasks.getTask(taskId);
        this.ui = ui;
    }

    /**
     * {@inheritDoc}
     *
     * @param storage Duke's storage system for tasks
     * @return Duke's response to tagging a task
     * @throws DukeException
     * @since 0.3
     */
    @Override
    public String execute(Storage storage) throws DukeException {
        task.addTag(tag);
        storage.saveDuke(tasks);
        return ui.addTag(task, tag);
    }

    /**
     * {@inheritDoc}
     *
     * @return False
     * @since 0.3
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
