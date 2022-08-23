package jduke.parser;

import jduke.commands.*;

public class Parser {
    public Command parseCommand(String input) {
        String mainCommand = input.split(" ", 2)[0].toLowerCase();
        String params = parseParams(input);

        switch (mainCommand) {
        case TodoCommand.COMMAND_WORD:
            return prepareTodo(params);
        case DeadlineCommand.COMMAND_WORD:
            return prepareDeadline(params);
        case EventCommand.COMMAND_WORD:
            return prepareEvent(params);
        case ListCommand.COMMAND_WORD:
            return prepareList(params);
        case MarkCommand.COMMAND_WORD:
            return prepareMark(params);
        case UnmarkCommand.COMMAND_WORD:
            return prepareUnmark(params);
        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(params);
        case FindCommand.COMMAND_WORD:
            return prepareFind(params);
        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();
        default:
            return new IncorrectCommand();
        }
    }
    private String parseParams(String input) {
        if (input.split(" ", 2).length == 1) {
            return "";
        }
        return input.split(" ", 2)[1];
    }

    private Command prepareTodo(String params) {
        if (!params.toLowerCase().matches("[^ ](.*)")) {
            return new IncorrectCommand(String.format("|  invalid TODO format%n|    %s%n", TodoCommand.FORMAT));
        }
        return new TodoCommand(params);
    }

    private Command prepareDeadline(String params) {
        if (!(params.toLowerCase().matches(
                "[^ ](.*) /by [0-9]{1,2}/[0-9]{1,2}/[0-9]{4} [0-9]{4}")
                || params.toLowerCase().matches("[^ ](.*) /by [0-9]{1,2}/[0-9]{1,2}/[0-9]{4}"))) {
            return new IncorrectCommand(
                    String.format("|  invalid DEADLINE format%n|    %s%n", DeadlineCommand.FORMAT));
        }
        return new DeadlineCommand(params);
    }

    private Command prepareEvent(String params) {
        if (!(params.toLowerCase().matches(
                "[^ ](.*) /at [0-9]{1,2}/[0-9]{1,2}/[0-9]{4} [0-9]{4}")
                || params.toLowerCase().matches("[^ ](.*) /at [0-9]{1,2}/[0-9]{1,2}/[0-9]{4}"))) {
            return new IncorrectCommand(String.format("|  invalid EVENT format%n|    %s%n", EventCommand.FORMAT));
        }
        return new EventCommand(params);
    }

    private Command prepareList(String params) {
        if (!(params.matches("[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}") || params.matches(""))) {
            return new IncorrectCommand(String.format("|  invalid LIST format%n|    %s%n", ListCommand.FORMAT));
        }
        return new ListCommand(params);
    }

    private Command prepareMark(String params) {
        if (!params.matches("([0-9]+)")) {
            return new IncorrectCommand(String.format("|  invalid MARK format%n|    %s%n", MarkCommand.FORMAT));
        }
        return new MarkCommand(params);
    }

    private Command prepareUnmark(String params) {
        if (!params.matches("([0-9]+)")) {
            return new IncorrectCommand(String.format("|  invalid UNMARK format%n|    %s%n", UnmarkCommand.FORMAT));
        }
        return new UnmarkCommand(params);
    }

    private Command prepareDelete(String params) {
        if (!params.matches("([0-9]+)")) {
            return new IncorrectCommand(String.format("|  invalid DELETE format%n|    %s%n", DeleteCommand.FORMAT));
        }
        return new DeleteCommand(params);
    }

    private Command prepareFind(String params) {
        if (!params.matches("[^ ](.*)")) {
            return new IncorrectCommand(String.format("|  invalid FIND format%n|    %s%n", FindCommand.FORMAT));
        }
        return new FindCommand(params);
    }
}
