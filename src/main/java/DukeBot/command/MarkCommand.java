package DukeBot.command;

import DukeBot.DukeException;
import DukeBot.Task;
import DukeBot.TaskList;
import DukeBot.Ui;

public class MarkCommand extends Command {

    private String command;

    private TaskList tasks;

    public MarkCommand(String command, TaskList tasks) {
        this.command = command;
        this.tasks = tasks;
    }

    @Override
    public String execute() throws DukeException {
        String[] s = command.split(" ");
        if (s.length != 2) {
            throw new DukeException("list command in this format: list <index>");
        }
        int indexOfTask;
        StringBuilder toReturn = new StringBuilder();
        try {
            indexOfTask = Integer.parseInt(s[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("list command in this format: list <index>");
        }
        switch (s[0]) {
        case "mark":
            tasks.get(indexOfTask - 1).markComplete();
            toReturn.append("Nice! I've marked this task as done:\n");
            toReturn.append(String.format("      %s", tasks.get(indexOfTask - 1)));
            return toReturn.toString();
        case "unmark":
            tasks.get(indexOfTask - 1).markIncomplete();
            toReturn.append("    OK, I've marked this task as not done yet");
            toReturn.append(String.format("      %s", tasks.get(indexOfTask - 1)));
            return toReturn.toString();
        default:
            throw new DukeException("Not sure what that means.");
        }
    }
}
