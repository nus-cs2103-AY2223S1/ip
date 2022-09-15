package commands;

import dukeegg.Storage;
import dukeegg.TaskList;
import exceptions.DukeException;
import exceptions.InvalidTaskIndexException;
import task.Task;
import ui.Ui;

/**
 * Tags a task with a some string.
 */
public class TagCommand extends Command {
    public static final String SYNTAX = "tag TAG_NAME TASK_NUMBER";

    private final String[] inputStrings;

    /**
     * Constructs a tag command, which tags the task at the specified index.
     *
     * @param inputStrings The specified input strings.
     */
    public TagCommand(String[] inputStrings) {
        this.inputStrings = inputStrings;
    }

    /**
     * Tags a task with the specified tag.
     * <p>
     * {@inheritDoc}
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.inputStrings.length == 1 || this.inputStrings[1].trim().isEmpty()) {
            throw new DukeException("Invalid format for tag command");
        }

        String[] tagStrings = inputStrings[1].split(" ", 2);
        if (tagStrings.length != 2) {
            throw new DukeException("Invalid format for tag command");
        }
        if (tagStrings[0].isEmpty()) {
            throw new DukeException("Tag cannot be empty!");
        }

        try {
            // Tasks are displayed as 1-indexed, but they are stored as 0-indexed.
            int taskIndex = Integer.parseInt(tagStrings[1].trim()) - 1;

            Task task = tasks.getTask(taskIndex);
            task.setTag(tagStrings[0]);

            return ui.showTagTask(task);
        } catch (NumberFormatException | IndexOutOfBoundsException exception) {
            throw new InvalidTaskIndexException();
        }
    }

    public boolean isExit() {
        return false;
    }
}
