package alpha;

import alpha.command.*;
import alpha.task.Deadline;
import alpha.task.Event;
import alpha.task.Todo;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    public Command interpretMessage(String input) throws AlphaException {
        String[] inputTokens = input.split(" ", 2);
        switch (inputTokens[0]) {
            case "todo": {
                checkInvalidInput(input, 4);
                return new Add(new Todo(input.substring(5), "T"));
            }
            case "event": {
                checkInvalidInput(input, 5);
                String[] taskInfo = inputTokens[1].split(" /on ", 2);;
                if (taskInfo.length <= 1) {
                    throw new AlphaException("Invalid input: Incorrect format! Enter help to learn about the command formats.");
                }
                String formattedDate = checkDateFormat(taskInfo[1]);
                return new Add(new Event(taskInfo[0], formattedDate, "E"));

            }
            case "deadline": {
                checkInvalidInput(input, 8);
                String[] taskInfo = inputTokens[1].split(" /by ", 2);
                if (taskInfo.length <= 1) {
                    throw new AlphaException("Invalid input: Incorrect format! Enter help to learn about the command formats.");
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
            case "bye": {
                return new Exit();
            }
            default: {
                AlphaException e = new AlphaException("Invalid input: Task type unknown!");
                throw e;
            }
        }
    }

    public void checkInvalidInput(String input, int commandLength) throws AlphaException {
        if (input.length() == commandLength) {
            throw new AlphaException("Invalid input: Task description is missing!");
        }
    }

    public String checkDateFormat(String date) throws AlphaException {
        DateTimeFormatter dTF = DateTimeFormatter.ofPattern("MMM d yyyy");
        String formattedDeadline;
        try {
            LocalDate d = LocalDate.parse(date);
            formattedDeadline = d.format(dTF);
            return formattedDeadline;
        } catch (DateTimeException d) {
            throw new AlphaException("Invalid date: Input date must be an actual date in YYYY-MM-DD format!");
        }
    }
}
