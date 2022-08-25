package duke.main;

import duke.command.*;

import java.time.DateTimeException;

public class Parser {

    public Command parse(String input) {
        String [] inputSplit = input.split(" ");
        String command = inputSplit[0];
        try {
            if (command.equals("list")) {
                return new ListCommand();
            } else {
                String task = inputSplit[1];

                if (command.equals("mark")) {
                    return new MarkCommand(task);
                } else if (command.equals("unmark")) {
                    return new UnmarkCommand(task);
                } else if (command.equals("todo")) {
                    return new AddTodoCommand(task);
                } else if (command.equals("deadline")) {
                    return new AddDeadlineCommand(task, inputSplit[3]);
                } else if (command.equals("event")) {
                    return new AddEventsCommand(task, inputSplit[3]);
                } else if (command.equals("delete")) {
                    return new DeleteCommand(inputSplit[1]);
                } else {
                    return new ErrorCommand();
                }
            }
        } catch (DateTimeException e) {
            System.out.println("Error!");
        }
        return new ErrorCommand();
    }
}
