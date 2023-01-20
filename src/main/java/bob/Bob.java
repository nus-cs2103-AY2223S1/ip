package bob;

import bob.commands.Command;

/**
 * Represents a chatbot, Bob, which records down and saves tasks inputted by user.
 */
public class Bob {

    private Storage storage;
    private Ui ui;
    private TaskList taskList;
    private Parser parser;
    private Command command;
    private boolean toExit = false;


    /**
     * Initialises Bob with a Ui, Storage, TaskList and Parser.
     */
    public Bob() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.taskList = this.storage.read();
        this.parser = new Parser();
    }

    public String welcome() {
        return ui.displayWelcomeMessage();
    }

    /**
     * Parses and executes relevant command based on input
     *
     * @param input user input
     * @return response to user input
     */
    public String getResponse(String input) {
        try {
            this.command = this.parser.parse(input, this.taskList);
            this.toExit = this.command.isExit();
            return this.command.executeCommand(this.taskList, this.storage, this.ui);
        } catch (BobException e) {
            return e.getMessage();
        }
    }

    /**
     * Retrieves boolean value toExit
     *
     * @return false unless ByeCommand executed
     */
    public boolean getToExit() {
        return this.toExit;
    }
}
