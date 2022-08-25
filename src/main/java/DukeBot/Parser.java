package DukeBot;

import DukeBot.command.*;
import DukeBot.command.FindCommand;

public class Parser {

    private TaskList tasks;

    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    public Command parse(String str) throws DukeException {
        String[] commands = str.split(" ");
        String action = commands[0];
        switch (action) {
        case "list":
            return new ListCommand(str, tasks);
        case "mark":
        case "unmark":
            return new MarkCommand(str, tasks);
        case "delete":
            return new DeleteCommand(str, tasks);
        case "todo":
        case "deadline":
        case "event":
            return new NewTaskCommand(str, tasks);
        case "bye":
            return new ExitCommand(str, tasks);
        case "find":
            return new FindCommand(str, tasks);
        default:
            throw new DukeException("Not sure what you mean.");
        }
    }

}
