package duke.data;

import java.util.Scanner;

import duke.commands.Command;
import duke.data.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Main launcher class for the Duke chatbot.
 */
public class Duke {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;


    public Duke() {
        this.ui = new Ui(new Scanner(System.in));
        this.storage = new Storage();
        this.taskList = this.storage.load();
    }

    /**
     * Runs the program.
     */
    public void run() {
        this.ui.greet();
        boolean terminate = false;

        do {
            try {
                String userInput = this.ui.receiveInput();
                Command c = Parser.parseCommand(userInput);
                c.execute(taskList, ui, storage);
                terminate = c.isEnd();
            } catch (DukeException de) {
                System.out.println(de.getMessage());
            } catch (IndexOutOfBoundsException ioobe) {
                System.out.println("Index input is greater than todolist length");
            }
        } while (!terminate);
    }

    public String getResponse(String input) {

        try {
            Command c = Parser.parseCommand(input);
            ui.setUserInput(input);
            return c.execute(taskList, ui, storage);
        } catch (DukeException de) {
            return de.getMessage();
        }
    }

    public boolean isEnd(String input) {
        try {
            Command c = Parser.parseCommand(input);
            return c.isEnd();
        } catch (DukeException de) {
            System.out.println(de.getMessage());
            return false;
        }
    }


}
