package duke;

import duke.Enums.Action;
import duke.Exceptions.*;
import duke.Task.Deadline;
import duke.Task.Event;
import duke.Task.Todo;

public class Parser {
    public static Command parse(String fullCommand) throws InvalidCommandException {
        if (fullCommand.trim().equals("bye")) {
          return new ExitCommand();
        } else if (fullCommand.trim().equals("list")) {
           return new ListTasksCommand();
        } else {
            Action action = Action.parseCommand(fullCommand);

            String[] components = fullCommand.split(" ");
            String contents = fullCommand.substring(action.label.length()).trim();
            try {
                switch (action) {
                    case Mark:
                        return new MarkCommand(Integer.parseInt(contents)-1);
                    case Unmark:
                        return new UnmarkCommand(Integer.parseInt(contents)-1);
                    case Delete:
                        return new DeleteTaskCommand(Integer.parseInt(contents)-1);
                    case Todo:
                        if (contents.isBlank())
                            throw new EmptyTitleException();
                        else
                            return new CreateTodoCommand(new Todo(contents));
                    case Deadline:
                        String[] deadlineComponents = contents.split(" /by ");

                        if (deadlineComponents.length != 2)
                            throw new InvalidDeadlineException();
                        else if (deadlineComponents[0].isBlank())
                            throw new EmptyTitleException();
                        else
                            return new CreateDeadlineCommand(
                                    new Deadline(deadlineComponents[0].trim(),
                                            deadlineComponents[1].trim())
                            );

                    case Event:
                        String[] eventComponents = contents.split(" /at ");
                        if (eventComponents.length != 2)
                            throw new InvalidEventException();
                        else if (eventComponents[0].isBlank())
                            throw new EmptyTitleException();
                        else
                            return new CreateEventCommand(
                                    new Event(eventComponents[0].trim(),
                                            eventComponents[1].trim())
                            );
                }
            } catch (NumberFormatException e) {
                throw new InvalidTaskIndexException();
            }
        }
        throw new InvalidCommandException("Unknown command found");
    }
}
