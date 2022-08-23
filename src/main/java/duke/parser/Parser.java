package duke.parser;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import duke.command.AtCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.ToDoCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.exception.EmptyCommandException;

public class Parser {
    private boolean isListening;

    public Parser() {
        this.isListening = true;
    }

    public Command parseText(String text) throws DukeException {
        List<String> commands = Arrays.stream(text.trim().split(" ", 2))
                .map(String::trim).collect(Collectors.toList());
        String mainCommand = commands.get(0);
        String description = commands.size() > 1 ? commands.get(1) : "";

        if (mainCommand.equals("bye")) {
            this.isListening = false;
            return new ByeCommand();
        } else if (mainCommand.equals("list")) {
            return new ListCommand();
        } else if (mainCommand.equals("mark")) {
            return new MarkCommand(description);
        } else if (mainCommand.equals("unmark")) {
            return new UnmarkCommand(description);
        } else if (mainCommand.equals("delete")) {
            return new DeleteCommand(description);
        } else if (mainCommand.equals("todo")) {
            return new ToDoCommand(description);
        } else if (mainCommand.equals("deadline")) {
            return new DeadlineCommand(description);
        } else if (mainCommand.equals("event")) {
            return new EventCommand(description);
        } else if (mainCommand.equals("find")) {
            return new FindCommand(description);
        } else if (mainCommand.equals("at")) {
            return new AtCommand(description);
        } else if (mainCommand.isEmpty()) {
            throw new EmptyCommandException();
        } else {
            throw new DukeException();
        }
    }

    public boolean getIsListening() {
        return this.isListening;
    }
}
