package Duke;

import Commands.*;

import java.time.format.DateTimeParseException;

public class Duke {
    private Storage storage;
    private TaskList lst;
    private Ui ui;

    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        lst = new TaskList(storage.load());
    }

    public void run() {
        ui.printWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = ui.readCommand();
                Command c = Parser.parse(command);
                c.execute(lst, ui, storage);
                isExit = c.isExit();
            } catch (DateTimeParseException e) {
                ui.printMsg(Constants.invalidDate);
            } catch (Exception e) {
                ui.printMsg(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("list.txt").run();
    }

}
