package duke.command;

import duke.exceptions.DukeInvalidParameterException;
import duke.storage.Storage;
import duke.task.Tag;
import duke.task.TaskList;

public class TagCommand implements Command {
    private final int TO_TAG;
    private final Tag TAG;

    public TagCommand(String[] inputs) {
        String[] parameters = inputs[1].split(" ");
        this.TO_TAG = Integer.parseInt(parameters[0]) - 1;
        this.TAG = new Tag(parameters[1]);
    }

    public String execute(TaskList tasks, Storage storage) {
        String res;
        try {
            res = tasks.addTag(TO_TAG, TAG);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeInvalidParameterException("target to unmark does not exist!");
        }
        storage.refresh(tasks);

        return res;
    }
}
