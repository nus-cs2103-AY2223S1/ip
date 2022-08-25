package DukeBot;

import DukeBot.command.*;

/**
 * Encapsulates the class that parses the commands from user.
 */
public class Parser {

    private TaskList tasks;

    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Parses the command from the user.
     *
     * @param str The command given by the user.
     * @return Command instance corresponding to the user's command.
     * @throws DukeException
     */
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
        default:
            throw new DukeException("Not sure what you mean.");
        }
    }

}
