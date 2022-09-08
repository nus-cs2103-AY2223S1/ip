package duke;

import duke.command.*;
import duke.exceptions.*;

public class Parser {
    public static Command parse(String input) throws DukeException {
        if (input.isBlank()) {
            throw new BlankCommandException();
        }
        String[] inputArr = input.split(" ", 2);
        // inputArr: ["keyword", "description"]
        String keyword = inputArr[0];
        if (inputArr.length < 2) {
            return parseSimpleCommand(keyword);
        }
        if (inputArr[1].isBlank()) {
            throw new BlankDescriptionException();
        }
        String description = inputArr[1];
        return parseComplexCommand(keyword, description);
    }

    private static Command parseSimpleCommand(String keyword) throws CommandNotFoundException {
        switch (keyword) {
            case ("bye"):
                return new ExitCommand();

            case ("list"):
                return new ListCommand();
            default:
                throw new CommandNotFoundException(keyword);
        }
    }

    private static Command parseComplexCommand(String keyword, String description) throws DukeException {
        switch (keyword) {
            case ("find"):
                return new FindCommand(description);
            case ("todo"):
                ToDo toDo = new ToDo(description);
                return new AddCommand(toDo);
            case ("deadline"):
                // description: "(deadlineTask) /by (by)
                String[] deadlineArr = description.split(" /by", 2);
                // deadlineArr: ["deadlineTask", " by"]
                if (deadlineArr.length == 1) {
                    throw new ImproperDeadlineFormatException();
                }
                String deadlineTask = deadlineArr[0];
                String by = deadlineArr[1];
                if (by.isBlank()) {
                    throw new NoDateException();
                }
                Deadline deadline = new Deadline(deadlineTask, by);
                return new AddCommand(deadline);
            case ("event"):
                // description: "(EventTask) /at (at)"
                String[] eventArr = description.split(" /at", 2);
                // eventArr: ["eventTask", "at"]
                if (eventArr.length == 1) {
                    throw new ImproperEventFormatException();
                }
                String evenTask = eventArr[0];
                String at = eventArr[1];
                if (at.isBlank()) {
                    throw new NoDateException();
                }
                Event event = new Event(evenTask, at);
                return new AddCommand(event);
            case ("mark"):
                try {
                    int x = Integer.valueOf(description);
                    return new MarkStatusCommand(x);
                } catch (NumberFormatException e) {
                    throw new InvalidNumberException();
                }
            case ("unmark"):
                try {
                    int x = Integer.valueOf(description);
                    return new UnmarkStatusCommand(x);
                } catch (NumberFormatException e) {
                    throw new InvalidNumberException();
                }
            case ("delete"):
                try {
                    int x = Integer.valueOf(description);
                    return new DeleteCommand(x);
                } catch (NumberFormatException e) {
                    throw new InvalidNumberException();
                }
            default:
                throw new CommandNotFoundException(description);
        }
    }
}