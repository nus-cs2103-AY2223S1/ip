import Command.Command;
import Duke.WagwanException;
import Duke.WagwanUi;
import Duke.Parser;
import Duke.TaskList;
import Duke.Storage;

import java.util.ArrayList;

public class Wagwan {

    private Storage storage;
    private TaskList tasks;
    private WagwanUi ui;
    private final String FILE_PATH = "./data/tasks.txt";


    public Wagwan() {
        ui = new WagwanUi();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
            WagwanUi.sendMessage("load complete!");
        } catch (WagwanException e) {
            ui.showLoadingError();
            tasks = new TaskList(new ArrayList<>());
        }
    }

    public String getResponse(String input) {
        try {
            String[] userCommand = ui.readCommand(input);
            Command c = Parser.parse(userCommand);
            return c.execute(tasks, ui, storage);
        } catch (WagwanException e) {
            return e.toString();
        }
    }
}