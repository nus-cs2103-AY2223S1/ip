package duke.command;

import duke.*;

/**
 * Command that do not understand user input
 *
 * @author benjytan45678
 * @version 0.1
 */
public class DefaultCommand extends Command {

    /**
     * Tells user that the system does not understand his/her input
     *
     * @param tasks specified list of tasks
     * @param ui specific ui object to interact with
     * @param storage specific storage to store the updated task list
     * @throws DukeException specific error message to be thrown
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, Container container, ContactMap contacts) throws DukeException {
        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
    }
}
