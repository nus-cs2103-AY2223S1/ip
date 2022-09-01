package duke;

/**
 * Represents the DUKE chatbot each <code>Duke</code> object contains a <code>Storage</code>,
 * a <code>TaskList</code> and a <code>Ui</code>
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    /**
     * Constructor for Duke class
     */
    public Duke() {
        this.storage = new Storage("duke.txt");
        try {
            tasks = new TaskList(storage.load());
            this.parser = new Parser(storage, tasks);
        } catch (DukeException e) {
            tasks = new TaskList();
            this.parser = new Parser(storage, tasks);
        }


    }

    /**
     * Starts the Duke chatbot
     */
    public String getResponse(String fullCommand) {
        String[] command = fullCommand.split(" ", 2);
        String result = parser.parse(command);
        return result;
    }
}



