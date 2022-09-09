package duke.loanbook.command;

import duke.loanbook.Loanbook;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Abstract class for all Loanbook Commands.
 *
 * @author Elgin
 */
public abstract class LoanbookCommand {
    /**
     * Executes the command and changes the Loanbook encapsulated in Duke.
     *
     * @param loanbook A loanbook that Duke encapsulates.
     * @param ui Ui that controls what gets displayed to the User.
     * @param storage Storage that is in charge of saving into a file.
     * @return Duke's message to the user.
     */
    public abstract String execute(Loanbook loanbook, Ui ui, Storage storage);
}
