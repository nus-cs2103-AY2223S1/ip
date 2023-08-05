package duke;

import java.io.IOException;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Duke class which receives a command and reply accordingly.
 *
 * @author Shawn Chew
 * @version CS2103T AY 22/23 Sem 1
 */
public class Duke {
    /**
     * Represents a Ui object.
     */
    private static Ui ui;

    /**
     * Represents a Storage object.
     */
    private Storage storage;

    /**
     * Represents a TaskList object.
     */
    private TaskList tasks;

    /**
     * Represents Duke's response.
     */
    private String response;


    /**
     * A constructor to initialize a Duke object.
     * Loads data in filePath to TaskList object.
     *
     * @param filePath The filePath to where the data is stored.
     */
    public Duke(String filePath) {
        ui = new Ui(this);
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (DukeException e) {
            ui.printError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Runs the duke program.
     */
    public void run() {
        ui.printGreetings();
        boolean isExit = false;
        while (!isExit) {
            isExit = processCommand(isExit);
        }
    }

    private boolean processCommand(boolean isExit) {
        try {
            String fullCommand = ui.getInput();
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
        } catch (ArrayIndexOutOfBoundsException | DukeException e) {
            ui.printError(e);
        } catch (IOException e) {
            ui.printTab("Can't save to duke.txt. There is an invalid data, please edit accordingly");
        } catch (Exception e) {
            try {
                storage.updateData(tasks);
            } catch (IOException e1) {
                e1.getMessage();
            }

        }
        return isExit;
    }

    /**
     * Main method for Duke.
     *
     * @param args Ignored and unused command line arguments.
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    public void setResponse(String response) {
        this.response = response;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
        } catch (ArrayIndexOutOfBoundsException | DukeException e) {
            ui.printError(e);
        } catch (IOException e) {
            ui.printTab("Can't save to duke.txt. There is an invalid data, please edit accordingly");
            this.setResponse("Can't save to duke.txt. There is an invalid data, please edit accordingly");
        } catch (Exception e) {
            try {
                storage.updateData(tasks);
            } catch (IOException e1) {
                e1.getMessage();
            }
        }
        return response;
    }
}