package blink;

import blink.command.Command;

import java.time.DateTimeException;

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
     * @param path The file path of the save file
     */
    public Blink(String path) {
        ui = new Ui();
        storage = new Storage(path);
        try {
            tasks = new TaskList(storage.load());
        } catch (BlinkException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Runs the program by parsing user inputs to check if they
     * are suitable commands and act upon them if they are.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (BlinkException e) {
                ui.showError(e.getMessage());
            } catch (NumberFormatException e) {
                ui.showError("Number input expected");
            } catch(DateTimeException e) {
                ui.showError("Invalid date input");
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        String path =  System.getProperty("user.home") + "/blink/blink.txt";
        Blink blink = new Blink(path);
        blink.run();
    }
}

