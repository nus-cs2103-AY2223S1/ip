package duke.main;

import java.util.InputMismatchException;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.ErrorCommand;

/**
 * Represents the duke.main.Duke object. Given a filepath, it is able to store
 * and load tasks for the user
 */
public class Duke {
    private Storage storage;
    private Ui ui;
    private Parser parser;
    private TaskList taskList;
    private boolean isBye = false;
    /**
     * Constructor for duke.main.Duke Object
     *
     * @param filePath
     */
    public Duke(String filePath) {
        ui = new Ui();
        ui.greet();
        storage = new Storage(filePath);
        parser = new Parser();
        taskList = new TaskList(storage.loadTasks());
        ui.list(taskList);
    }

    public String getResponse(String input) {
        try {
            Command command = parser.parse(input);
            assert command instanceof Command : "Not a command!";
            if (command instanceof ByeCommand) {
                isBye = true;
            }
            String commandString = command.execute(taskList, ui, storage);
            String numItemsString = "\n\n You have "
                    + taskList.length() + " items in the list btw! hehe";
            String response = commandString + numItemsString;
            return response;
        } catch (InputMismatchException | IndexOutOfBoundsException
                | NumberFormatException | NullPointerException e) {
            return new ErrorCommand().execute(taskList, ui, storage);
        }
    }

    public Ui getUi() {
        return this.ui;
    }

    public boolean isBye() {
        return isBye;
    }

}
