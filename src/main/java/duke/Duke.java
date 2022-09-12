package duke;

/**
 * The driving engine for Duke
 */
public class Duke {
    /**
     * The path to the file containing the user data
     */
    public static final String FILE_PATH = "tasks.txt";

    private final Parser parser;

    /**
     * Constructs an instance with the default file path
     */
    public Duke() {
        Storage storage = new Storage(FILE_PATH);
        parser = new Parser(new TaskList(storage));
    }

    /**
     * Returns Duke's response to user input
     * @param input The input string
     * @return Duke's response
     */
    public String getResponse(String input) {
        try {
            return parser.parseUserCommand(input);
            //                Command c = Parser.parse(fullCommand);
            //                c.execute(tasks, ui, storage);
        } catch (CustomMessageException e) {
            return e.getMessage();
        }
    }
}
