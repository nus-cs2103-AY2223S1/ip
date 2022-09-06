package betago;

import betago.commands.Command;
/**
 * Duke class that contains the main program and initialises the various objects used such as
 * TaskList, Parser and Storage.
 */
public class Duke {

    /** Storage variable to load and save tasks from data file */
    private final Storage storage;

    /** TaskList variable to store tasks */
    private final TaskList tasks;

    /** Parser variable to read commands from the user */
    private final Parser commander;

    /**
     * Constructor for Duke.
     * Initialises Storage, TaskList and Parser variable.
     */
    public Duke() {
        this.tasks = new TaskList();
        this.storage = new Storage(this.tasks);
        this.commander = new Parser(this.tasks, this.storage);
        Ui.greet();
        this.storage.loadFile();
    }
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        Command command = this.commander.readCommands(input);
        return command.execute(this.tasks, this.storage, input);
    }

}
