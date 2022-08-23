import Commands.Command;
import common.Parser;
import TaskList.TaskList;
import common.Storage;
import common.Ui;
import dukeExceptions.DukeException;
import java.io.IOException;


public class Duke {
    static final String storageName = "storage.txt";
    static final String storageDirName = "data";

    private Storage storage;
    private TaskList taskList = new TaskList();

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.initialize();
        duke.runLoop();
        duke.shutdown();
    }

    public void initialize() {
        this.storage = new Storage(storageName, storageDirName);
        try {
            this.storage.initializeStorage();
            this.storage.readFromStorage(this.taskList);
        } catch (IOException e) {
            Ui.printError(e);
            this.taskList = new TaskList();
        }
        Ui.showWelcome();
    }

    public void shutdown() {
        this.storage.writeToStorage(this.taskList);
        Ui.printSaving();
        Ui.printGoodbye();
        Ui.printDivider();
    }

    public void runLoop() {
        boolean isExit = false;
        while (!isExit) {
            try {
                Ui.printGetUserCommand();
                String fullCommand = Ui.readUserCommand();
                Command c = Parser.parseInput(fullCommand);
                c.execute(this.taskList);
                isExit = c.isExit();
            } catch (DukeException e) {
                Ui.printError(e);
            } finally {
                Ui.printDivider();
            }
        }
    }
}
