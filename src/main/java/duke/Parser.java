package duke;

import duke.modules.Todos;
import duke.modules.todos.Deadline;
import duke.modules.todos.Event;
import duke.modules.todos.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static duke.Ui.say;
import static duke.Ui.sayAsError;
import static duke.Ui.sayError;

/**
 * Command parser cum evaluator.
 */
public class Parser {

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
            List<String> result = new ArrayList<>();

            switch (command) {
            case "":
                result.addAll(sayAsError("Sorry, I didn't catch that?"));
                break;
            case "bye":
                result.addAll(say("OK. See you next time! *boings away*"));
                return new ExecuteResult(true, result);
            case "todo":
                result.addAll(todos.cmdAdd(scanner, Todo::fromChat));
                break;
            case "deadline":
                result.addAll(todos.cmdAdd(scanner, Deadline::fromChat));
                break;
            case "event":
                result.addAll(todos.cmdAdd(scanner, Event::fromChat));
                break;
            case "list":
                result.addAll(todos.cmdList());
                break;
            case "mark":
                result.addAll(todos.cmdMark(scanner));
                break;
            case "unmark":
                result.addAll(todos.cmdUnmark(scanner));
                break;
            case "delete":
                result.addAll(todos.cmdDelete(scanner));
                break;
            case "find":
                result.addAll(todos.cmdFind(scanner));
                break;
            default:
                result.addAll(sayAsError("Sorry, I didn't understand what you said :("));
                break;
            }
            return new ExecuteResult(false, result);
        } catch (MessagefulException e) {
            return new ExecuteResult(false, sayError(e));
        }
    }
}
