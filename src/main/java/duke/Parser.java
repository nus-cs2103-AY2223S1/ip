package duke;

import duke.modules.Todos;
import duke.modules.todos.Deadline;
import duke.modules.todos.Event;
import duke.modules.todos.Todo;

import java.util.Scanner;

import static duke.Ui.say;
import static duke.Ui.sayAsError;
import static duke.Ui.sayError;

/**
 * Command parser cum evaluator.
 */
public class Parser {

    /**
     * Represents the result of an execution.
     */
    static class ExecuteResult {
        /** Whether the program should exit after this command */
        private final boolean shouldExitAfter;

        /**
         * Constructor
         * @param shouldExitAfter Whether the program should exit after this command
         */
        public ExecuteResult(boolean shouldExitAfter) {
            this.shouldExitAfter = shouldExitAfter;
        }

        public boolean shouldExitAfter() {
            return shouldExitAfter;
        }
    }

    /**
     * Parses and executes the given input using the given module instances.
     * @param line The input given to the bot.
     * @param todos The Todos module instance to use.
     * @return Whether the process should exit after this command.
     */
    public static ExecuteResult execute(String line, Todos todos) {
        try {
            Scanner scanner = new Scanner(line);
            String command = scanner.hasNext() ? scanner.next() : "";

            switch (command) {
            case "":
                sayAsError("Sorry, I didn't catch that?");
                break;
            case "bye":
                say("OK. See you next time! *boings away*");
                return new ExecuteResult(true);
            case "todo":
                todos.cmdAdd(scanner, Todo::fromChat);
                break;
            case "deadline":
                todos.cmdAdd(scanner, Deadline::fromChat);
                break;
            case "event":
                todos.cmdAdd(scanner, Event::fromChat);
                break;
            case "list":
                todos.cmdList();
                break;
            case "mark":
                todos.cmdMark(scanner);
                break;
            case "unmark":
                todos.cmdUnmark(scanner);
                break;
            case "delete":
                todos.cmdDelete(scanner);
                break;
            case "find":
                todos.cmdFind(scanner);
                break;
            default:
                sayAsError("Sorry, I didn't understand what you said :(");
                break;
            }
        } catch (MessagefulException e) {
            sayError(e);
        }
        return new ExecuteResult(false);
    }
}
