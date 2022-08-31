package alpha;

import alpha.command.*;
import alpha.task.Deadline;
import alpha.task.Event;
import alpha.task.Todo;

import java.io.IOException;

public class Parser {
    public Command interpretMessage(String input) throws AlphaException, IOException {
        String[] inputTokens = input.split(" ", 2);
        switch (inputTokens[0]) {
            case "todo": {
                checkInvalidInput(input, 4);
                return new Add(new Todo(input.substring(5), "T"));
            }
            case "event": {
                checkInvalidInput(input, 5);
                String[] taskInfo = inputTokens[1].split("/on ", 2);
                return new Add(new Event(taskInfo[0], taskInfo[1], "E"));
            }
            case "deadline": {
                checkInvalidInput(input, 8);
                String[] taskInfo = inputTokens[1].split("/by ", 2);
                return new Add(new Deadline(taskInfo[0], taskInfo[1], "D"));
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
            case "bye": {
                return new Exit();
            }
            default: {
                AlphaException e = new AlphaException("Invalid input: alpha.task.Task type unknown!");
                throw e;
            }
        }
    }

    public void checkInvalidInput(String input, int commandLength) throws AlphaException {
        if (input.length() == commandLength) {
            throw new AlphaException("Invalid input: alpha.task.Task description is missing!");
        }
    }
}
