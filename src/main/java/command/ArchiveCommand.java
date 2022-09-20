package command;

import exception.MeowerException;
import exception.MeowerFileAddressInvalidException;

import task.Task;
import meower.Storage;
import meower.TaskList;
import meower.Ui;

public class ArchiveCommand {
    private final String MESSAGE_FILE_ADDRESS_ERROR = "User file address invalid, please check pathing";

    private String logFileAddress = "";
    private Ui ui;
    
    public ArchiveCommand() {
    }

    public ArchiveCommand(String newAddress) {
        this.logFileAddress = newAddress;
    }
 
    /** 
     * Executes the functionality of the command, in the tasklist, 
     * UI and storage that are taken in as arguments, in this case saves the chatbot logs
     * @param tasks tasklist from Meower chatbot
     * @param ui ui from Meower chatbot
     * @param storage storage from Meower chatbot
     * @throws MeowerException
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MeowerException {
        //if not a valid address, throw exception
        if (!this.verifyAddress(this.logFileAddress)) {
            throw new MeowerFileAddressInvalidException(MESSAGE_FILE_ADDRESS_ERROR);
        }

        //else, do cleanUp()
        try {
            if (this.logFileAddress.equals("")) {
                return ui.bye(storage.saveToFile());
            } else {
                return ui.bye(storage.saveToFile(this.logFileAddress));
            }
        } catch (MeowerException e) {
            throw e;
        }
    }

    /** 
     * verify if user given file address is valid
     * @param address file path address given by user
     * @return boolean
     */

    private boolean verifyAddress(String address) {
        //pre-process address string
        String addressToCheck = address.strip();
        String[] addressSplit = addressToCheck.split(" ");

        //verification
        if (addressSplit.length > 1) {
            return false;
        }
        if (address.equals("")) {
            return false;
        }
        return true;
    }
    
    /** 
     * Returns the task that will be generated from the command, returns an empty task if no task is to be generated
     * @return Task
     */
    public Task getTask() {
        return Task.empty();
    }
}
