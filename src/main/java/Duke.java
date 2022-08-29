import duke.DukeException;
import duke.command.Command;
import duke.main.Parser;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private static final Scanner scan = new Scanner( System.in );
    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.loadInTasks());
        } catch (IOException ioe) {
            System.exit(0);
        } catch (DukeException de) {
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) throws DukeException {
        new Duke("data/tasks.txt").run();
    }

    public void run() {
        ui.printIntro();
        boolean isExit = false;
        while (!isExit) {
            try {
//                getInput(scan);
                String inData;
                inData = scan.nextLine();
                Command command = Parser.parseCommand(inData);
                isExit = command.isExitCommand();
                command.execute(tasks, ui, storage);
            } catch (DukeException de) {
                ui.printDukeException(de);
            } catch (IOException ie) {
                ui.printIoException(ie);
            }
        }

    }

}
