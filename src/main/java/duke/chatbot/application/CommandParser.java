package duke.chatbot.application;

import duke.chatbot.command.*;
import duke.chatbot.data.exception.InvalidInputException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommandParser {
    public static Command parseCommand(String line) throws InvalidInputException {
        if (line == null) {
            throw new InvalidInputException();
        } else if (line.equals("bye")) {
            return new ExitCommand();
        } else if (line.equals("list")) {
            return new ListCommand();
        } else {
            Scanner sc = new Scanner(line);
            List<String> arguments = new ArrayList<>();

            String caseString = sc.next();
            if ((caseString.equals("mark") || caseString.equals("unmark") || caseString.equals("delete"))
                    && sc.hasNextInt()) {
                arguments.add(sc.next());
                if (!sc.hasNext()) {
                    if (caseString.equals("mark")) {
                        return new MarkCommand(arguments);
                    } else if (caseString.equals("unmark")) {
                        return new UnmarkCommand(arguments);
                    } else {
                        return new DeleteCommand(arguments);
                    }
                }
            } else if (caseString.equals("todo") && sc.hasNext()) {
                arguments.add(sc.nextLine());
                return new AddToDoCommand(arguments);
            } else if (caseString.equals("deadline") && sc.hasNext()) {
                sc.useDelimiter(" /by ");
                arguments.add(sc.next());
                if (sc.hasNext()) {
                    arguments.add(sc.next());
                    if (!sc.hasNext()) {
                        return new AddDeadlineCommand(arguments);
                    }
                }
            } else if (caseString.equals("event") && sc.hasNext()) {
                sc.useDelimiter(" /at ");
                arguments.add(sc.next());
                if (sc.hasNext()) {
                    arguments.add(sc.next());
                    if (!sc.hasNext()) {
                        return new AddEventCommand(arguments);
                    }
                }
            } else if (caseString.equals("check") && sc.hasNext()) {
                arguments.add(sc.nextLine());
                return new CheckDateCommand(arguments);
            }
            throw new InvalidInputException();
        }
    }
}
