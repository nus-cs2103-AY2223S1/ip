package blink;

import java.io.FileNotFoundException;
import java.time.DateTimeException;

import blink.command.Command;

/**
 * Main class where the Blink program runs, containing the TaskList,
 * Storage and Ui.
 *
 * @author maxng17
 * @version CS2103T AY 22/23 Sem 1
 */
public class Blink {

    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Constructor for Blink program
     *
     */

    public Blink() {
        this.ui = new Ui();
        this.storage = new Storage(System.getProperty("user.home")
                + "/blink/blink.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Runs the program by parsing user inputs to check if they
     * are suitable commands and act upon them if they are.
     */
    public String getResponse(String input) {
        String err = "Error found: ";
        try {
            Command command = Parser.parse(input);
            return command.execute(this.tasks, this.ui, this.storage);
        } catch (BlinkException e) {
            return err + e.getMessage();
        } catch (NumberFormatException e) {
            return err + "Number input expected";
        } catch (DateTimeException e) {
            return err + "Invalid date input, proper format is YYYY-MM-DD E.g. (2022-09-22)";
        }
    }

}

