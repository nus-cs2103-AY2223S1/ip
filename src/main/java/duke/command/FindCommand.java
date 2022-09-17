package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.DukeException;

/**
 * FindCommand class to represent an instruction to take in a keyword and list all tasks containing the keyword.
 *
 * @author Sheryl Kong (A0240686Y)
 */


public class FindCommand extends Command {
    private String desc;

    /**
     * Constructor for FindCommand class
     *
     * @param desc String
     */

    public FindCommand(String desc) {
        this.desc = desc;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        this.response = UI.findResponse(taskList, desc);
        UI.find(taskList, this.desc);
    }

}
