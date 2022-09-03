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
        //        try {
        parser = new Parser(new TaskList(storage));
        //        } catch (CustomMessageException e) {
        //            ui.showLoadingError();
        //        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     *
     * @param input The input string
     * @return Duke's response
     */
    public String getResponse(String input) {
        //        The initialisation code that starts the chatbot
        try {
            return parser.parseUserCommand(input);
            //                Command c = Parser.parse(fullCommand);
            //                c.execute(tasks, ui, storage);
        } catch (CustomMessageException e) {
            return e.getMessage();
        }
    }
}
