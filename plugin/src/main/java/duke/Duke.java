/**
 * This is Duke, he replies to messages.
 *
 * @author Pei Cheng Yi A0229823Y
 */

package duke;

// Reading command line inputs
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import duke.commands.Command;
import duke.commands.ExitCommand;
import duke.commands.GreetCommand;
import duke.enums.Commands;
import duke.exceptions.DukeException;
import duke.lists.TaskList;
import duke.utils.Parser;
import duke.utils.Ui;

/**
 * The main logic of Duke
 */
public class Duke {
    /* Store tasks from user */
    private static TaskList tasks;
    private static Parser cf;
    private static Ui ui;

    /**
     * Initialises Duke
     * @param fname address to store tasks
     */
    public Duke(String fname) {
        try {
            tasks = new TaskList(fname);
            cf = new Parser(tasks);
            ui = new Ui();
        } catch (DukeException e) {
            ui.display(e.getMessage());
        }
    }

    /**
     * Executes Duke
     * @throws DukeException when there is an error
     * @throws IOException when there is an IO error
     */
    public void run() throws DukeException, IOException {
        // Setting up to read command line inputs
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        // Greet the user
        Command cmd = new GreetCommand();
        cmd.execute();
        // Read inputs until the exit command is entered
        String input = reader.readLine();
        while (!input.equals(Commands.EXIT.toString())) {
            try {
                cmd = cf.handleInput(input);
                cmd.execute();
            } catch (DukeException e) {
                ui.display(e.getMessage());
            }
            input = reader.readLine();
        }
        // Exit the bot
        cmd = new ExitCommand();
        cmd.execute();
    }

    public static void main(String[] args) throws DukeException, IOException {
        new Duke(".\\data\\duke.txt").run();
    }
}
