package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Main class for duke.
 */
public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private Parser parser;
    private Command command;

    /**
     * Constructs a Duke object.
     */
    public Duke() {
        storage = new Storage("data/duke.txt");
        ui = new Ui();
        taskList = new TaskList();
        storage.loadTasks(taskList);
        parser = new Parser(storage, ui, taskList);
    }

    /**
     * Runs the duke program.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        do {
            ui.showInputLine();
            String input = scanner.nextLine();

            try {
                command = parser.parse(input);
                command.execute();
            } catch (NumberFormatException e) {
                ui.showError("Please Enter a valid task number!");
            } catch (IllegalArgumentException e) {
                ui.showError("I'm sorry but I don't know what that means.");
            } catch (DukeException e) {
                ui.showError(e);
            }
        } while (command == null || !command.isExit());
        scanner.close();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    public String greet() {
        return ui.greet();
    }

    public String getResponse(String input) {
        try {
            command = parser.parse(input);
            return command.execute();
        } catch (NumberFormatException e) {
            return ui.showError("Please Enter a valid task number!");
        } catch (IllegalArgumentException e) {
            return ui.showError("I'm sorry but I don't know what that means.");
        } catch (DukeException e) {
            return ui.showError(e);
        }
    }
}
