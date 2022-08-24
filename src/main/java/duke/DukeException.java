package duke;

/**
 * Custom Exception class to print the different errors that can occur when running our programme
 */
public class DukeException extends Exception{
    public DukeException(String str) {
        super(str);
    }
}
