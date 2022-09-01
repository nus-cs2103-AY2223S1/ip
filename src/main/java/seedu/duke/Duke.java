package seedu.duke;

public class Duke {
    private static Storage save;
    private static Ui ui;
    private static Parser parser;

    /**
     * A constructor that returns an instance of Duke.
     */
    public Duke(String fileType) {
        save = new Storage(fileType);

        TaskList taskList = new TaskList(save);
        parser = new Parser(taskList);
        ui = new Ui(parser);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return parser.parse(input);
    }


    /**
     * Prints out greeting, initialises the TaskList and opens the scanner to start
     * receiving commands.
     */

    /*
    public static void run() {
        ui.introduction();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();

    }

     */


}

