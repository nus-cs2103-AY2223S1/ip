package sally;

import sally.command.Command;
import sally.exception.SallyException;
import sally.parser.Parser;
import sally.storage.Storage;
import sally.task.TaskList;
import sally.ui.Ui;

/**
 * Main class for Sally
 *
 * @author liviamil
 */

public class Sally {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    boolean isBye = false;

    public Sally(String filePath) {
        ui = new Ui();
        try  {
            storage = new Storage(filePath);
        } catch (SallyException e) {
            ui.showError();
        }
        try {
            tasks = new TaskList();
            storage.readsFile(tasks);
        } catch (SallyException e) {
            ui.showError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Sally("Sally/Sally.txt").run();
    }

    public void run() {
        ui.showGreeting();

        while (!isBye) {
            try {
                String stringCommand = ui.getUserCommand();
                Command command = Parser.parseCommand(stringCommand);
                command.execute(tasks, ui, storage);
                isBye = command.isBye();
            } catch (SallyException e) {
                System.out.println(e);
            }
        }
    }

}