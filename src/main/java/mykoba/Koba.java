package mykoba;

import java.io.File;

import gui.Launcher;

import command.Command;
import exception.KobaException;

/**
 * This class represents a chat bot.
 */
public class Koba {
    private Ui ui;
    private Storage storage;
    private TaskList tasklist;

    /**
     * Constructs a Koba chat bot.
     *
     * @param filePath file path of the storage file.
     */
    public Koba(String filePath) {
        try {
            File file = new File(filePath);
            storage = new Storage(file);
            tasklist = new TaskList();
            storage.loadFromFile(tasklist);
            ui = new Ui(tasklist, storage);
        } catch (KobaException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns a String as a response to user input.
     *
     * @param input user input parsed as a String.
     * @return a response.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            assert c instanceof Command;
            String response = c.execute(tasklist, ui, storage);
            assert !response.equals("");
            return response;
        } catch (KobaException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns intro message.
     *
     * @return Introduction message.
     */
    public String getIntro() {
        return ui.welcome();
    }

    public static void main(String[] args) {
        Launcher.main(args);
    }
}
