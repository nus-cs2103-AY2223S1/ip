package command;

import java.text.ParseException;

/**
 * An abstract class that represents Command
 *
 * @author  Wee Xin Yang, Markus
 * @version 0.2
 * @since   2022-9-15
 */
public abstract class Command {
    /**
     * Abstract method for executing commands
     * @return a string after the execution of command
     */
    public abstract String execute() throws ParseException;
}
