package duke;

/**
 * Custom exceptions relating to Duke.
 */
class DukeException extends Exception {

    public static final DukeException IDTOOBIG = new DukeException("id is larger than current list size");
    public static final DukeException INVALIDARGUMENT = new DukeException("Invalid argument");
    public static final DukeException INVALIDCOMMAND = new DukeException("Invalid command");
    public static final DukeException TASKALREADYMARKED = new DukeException("Task already marked as done");
    public static final DukeException TASKALREADYUNMARKED = new DukeException("Task already marked as not done");
    private DukeException(String text) {
        super(text);
    }
};

