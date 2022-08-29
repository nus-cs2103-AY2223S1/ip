package john;

import john.commands.ByeCommand;
import john.commands.Command;
import john.data.TaskList;
import john.data.exception.JohnException;
import john.parser.Parser;
import john.storage.Storage;
import john.ui.Ui;

/**
 * The main entry point to the program.
 */
public class John {
    private TaskList tasklist;
    private Storage storage;
    private Ui ui;

    private John(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasklist = new TaskList(storage.load());
        } catch (JohnException e) {
            this.ui.showErrorMessage(e.getMessage());
            this.tasklist = new TaskList();
        }
    }

    private void start() {
        this.ui.showGreeting();
    }
    private void exit() {
        this.ui.showGoodbye();
        System.exit(0);
    }

    private void runCommandLoop() {
        Command command;
        do {
            String input = this.ui.getUserCommand();
            command = new Parser().parseCommand(input);
            String result = executeCommand(command);
            this.ui.showToUser(result);
        } while (!ByeCommand.isBye(command));
    }

    private String executeCommand(Command command) {
        try {
            command.setData(tasklist);
            String result = command.execute();
            storage.saveAllTasks(tasklist.getTasksToStore());
            return result;
        } catch (JohnException e) {
            this.ui.showErrorMessage(e.getMessage());
        }
        return "";
    }

    private void run() {
        start();
        runCommandLoop();
        exit();
    }
    public static void main(String[] args) {
        new John("data/tasks.txt").run();
    }
}
