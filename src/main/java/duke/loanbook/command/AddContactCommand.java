package duke.loanbook.command;

import duke.exception.DukeException;
import duke.loanbook.Loanbook;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Adds a contact to the Loanbook command.
 *
 * @author Elgin
 */
public class AddContactCommand extends LoanbookCommand {
    private static String[] arguments;

    /**
     * Constructor of an AddContactCommand.
     *
     * @param arguments An array of strings that precedes the user's command string.
     */
    public AddContactCommand(String... arguments) {
        if (arguments.length != 4) {
            throw new DukeException("Wrong use of loanbook add! Usage 'loanbook add name number amount isOwe");
        }

        AddContactCommand.arguments = arguments;
    }

    /**
     * Executes the AddContactCommand.
     *
     * @param loanbook A loanbook that Duke encapsulates.
     * @param ui Ui that controls what gets displayed to the User.
     * @param storage Storage that is in charge of saving into a file.
     * @return Duke's message to the user.
     */
    @Override
    public String execute(Loanbook loanbook, Ui ui, Storage storage) {
        String name = arguments[0];
        String phoneNumber = arguments[1];
        double amount = Double.parseDouble(arguments[2]);
        boolean isOwe = Boolean.parseBoolean(arguments[3]);

        String addedContactName = loanbook.addContact(name, phoneNumber, amount, isOwe);

        return Ui.getContactAddedMsg(addedContactName);
    }
}
