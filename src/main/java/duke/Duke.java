package duke;

import java.io.IOException;

/** A class that initialises and runs the Duke bot. */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Parser parser;

    /**
     * A constructor that initialises the Duke bot
     *
     * @param taskList List of tasks.
     * @param pathName The path of the text file to store and load data.
     * @throws IOException From loadUpData() method.
     */
    public Duke(TaskList taskList, String pathName) throws IOException {
        this.taskList = taskList;
        this.storage = new Storage(pathName, this.taskList);
        this.parser = new Parser(this.taskList, this.storage);

        //Load up data from taskList text file
        this.storage.loadUpData();
    }

    /**
     * Parses the input entered by the user.
     */
    public String parse(String text) {
        return this.parser.parse(text);
    }

    /**
     * Returns the response of the Duke bot.
     *
     * @param input Response of Duke bot.
     * @return String as the response of the Duke bot.
     */
    public String getResponse(String input) {
        return input;
    }

}