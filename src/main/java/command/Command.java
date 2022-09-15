package command;

/**
 * An abstract class that represents Command
 *
 * @author  Wee Xin Yang, Markus
 * @version 0.2
 * @since   2022-9-15
 */
import java.text.ParseException;

public abstract class Command {
    public abstract String execute() throws ParseException;
}
