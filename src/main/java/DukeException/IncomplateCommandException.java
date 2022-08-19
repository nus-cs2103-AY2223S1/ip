package DukeException;

public class IncomplateCommandException extends DukeException{
    /**
     * The error shown when user entered commands that needs (multiple) input, but input not found.
     * 1. mark/unmark without index
     * 2. todo/deadline/event without any description
     * @param msg error message.
     */
    public IncomplateCommandException(String msg, String suggestion) {
        super(msg + "\nYou may need to enter additional information for this command to be executed. PLease check!\n* Suggestion: " + suggestion);
    }
}