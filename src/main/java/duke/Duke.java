package duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * The class that sets up the other classes to run.
 */
public class Duke {

    private Parser parser;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor of Duke class.
     *
     * @param filePath The String that is the relative path to the text document.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList();
        this.parser = new Parser(this.storage, this.tasks);
        this.ui = new Ui(this.parser);
        try {
            this.parser.load();
            System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("Date and Time format error: check input or file, " + e.getMessage());
        } catch (DukeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Runs the ui function.
     */
    public void run() {
        this.ui.run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            return this.parser.reply(input);
        } catch (IOException e) {
            return e.getMessage();
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args)  {
        new Duke("data/tasks.txt").run();
    }
}

