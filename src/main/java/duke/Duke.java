package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.data.Storage;
import duke.data.TaskList;
import duke.util.DukeException;
import duke.util.Parser;
import duke.util.Ui;

/**
 * Takes in input and chats with user.
 *
 * @author Jicson Toh
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor for Duke Class.
     *
     * @param filePath file path of data.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.loadData());
        this.parser = new Parser();
    }

    public static void main(String[] args) {
        new Duke("src/main/java/duke/data/data.txt").run();
    }

    /**
     * Executes the command input by the user.
     */
    public void run() {
        Scanner input = new Scanner(System.in);
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            storage.saveData(tasks);
            String action = input.nextLine();
            try {
                Command command = parser.parseCommand(action);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Outputs the string of the executed command.
     *
     * @param action action to be executed.
     * @return returns the string of command executed.
     */
    public String getResponse(String action) {
        try {
            Command command = parser.parseCommand(action);
            String result = command.execute(tasks, ui, storage);
            storage.saveData(tasks);
            return result;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
