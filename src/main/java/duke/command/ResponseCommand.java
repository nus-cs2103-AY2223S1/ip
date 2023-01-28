package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.UI;

public class ResponseCommand extends Command {

    /**
     * Constructor for ResponseCommand class
     *
     * @param response String
     */

    public ResponseCommand(String response) {
        this.response = response;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        UI.response(response);
        response = UI.getResponse(response);

    }
}