package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;
import exception.DukeFileAddressInvalidException;
import task.Task;

/**
 * Represents a "bye" command from the user that will end the chatbot.
 * @extends Command
 */

public class ByeCommand extends Command {

    private String logFileAddress = "";
    private Ui ui;
    
    public ByeCommand() {
    }

    public ByeCommand(String newAddress) {
        this.logFileAddress = newAddress;
    }

    
    /** 
     * Checks if command will cause chatbot to end.
     * @return boolean
     */
    @Override
    public boolean isEnd() {
        return true;
    }

    
    /** 
     * Executes the functionality of the command, in the tasklist, 
     * UI and storage that are taken in as arguments, in this case saves the chatbot logs
     * @param tasks
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (!this.verifyAddress(this.logFileAddress)) {
            throw new DukeFileAddressInvalidException("User file address invalid, please check pathing");
        }
        try {
            if (this.logFileAddress.equals("")) {
                return ui.bye(storage.cleanUp());
            } else {
                return ui.bye(storage.cleanUp(this.logFileAddress));
            }
        } catch (DukeException e) {
            throw e;
        }
    }

    private boolean verifyAddress(String address) {
        String addressToCheck = address.strip();
        String[] addressSplit = addressToCheck.split(" ");
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
