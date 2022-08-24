package duke.parser;

import duke.command.*;
import duke.exception.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

public class Parser {

    public static Command parse(String input) throws DukeException {
        String[] inputArray = input.split(" ",2);
        String keyPhrase = inputArray[0];
        String argument = "";
        Command command;
        if (inputArray.length == 2) {
            argument = inputArray[1];
        }
        switch(keyPhrase) {
            case "bye":
                command = new ExitCommand();
                break;
            case "list":
                command = new ListCommand();
                break;
            case "mark":
                command = new MarkCommand(handleMark(argument));
                break;
            case "unmark":
                command = new UnMarkCommand(handleMark(argument));
                break;
            case "todo":
                ToDo todo = handleToDo(argument);
                command = new AddCommand(todo);
                break;
            case "deadline":
                Deadline deadline = handleDeadline(argument);
                command = new AddCommand(deadline);
                break;
            case "event":
                Event event = handleEvent(argument);
                command = new AddCommand(event);
                break;
            case "delete":
                command = new DeleteCommand(handleDelete(argument));
                break;
            default:
                throw new InvalidCommandException(keyPhrase);
        }
        return command;
    }

    public static ToDo handleToDo(String information) throws EmptyArgumentException {
        if (information.isEmpty()) {
            throw new EmptyArgumentException(Commands.ToDo);
        }
        ToDo todo = new ToDo(information);
        return todo;
    }

    public static Deadline handleDeadline(String information) throws DukeException {
        if (information.isEmpty()) {
            throw new EmptyArgumentException(Commands.Deadline);
        }
        if (!information.contains("/by")) {
            throw new InvalidArgumentException(Commands.Deadline);
        }
        String[] stringArr = information.split(" /by ",2);
        String[] dateTimeArr = stringArr[1].split(" ");
        Deadline deadline = null;
        try {
            deadline = new Deadline(stringArr[0], stringArr[1]);
        } catch (java.time.format.DateTimeParseException e) {
            throw new InvalidArgumentException(Commands.Date);
        }
        return deadline;
    }

    public static Event handleEvent(String information) throws DukeException {
        if (information.isEmpty()) {
            throw new EmptyArgumentException(Commands.Event);
        }
        if (!information.contains("/at")) {
            throw new InvalidArgumentException(Commands.Event);
        }
        String[] stringArr = information.split(" /at ",2);
        Event event = new Event(stringArr[0], stringArr[1]);
        return event;
    }

    public static int handleMark(String information) throws DukeException {
        if (information.isEmpty()) {
            throw new EmptyArgumentException(Commands.Mark);
        }
        if (!information.chars().allMatch( Character :: isDigit )) {
            throw new InvalidArgumentException(Commands.Mark);
        }
        int index = Integer.parseInt(information) - 1;
        if (index < 0) {
            throw new InvalidTaskNumberException();
        }
        return index;
    }

    public static int handleDelete(String information) throws DukeException {
        if (information.isEmpty()) {
            throw new EmptyArgumentException(Commands.Delete);
        }
        if (!information.chars().allMatch( Character :: isDigit )) {
            throw new InvalidArgumentException(Commands.Delete);
        }
        int index = Integer.parseInt(information) - 1;
        if (index < 0) {
            throw new InvalidTaskNumberException();
        }
        return index;
    }

}
