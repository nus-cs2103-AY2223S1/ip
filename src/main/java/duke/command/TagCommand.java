package duke.command;

import duke.exceptions.DukeException;
import duke.exceptions.DukeInvalidParameterException;
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
        String[] parameters = inputs[1].split(" ");
        this.TO_TAG = Integer.parseInt(parameters[0]) - 1;
        this.TAG = new Tag(parameters[1]);
    }

    /**
     * Executes the tag command and prints the results of this add command.
     *
     * @param tasks TaskList which contains all the tasks Duke currently has.
     * @param storage Storage created when starting Duke.
     * @throws DukeException Exception from executing command.
     */
    public String execute(TaskList tasks, Storage storage) {
        String res;
        try {
            res = tasks.addTag(TO_TAG, TAG);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeInvalidParameterException("target to tag does not exist!");
        }
        storage.refresh(tasks);

        return res;
    }
}
