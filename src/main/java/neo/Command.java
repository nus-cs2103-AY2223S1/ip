package neo;
import java.io.IOException;

/**
 * Abstract command class.
 */
abstract class Command {
    abstract String complete(String temp) throws NeoException, IOException;
}