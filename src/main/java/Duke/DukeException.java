package Duke;

/**
* Class that catches all possible exceptions that occur within Duke and
* prints the error occured without terminating the program so the user
* will be able to know what caused the error and how to rectify it.
*
* @author Linus Chui
*/
public class DukeException extends Exception {

    public DukeException(String errorMessage) {
        super(" ☹ OOPS!!! " + errorMessage);
    }

    @Override
    public String toString() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
