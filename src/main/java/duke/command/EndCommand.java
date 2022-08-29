package duke.command;

import duke.*;


/**
 * Encapsulates a command to close the programme.
 */
public class EndCommand extends Command {

    /**
     * A function that closes the programme and prints the relevant messages
     *  @param taskList stores the tasks of the program
     * @param storage reads and writes from the text file which stores the tasks in memory
     * @param ui interfaces with the user using the commandline
     * @return
     */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        String returnString;
        returnString = ui.goodbyeMessage();
        return returnString;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}