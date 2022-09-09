package duke.loanbook.command;

import duke.loanbook.Loanbook;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Deletes a contact from Loanbook command.
 *
 * @author Elgin
 */
public class DeleteContactCommand extends LoanbookCommand {
    private static String nameToDelete;

    /**
     * Constructor of DeleteContact command.
     *
     * @param nameToDelete The name of the contact to be deleted.
     */
    public DeleteContactCommand(String nameToDelete) {
        DeleteContactCommand.nameToDelete = nameToDelete;
    }

    /**
     * Executes the delete command and removes the contact form the loanbook.
     *
     * @param loanbook A loanbook that Duke encapsulates.
     * @param ui Ui that controls what gets displayed to the User.
     * @param storage Storage that is in charge of saving into a file.
     * @return Duke's message to the user.
     */
    @Override
    public String execute(Loanbook loanbook, Ui ui, Storage storage) {
        String deletedContact = loanbook.deleteContact(DeleteContactCommand.nameToDelete);

        return Ui.getContactDeletedMsg(deletedContact);
    }
}
