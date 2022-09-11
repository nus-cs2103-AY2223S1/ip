package bro;

import bro.command.Command;
import javafx.application.Platform;
/**
 * The class for bot.
 */
public class Bro {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private String filePath = "./bro.Bro.txt";
    /**
     * Constructor for the class Bro.
     */
    public Bro() {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (BroException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String result = "";
        try {
            Command c = new Parser().parse(input);
            result += c.execute(tasks, ui, storage);
        } catch (BroException e) {
            result += ui.errorMessage(e.getMessage());
        }
        return result;
    }
}
