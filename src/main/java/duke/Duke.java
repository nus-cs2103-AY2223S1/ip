package duke;
/**
 * This is Duke, he replies to messages.
 * 
 * @author Pei Cheng Yi A0229823Y
 */

// Reading command line inputs
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import duke.commands.AbstractCommand;
import duke.commands.Exit;
import duke.commands.Greet;
import duke.enums.*;
import duke.exceptions.*;
import duke.lists.*;
import duke.utils.*;

public class Duke {
    /* Store tasks from user */
    private static TaskList tasks = new TaskList();
    private static CommandFactory cf = new CommandFactory(tasks);

    public static void main(String[] args) throws DukeException, IOException {
        // Setting up to read command line inputs
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        // Greet the user
        AbstractCommand cmd = new Greet();
        cmd.execute();
        // Read inputs until the exit command is entered
        String input = reader.readLine();
        while (!input.equals(Commands.EXIT.toString())) {
            try {
                cmd = cf.handleInput(input);
                cmd.execute();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            input = reader.readLine();
        }
        // Exit the bot
        cmd = new Exit();
        cmd.execute();
    }
}
