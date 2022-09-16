package scruffles;

/**
 * An exception that's thrown when the program receives an unknown input
 *
 * @author Shamus Tan
 */
public class UnknownArgumentException extends Exception {

    public UnknownArgumentException(String arg) {
        super("grrr >:( i don't know what " + arg + " is woof woof!");
    }
}
