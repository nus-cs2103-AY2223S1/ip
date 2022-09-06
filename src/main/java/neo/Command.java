package neo;
import java.io.IOException;

/**
 * Abstract command class.
 */
abstract class Command {
    abstract void complete(String temp) throws NeoException, IOException;
}