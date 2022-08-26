package commands;

import drivers.Storage;
import drivers.TaskList;
import drivers.UI;
import exceptions.TumuException;

/**
 * Class to be executed when a find command is issued
 * by the user.
 *
 * Finds all entries that contain the keyword inside the task description.
 */
public class FindCmd extends Command {
    private final String body;

    /**
     * Constructor for the FindCmd class.
     * @param body The rest of the instruction issued by the user after command.
     */
    public FindCmd(String body) {
        this.body = body;
    }

    /**
     * Executes the command and gives the appropriate
     * feedback to the user.
     * @param tasks TaskList containing all the tasks currently available.
     * @param ui Specifies how the program interacts with the user.
     * @param storage Stores and retrieves data from a local .txt file.
     * @throws TumuException Parent exception for the program.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws TumuException {

    }
}
