package duke.loanbook.command;

import duke.loanbook.Loanbook;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Lists all contacts in the Loanbook command.
 *
 * @author Elgin
 */
public class ListContactsCommand extends LoanbookCommand {
    /**
     * Executes the ListContacts command and lists all contacts to the user.
     *
     * @param loanbook A loanbook that Duke encapsulates.
     * @param ui Ui that controls what gets displayed to the User.
     * @param storage Storage that is in charge of saving into a file.
     * @return Duke's message to the user.
     */
    @Override
    public String execute(Loanbook loanbook, Ui ui, Storage storage) {
        return Ui.getLoanbookContacts(loanbook.toString());
    }
}
