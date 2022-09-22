package meower;

import command.Command;
import exception.MeowerException;
public class Meower {

    private TaskList tasks;
    private Parser parser;
    private Storage storage;
    private Ui ui;

    public Meower() {
        this.parser = new Parser();
        this.tasks = new TaskList();
        this.ui = new Ui(this.tasks);
        this.storage = new Storage(this.tasks, this.ui);
    }

    //methods below taken from textbook and partially adapted

    /**
     * Process user inputs and generate the corresponding commands and ui events
     */
    public String processUserInput(String userInput) {
        try {
            Command nextCommand = parser.parse(userInput);
            return nextCommand.execute(this.tasks, this.ui, this.storage);
        } catch (MeowerException e) {
            return ui.errorMessage(e);
        }
    }

    
    /** 
     * Generates the reponse to a given userinput
     * @param input String representation of the userinput
     * @return String
     */
    public String getResponse(String input) {
        try {
            Command nextCommand = parser.parse(input);
            assert this.tasks.getSize() >= 0: "tasklist size pointer should never be negative";
            return nextCommand.execute(this.tasks, this.ui, this.storage);
        } catch (MeowerException e) {
            return ui.errorMessage(e);
        }
    }
}
