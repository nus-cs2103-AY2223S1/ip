package DukeBot;

import DukeBot.command.Command;
import DukeBot.command.DeleteCommand;
import DukeBot.command.ExitCommand;
import DukeBot.command.ListCommand;
import DukeBot.command.MarkCommand;
import DukeBot.command.NewTaskCommand;

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
            // Fallthrough
        case "unmark":
            return new MarkCommand(str, tasks);
        case "delete":
            return new DeleteCommand(str, tasks);
        case "todo":
            // Fallthrough
        case "deadline":
            // Fallthrough
        case "event":
            return new NewTaskCommand(str, tasks);
        case "bye":
            return new ExitCommand(str, tasks);
        default:
            throw new DukeException("Not sure what you mean.");
        }
    }

}
