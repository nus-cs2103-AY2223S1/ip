package duke.command;

import duke.exceptions.DukeBadFormatException;
import duke.exceptions.DukeIndexRangeException;
import duke.storage.Storage;
import duke.task.Tag;
import duke.task.TaskList;

/**
 * Represents the command for the tagging a task.
 */
public class TagCommand implements Command {
    private final int TO_TAG;
    private final Tag TAG;

    /**
     * Constructs a TagCommand.
     *
     * @param inputs An array of String input obtained from parsing the user input.
     */
    public TagCommand(String[] inputs) {
        try {
            String[] parameters = inputs[1].split(" ");
            this.TO_TAG = Integer.parseInt(parameters[0]) - 1;
            this.TAG = new Tag(parameters[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeBadFormatException("tag <integer: toTag> <tagName>");
        }

    }

    /**
     * Executes the tag command and prints the results of this add command.
     *
     * @param tasks TaskList which contains all the tasks Duke currently has.
     * @param storage Storage created when starting Duke.
     * @throws DukeIndexRangeException Exception when target to tag does not exist.
     */
    public String execute(TaskList tasks, Storage storage) throws DukeIndexRangeException {
        String res = tasks.addTag(TO_TAG, TAG);
        storage.refresh(tasks);
        return res;
    }
}
