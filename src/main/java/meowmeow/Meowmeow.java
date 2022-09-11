package meowmeow;

import meowmeow.commands.Command;

/**
 * <p>The Meowmeow class is the main class of the program.</p>
 * <p>This class is used to run the program.</p>
 */
public class Meowmeow {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for the Meowmeow class.
     */
    public Meowmeow() {
        ui = new Ui();
        storage = new Storage("data/meowmeow.txt");
        tasks = new TaskList(storage.load(), storage);
    }

    /**
     * Method that gets the response from the chatbot.
     *
     * @param input the user input.
     */
    public String getResponse(String input) {
        assert input != null : "Input cannot be null";
        String fullCommand = input;
        Command c = Parser.parse(fullCommand);
        String output = c.execute(tasks, ui, storage);
        storage.save(tasks.getTaskList());
        return output;
    }

}
