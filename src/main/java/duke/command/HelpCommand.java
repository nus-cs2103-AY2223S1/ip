package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;

/**
 * Command to execute helping the user to know all the tasks Duke can do
 *
 * @author Nephelite
 * @version 0.2
 */
public class HelpCommand extends Command {
    /**
     * {@inheritDoc}
     *
     * @param storage Duke's storage system for tasks
     * @return Duke's response to the execution of the command
     * @throws DukeException for invalid inputs
     * @since 0.3
     */
    @Override
    public String execute(Storage storage) {
        String response = "These are the commands I know.\n";
        for (RecognisedCommand e : RecognisedCommand.values()) {
            switch (e) {
            case BYE:
                response += "bye:\nEnds my service.\n";
                break;
            case LIST:
                response += "list:\nLists all the tasks I have been given to track.\n";
                break;
            case HELP:
                response += "help:\nLists all the commands I know.\n";
                break;
            case MARK:
                response += "mark:\nFormat: mark x, where x is an integer."
                        + "\nMarks the task that is index x on the list as done.\n";
                break;
            case UNMARK:
                response += "unmark:\nFormat: unmark x, where x is an integer."
                        + "\nMarks the task that is index x on the list as not done.\n";
                break;
            case DELETE:
                response += "delete:\nFormat: delete x, where x is an integer."
                        + "\nMarks the task that is index x on the list as done.\n";
                break;
            case TODO:
                response += "todo:\nFormat: todo <duke.task>"
                        + "\nI will add the <task> to the list of tasks.\n";
                break;
            case DEADLINE:
                response += "deadline:\nFormat: todo <task> /by <time/date>"
                        + "\nI will add the <task> to the list of tasks."
                        + "\nThe <task> will also display its deadline at <time/date>.\n";
                break;
            case EVENT:
                response += "event:\nFormat: todo <task> /at <time/date"
                        + "\nI will add the <task> to the list of tasks."
                        + "\nThe <task> will also display the <time/date> the task should be done.\n";
                break;
            case FIND:
                response += "find:\nFormat: find <keyword>"
                        + "\nI will search for all tasks that possesses the <keyword>\n";
                break;
            case TAG:
                response += "tag:\nFormat: tag <task index> <description>"
                        + "\nI will tag the task in the task list with the <task index> index, with the"
                        + "<description>\n";
                break;
            default:
            }
        }
        return response;
    }
    /**
     * {@inheritDoc}
     *
     * @return true if the command ends the current session. Otherwise, false.
     * @since 0.1
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
