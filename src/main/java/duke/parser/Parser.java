package duke.parser;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import duke.exception.DukeException;
import duke.exception.EmptyCommandException;

import duke.command.Command;
import duke.command.CommandPair;

public class Parser {
    private boolean isListening;

    public Parser() {
        this.isListening = true;
    }

    public CommandPair parseText(String text) throws DukeException {
        List<String> commands = Arrays.stream(text.trim().split(" ", 2))
                .map(String::trim).collect(Collectors.toList());
        String mainCommand = commands.get(0);
        String description = commands.size() > 1 ? commands.get(1) : "";

        if (mainCommand.equals("bye")) {
            this.isListening = false;
            return new CommandPair(Command.BYE);
        } else if (mainCommand.equals("list")) {
            return new CommandPair(Command.LIST);
        } else if (mainCommand.equals("mark")) {
            return new CommandPair(Command.MARK, description);
        } else if (mainCommand.equals("unmark")) {
            return new CommandPair(Command.UNMARK, description);
        } else if (mainCommand.equals("delete")) {
            return new CommandPair(Command.DELETE, description);
        } else if (mainCommand.equals("todo")) {
            return new CommandPair(Command.TODO, description);
        } else if (mainCommand.equals("deadline")) {
            return new CommandPair(Command.DEADLINE, description);
        } else if (mainCommand.equals("event")) {
            return new CommandPair(Command.EVENT, description);
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
