package duke;

import java.util.ArrayList;
import java.io.IOException;

/**
 * A class that initialises and runs the Duke bot.
 *
 */
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
     * @throws IOException
     */
    public Duke(TaskList taskList, String pathName) throws IOException {
        this.taskList = taskList;
        this.storage = new Storage(pathName, this.taskList);
        this.ui = new Ui();
        this.parser = new Parser(this.taskList, this.storage);

        this.storage.loadUpData();

    }

    /**
     * Runs the UI of the bot.
     */
    public void runUi() {
        this.ui.run();
    }

    /**
     * Parses the input entered by the user.
     */
    public void parse() {
        this.parser.parse();
    }

    /**
     * Runs the Duke bot.
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            Duke dukeBot = new Duke(new TaskList(new ArrayList<>(100)), "taskList.txt");

            dukeBot.runUi();

            dukeBot.parse();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}