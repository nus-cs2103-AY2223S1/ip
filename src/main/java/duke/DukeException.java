package duke;

/**
 * An exception class to specify exceptions that may occur specific
 * to duke program
 */
class DukeException extends Exception {

    public DukeException(String error) {
        super(error);
    }
}
