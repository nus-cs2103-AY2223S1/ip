package duke;

import duke.modules.Todos;
import duke.modules.todos.Deadline;
import duke.modules.todos.Event;
import duke.modules.todos.Todo;

import java.util.Scanner;

import static duke.Ui.say;
import static duke.Ui.sayAsError;
import static duke.Ui.sayError;

public class Parser {
    public static boolean execute(String line, Todos todos) {
        try {
            Scanner scanner = new Scanner(line);
            String command = scanner.hasNext() ? scanner.next() : "";

            switch (command) {
            case "":
                sayAsError("Sorry, I didn't catch that?");
                break;
            case "bye":
                say("OK. See you next time! *boings away*");
                return true;
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
            default:
                sayAsError("Sorry, I didn't understand what you said :(");
                break;
            }
        } catch (MessagefulException e){
            sayError(e);
        }
        return false;
    }
}
