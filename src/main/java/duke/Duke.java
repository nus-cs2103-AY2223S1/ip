package duke;

/**
 * A Personal Assistant Chatbot that helps a person keep track of several things like tasks.
 *
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    /**
     * The constructor of the Duke class.
     *
     */
    public Duke() {
        this.storage = new Storage("data/tasks.txt");
        this.taskList = new TaskList();
        this.storage.load(this.taskList);
        this.ui = new Ui();
        this.parser = new Parser();
    }

    /**
     * Gets a response from the Duke bot
     *
     * @param input
     * @return response String
     */
    public String getResponse(String input) {
        return this.parser.parse(input, this.ui, this.taskList, this.storage);
    }

    /**
     * Main method which instantiates the Duke chatbot and runs it.
     *
     * @param args
     */
    public static void main(String[] args) {


    }
}
