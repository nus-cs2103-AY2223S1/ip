package duke;

/**
 * Command interface to be passed onto Parser.
 */
interface Command {
    void execute(String string) throws DukeException;
}
