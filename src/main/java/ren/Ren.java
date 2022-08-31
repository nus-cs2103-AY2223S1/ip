package ren;

/**
 * Ren is a Task Manager program that helps a user keep track of and manage their tasks.
 */
public class Ren {
    /** Parser helps the Ren bot to interpret commands from the user. */
    private final Parser parser;

    /**
     * Constructor for a Ren bot.
     */
    public Ren() {
        TaskList tasks = new TaskList(new Storage("data/list.txt"));
        parser = new Parser(tasks);
    }

    /**
     * Interprets commands from the user.
     *
     * @param input The command from the user.
     * @return String containing message from Ren after attempting to execute the command.
     * @throws RenException If the execution failed.
     */
    public String interpret(String input) throws RenException {
        return parser.parseCommand(input);
    }

    /**
     * The types of Tasks supported by Ren.
     */
    enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }
}
