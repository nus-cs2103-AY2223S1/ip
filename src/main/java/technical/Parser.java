package technical;

import java.io.IOException;

import javafx.util.Pair;
import ui.Ui;

/**
 * Parser class to process user inputs.
 * @author Nicholas Patrick
 */
public class Parser {
    /**
     * Splits a command line into arguments.
     *
     * @param line The command line String.
     * @return A String array of arguments.
     */
    public static String[] tokenise(String line) {
        return line.split(" ");
    }

    /**
     * Lists the list of commands.
     */
    public static String mismatch() {
        return Ui.reply("list of commands: list, mark, unmark, todo, deadline, event, delete, sort");
    }

    /**
     * Parses and executes a command.
     *
     * @param line The line of the command
     * @return Whether the program should continue running
     * @throws IOException in case of failure to write into save file.
     */
    public static Pair<String, Boolean> parseExecute(String line) throws IOException {
        String[] arguments = Parser.tokenise(line);
        if (arguments[0].equals("bye")) {
            return new Pair<>(Ui.bye(), false);
        }
        if (arguments[0].equals("list")) {
            return new Pair<>(TaskList.list(false), true);
        }
        if (arguments[0].equals("mark")) {
            return new Pair<>(TaskList.mark(arguments), true);
        }
        if (arguments[0].equals("unmark")) {
            return new Pair<>(TaskList.unmark(arguments), true);
        }
        if (arguments[0].equals("todo")) {
            return new Pair<>(TaskList.todo(arguments), true);
        }
        if (arguments[0].equals("deadline")) {
            return new Pair<>(TaskList.deadline(arguments), true);
        }
        if (arguments[0].equals("event")) {
            return new Pair<>(TaskList.event(arguments), true);
        }
        if (arguments[0].equals("delete")) {
            return new Pair<>(TaskList.delete(arguments), true);
        }
        if (arguments[0].equals("find")) {
            return new Pair<>(TaskList.find(arguments), true);
        }
        if (arguments[0].equals("sort")) {
            return new Pair<>(TaskList.sort(), true);
        }
        return new Pair<>(mismatch(), true);
    }
}
