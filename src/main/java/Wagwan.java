import java.util.ArrayList;

import command.Command;
import wagwan.Parser;
import wagwan.Storage;
import wagwan.TaskList;
import wagwan.WagwanException;
import wagwan.WagwanUi;

/**
* Wagwan helps keep track of user's tasks.
*/
public class Wagwan {

    private Storage storage;
    private TaskList tasks;
    private WagwanUi ui;
    private final String filepath = "./data/tasks.txt";


    /**
     * Constructor for Wagwan
     */
    public Wagwan() {
        ui = new WagwanUi();
        storage = new Storage(filepath);
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
