package duke;

/**
 * Command interface to be passed onto Parser.
 */
interface Command {
    String execute(String string) throws DukeException;
}
