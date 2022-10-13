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
     * Returns the program's response to the user's input.
     *
     * @param input the user input.
     * @return String the program's response.
     */
    public String getResponse(String input) {
        assert input != "" : "Input cannot be empty";
        String fullCommand = input;
        Command c = Parser.parse(fullCommand);
        String output = c.execute(tasks, ui, storage);
        storage.save(tasks.getTaskList());
        return output;
    }

}
