package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnknownCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.exception.InvalidDateException;
import duke.exception.InvalidInputException;
import duke.exception.MissingInputException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;


/**
 * Parser class to parse user input and read files.
 */
public class Parser {

    /**
     * Constructor for Parser object in program.
     *
     */
    public Parser() {

    }


    /**
     * Parse input to give corresponding command.
     *
     * @param input The exact user input to be parsed
     * @return The mapped Command if all input is valid.
     * @throws DukeException If input formats not met or missing/invalid input.
     */
    public Command parse(String input) throws DukeException {

        String[] splitInput = input.split(" ");
        String command = splitInput[0];
        String[] arguments = Arrays.copyOfRange(splitInput, 1, splitInput.length);

        if (command.equals("mark")) {
            checkMarkInputs(splitInput);
            return new MarkCommand(arguments[0]);
        } else if (command.equals("unmark")) {
            checkUnmarkInputs(splitInput);
            return new UnmarkCommand(arguments[0]);
        } else if (command.equals("deadline")) {
            int byIndex = checkDeadlineInput(splitInput);
            Task task = getDeadline(splitInput, byIndex);
            return new AddCommand(task);
        } else if (command.equals("todo")) {
            checkTodoInputs(splitInput);
            Task task = new Todo(String.join(" ", arguments));
            return new AddCommand(task);
        } else if (command.equals("event")) {
            int atIndex = checkEventInput(splitInput);
            Task task = getEvent(splitInput, atIndex);
            return new AddCommand(task);
        } else if (command.equals("delete")) {
            checkDeleteInputs(splitInput);
            return new DeleteCommand(arguments[0]);
        } else if (command.equals("bye") && splitInput.length == 1) {
            return new ExitCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (command.equals("find")) {
            if (splitInput.length == 1) {
                throw new MissingInputException("keyword", "find");
            }
            return new FindCommand(String.join(" ", arguments));
        } else {
            return new UnknownCommand();
        }
    }

    /**
     * Parse the lines of tasks and populate temporary TaskList.
     * Parse line by line, create new Task object for each line.
     *
     * @param input The line being read from the file.
     *
     */
    public Task fileLoadParser(String input) {
        String[] splitInput = input.split("\\|");
        String type = splitInput[0];
        String[] arguments = Arrays.copyOfRange(splitInput, 2, splitInput.length);
        Task t;
        if (type.equals("T")) {
            t = new Todo(splitInput[2]);
        } else if (type.equals("D")) {
            String desc = arguments[0];
            String tempDate = arguments[1];
            LocalDateTime date = stringToDate(tempDate);
            t = new Deadline(desc, date);
        } else {
            assert type.equals("E") : "Should only be Event type of Task left";
            t = new Event(arguments);
        }
        if (splitInput[1].equals("1")) {
            t.complete();
        }
        return t;
    }

    private void checkMarkInputs(String[] command) throws DukeException {
        if (command.length == 1) {
            throw new MissingInputException("index", "mark");
        }
        if (command.length > 2) {
            throw new InvalidInputException(String.join(" ",
                    Arrays.copyOfRange(command, 1, command.length)),
                    "mark");
        }
    }

    private void checkUnmarkInputs(String[] command) throws DukeException {
        if (command.length == 1) {
            throw new MissingInputException("index", "unmark");
        }
        if (command.length > 2) {
            throw new InvalidInputException(String.join(" ",
                    Arrays.copyOfRange(command, 1, command.length)),
                    "unmark");
        }
    }

    private void checkTodoInputs(String[] arr) throws DukeException {
        if (arr.length < 2) {
            throw new MissingInputException("description", "todo");
        }
    }

    private int checkDeadlineInput(String[] arr) throws DukeException {
        int i = 1;
        while (i < arr.length && !arr[i].equals("/by")) {
            i++;
        }
        if (i == 1) {
            throw new MissingInputException("description", arr[0]);
        } else if (arr.length - 1 == i) {
            throw new MissingInputException("date", arr[0]);
        }
        return i;
    }

    private void checkDeleteInputs(String[] command) throws DukeException {
        if (command.length == 1) {
            throw new MissingInputException("index", "delete");
        }
        if (command.length > 2) {
            throw new InvalidInputException(String.join(" ",
                    Arrays.copyOfRange(command, 1, command.length)),
                    "delete");
        }
    }

    private int checkEventInput(String[] arr) throws DukeException {
        int i = 1;
        while (i < arr.length && !arr[i].equals("/at")) {
            i++;
        }
        if (i == 1) {
            throw new MissingInputException("description", arr[0]);
        } else if (arr.length - 1 == i) {
            throw new MissingInputException("date", arr[0]);
        }
        return i;
    }

    private Task getDeadline(String[] splitInput, int byIndex) throws DukeException {
        try {
            String description = String.join(" ", Arrays.copyOfRange(splitInput, 1, byIndex));

            String tempDate = String.join(" ",
                    Arrays.copyOfRange(splitInput,
                    byIndex + 1, splitInput.length));
            LocalDateTime date = stringToDate(tempDate);
            return new Deadline(description, date);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }

    private Task getEvent(String[] splitInput, int atIndex) {
        String description = String.join(" ", Arrays.copyOfRange(splitInput, 1, atIndex));
        String dueDate = String.join(" ", Arrays.copyOfRange(splitInput, atIndex + 1, splitInput.length));
        String[] packagedArguments = {description, dueDate};
        return new Event(packagedArguments);
    }

    private LocalDateTime stringToDate(String string) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return LocalDateTime.parse(string, format);
    }


}
