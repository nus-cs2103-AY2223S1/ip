package duke.command;

import duke.dukeexception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * Represents command to get task on a certain day, or to list out all the tasks.
 */
public class OtherCommand extends Command {
    private String fullCommand;
    /**
     * Class constructor. Construct a command for getting or listing tasks.
     * @param fullCommand A string of a line from System.in. Begin with "Get" or "list".
     */
    public OtherCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Execute the get or list command. Print out the results.
     * @param taskList The target taskList will be checked or listed.
     * @param storage The object containing the corresponding file.
     * @param c An Enum indicates the type of task.
     * @return A string indicates execution status of the command.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList taskList, Storage storage, CommandType c) throws DukeException {
        switch (c) {
        case GET:
            return (taskList.getASpecificDay(fullCommand));
        case FIND:
            return (taskList.find(fullCommand));
        case LIST:
            return (taskList.listAllTask());
        default:
            throw new DukeException("Sorry, something went wrong when executing this command.");
        }
    }
}
