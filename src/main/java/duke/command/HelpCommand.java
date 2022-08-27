package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;

/**
 * Command to execute helping the user to know all the tasks Duke can do
 * @author Nephelite
 * @version 0.2
 */
public class HelpCommand extends Command {
    /**
     * {@inheritDoc}
     * @param storage Duke's storage system for tasks
     * @return Duke's response to the execution of the command
     * @throws DukeException for invalid inputs
     * @since 0.2
     */
    @Override
    public String execute(Storage storage) {
        System.out.println("These are the commands I know.");
        for (RecognisedCommand e : RecognisedCommand.values()) {
            switch (e) {
            case BYE:
                return "Ends my service.";
            case LIST:
                return "Lists all the tasks I have been given to track.";
            case HELP:
                return "Lists all the commands I know.";
            case MARK:
                return "Format: mark x, where x is an integer."
                        + "\nMarks the task that is index x on the list as done.";
            case UNMARK:
                return "Format: unmark x, where x is an integer."
                        + "\nMarks the task that is index x on the list as not done.";
            case DELETE:
                return "Format: delete x, where x is an integer."
                        + "\nMarks the task that is index x on the list as done.";
            case TODO:
                return "Format: todo <duke.task>"
                        + "\nI will add the <task> to the list of tasks.";
            case DEADLINE:
                return "Format: todo <task> /by <time/date>"
                        + "\nI will add the <task> to the list of tasks."
                        + "\nThe <task> will also display its deadline at <time/date>.";
            case EVENT:
                return "Format: todo <task> /at <time/date"
                        + "\nI will add the <task> to the list of tasks."
                        + "\nThe <task> will also display the <time/date> the task should be done.";
            case FIND:
                return "Format: find <keyword>"
                        + "\nI will search for all tasks that possesses the <keyword>";
            default:
                return null;
            }
        }
        return null;
    }
    /**
     * {@inheritDoc}
     * @return true if the command ends the current session. Otherwise, false.
     * @since 0.1
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
