package wagwan;

/**
* Class that catches all possible exceptions that occur within Duke and
* prints the error occured without terminating the program so the user
* will be able to know what caused the error and how to rectify it.
*
* @author Linus Chui
*/
public class WagwanException extends Exception {

    public WagwanException(String errorMessage) {
        super(" OOPS!!! " + errorMessage);
    }

}
