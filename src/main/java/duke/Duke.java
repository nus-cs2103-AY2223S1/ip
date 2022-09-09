package duke;

import java.util.ArrayList;
import java.io.IOException;

/** A class that initialises and runs the Duke bot. */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
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
        this.ui = new Ui();
        this.parser = new Parser(this.taskList, this.storage);

        this.storage.loadUpData();

    }

    /**
     * An empty constructor for the Duke bot.
     */
    public Duke() {

    }

    /**
     * Runs the UI of the bot.
     */
    public String runUi() {
        return this.ui.run();
    }

    /**
     * Parses the input entered by the user.
     */
    public String parse(String text) {
        return this.parser.parse(text);
    }

    /*
    public static void main(String[] args) {
        try {
            Duke dukeBot = new Duke(new TaskList(new ArrayList<>(100)), "taskList.txt");

            dukeBot.runUi();

            dukeBot.parse();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    } */

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