package jduke;

import jduke.commands.ByeCommand;
import jduke.commands.Command;
import jduke.data.TaskList;
import jduke.data.exception.JdukeException;
import jduke.parser.Parser;
import jduke.storage.Storage;
import jduke.ui.Ui;

/**
 * The main entry point to the program.
 */
public class Jduke {
    private TaskList tasklist;
    private Storage storage;
    private Ui ui;

    private Jduke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasklist = new TaskList(storage.load());
        } catch (JdukeException e) {
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
        } catch (JdukeException e) {
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
        new Jduke("data/tasks.txt").run();
    }
}
