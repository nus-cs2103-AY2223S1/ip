package alpha;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import alpha.command.Add;
import alpha.command.Command;
import alpha.command.Delete;
import alpha.command.Exit;
import alpha.command.Find;
import alpha.command.Help;
import alpha.command.List;
import alpha.command.Mark;
import alpha.command.Unmark;
import alpha.task.Deadline;
import alpha.task.Event;
import alpha.task.Todo;

/** Interprets user message */
public class Parser {

    /**
     * Interprets the message entered by the user.
     *
     * @param input The message to be interpreted.
     * @return Command to be executed.
     * @throws AlphaException If the input is invalid.
     */
    public Command interpretMessage(String input) throws AlphaException {
        String[] inputTokens = input.split(" ", 2);
        switch (inputTokens[0].toLowerCase()) {
        case "todo": {
            checkInvalidInput(input, 4);
            return new Add(new Todo(input.substring(5), "T"));
        }
        case "event": {
            checkInvalidInput(input, 5);
            String[] taskInfo = inputTokens[1].split(" /on ", 2);
            if (taskInfo.length <= 1) {
                throw new AlphaException("Invalid input: Incorrect format! "
                        + "(enter help to know more)");
            }
            String formattedDate = checkDateFormat(taskInfo[1]);
            return new Add(new Event(taskInfo[0], formattedDate, "E"));

        }
        case "deadline": {
            checkInvalidInput(input, 8);
            String[] taskInfo = inputTokens[1].split(" /by ", 2);
            if (taskInfo.length <= 1) {
                throw new AlphaException("Invalid input: Incorrect format! "
                        + "(enter help to know more)");
            }
            String formattedDate = checkDateFormat(taskInfo[1]);
            return new Add(new Deadline(taskInfo[0], formattedDate, "D"));
        }
        case "mark": {
            checkInvalidInput(input, 4);
            return new Mark(Integer.parseInt(inputTokens[1]));
        }
        case "unmark": {
            checkInvalidInput(input, 6);
            return new Unmark(Integer.parseInt(inputTokens[1]));
        }
        case "list": {
            return new List();
        }
        case "delete": {
            checkInvalidInput(input, 6);
            return new Delete(Integer.parseInt(inputTokens[1]));
        }
        case "help": {
            return new Help();
        }
        case "find": {
            checkInvalidInput(input, 4);
            return new Find(inputTokens[1]);
        }
        case "bye": {
            return new Exit();
        }
        default: {
            throw new AlphaException("Invalid input: Task type unknown!");
        }
        }
    }

    /**
     * Checks the validity of the input message.
     *
     * @param input Input message whose validity needs to be checked.
     * @param commandLength The required length of the input message.
     * @throws AlphaException If the task description is missing.
     */
    public void checkInvalidInput(String input, int commandLength) throws AlphaException {
        if (input.length() == commandLength) {
            throw new AlphaException("Invalid input: Task description is missing!");
        }
    }

    /**
     * Checks the validity of the input message.
     *
     * @param date Input date whose validity needs to be checked.
     * @throws AlphaException If the date is not entered in the required format.
     */
    public String checkDateFormat(String date) throws AlphaException {
        DateTimeFormatter dTF = DateTimeFormatter.ofPattern("MMM d yyyy");
        String formattedDeadline;
        try {
            LocalDate d = LocalDate.parse(date);
            formattedDeadline = d.format(dTF);
            return formattedDeadline;
        } catch (DateTimeException d) {
            throw new AlphaException("Invalid input: Input date must be an actual date in YYYY-MM-DD format!");
        }
    }
}
