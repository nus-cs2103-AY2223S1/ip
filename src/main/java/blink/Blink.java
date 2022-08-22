package blink;

import blink.command.Command;

import java.time.DateTimeException;

public class Blink {

    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    public Blink(String path)  {
        ui = new Ui();
        storage = new Storage(path);
        try {
            tasks = new TaskList(storage.load());
        } catch (BlinkException e) {
            tasks = new TaskList();
        }
    }

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
        String path = System.getProperty("user.home") + "/blink/blink.txt";
        Blink blink = new Blink(path);
        blink.run();
    }
}

