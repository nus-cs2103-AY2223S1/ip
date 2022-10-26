package duke.command;

import duke.*;

/**
 * Command that specifies end of program
 *
 * @author benjytan45678
 * @version 0.1
 */
public class ByeCommand extends Command {

    /**
     * Specifies end of program
     *
     * @param tasks specified list of tasks
     * @param ui specific ui object to interact with
     * @param storage specific storage to store the updated task list
     * @throws DukeException specific error message to be thrown
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, Container container, ContactMap contacts) {
        String message = ui.showBye();
        this.setExit();
        return message;
    }
}
