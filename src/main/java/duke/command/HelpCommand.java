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
                response += "Ends my service.\n";
            case LIST:
                response += "Lists all the tasks I have been given to track.\n";
            case HELP:
                response += "Lists all the commands I know.\n";
            case MARK:
                response += "Format: mark x, where x is an integer."
                        + "\nMarks the task that is index x on the list as done.\n";
            case UNMARK:
                response += "Format: unmark x, where x is an integer."
                        + "\nMarks the task that is index x on the list as not done.\n";
            case DELETE:
                response += "Format: delete x, where x is an integer."
                        + "\nMarks the task that is index x on the list as done.\n";
            case TODO:
                response += "Format: todo <duke.task>"
                        + "\nI will add the <task> to the list of tasks.\n";
            case DEADLINE:
                response += "Format: todo <task> /by <time/date>"
                        + "\nI will add the <task> to the list of tasks."
                        + "\nThe <task> will also display its deadline at <time/date>.\n";
            case EVENT:
                response += "Format: todo <task> /at <time/date"
                        + "\nI will add the <task> to the list of tasks."
                        + "\nThe <task> will also display the <time/date> the task should be done.\n";
            case FIND:
                response += "Format: find <keyword>"
                        + "\nI will search for all tasks that possesses the <keyword>\n";
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
