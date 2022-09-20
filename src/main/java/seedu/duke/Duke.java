package seedu.duke;

public class Duke {
    private static Storage save;
    private static Parser parser;

    /**
     * A constructor that returns an instance of Duke.
     *
     * @param fileType The location of the save file.
     */
    public Duke(String fileType) {
        save = new Storage(fileType);

        TaskList taskList = new TaskList(save);
        parser = new Parser(taskList);
    }


    /**
     * Returns Duke's response to the given user input.
     *
     * @param input The user's input.
     * @return Duke's response to the input.
     */
    public String getResponse(String input) {
        return parser.parse(input);
    }

}

