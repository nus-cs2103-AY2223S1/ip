package DukeBot.command;

import DukeBot.DukeException;
import DukeBot.Task;
import DukeBot.TaskList;

public class MarkCommand extends Command {

    private String command;

    private TaskList tasks;

    public MarkCommand(String command, TaskList tasks) {
        this.command = command;
        this.tasks = tasks;
    }

    @Override
    public void execute() throws DukeException {
        String[] s = command.split(" ");
        if (s.length != 2) {
            throw new DukeException("list command in this format: list <index>");
        }
        int indexOfTask;
        try {
            indexOfTask = Integer.parseInt(s[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("list command in this format: list <index>");
        }
        switch (s[0]) {
        case "mark":
            tasks.get(indexOfTask - 1).markComplete();
            System.out.println("    Nice! I've marked this task as done:");
            System.out.println(String.format("      %s", tasks.get(indexOfTask - 1)));
            break;
        case "unmark":
            tasks.get(indexOfTask - 1).markIncomplete();
            System.out.println("    OK, I've marked this task as not done yet");
            System.out.println(String.format("      %s", tasks.get(indexOfTask - 1)));
            break;
        default:
            throw new DukeException("Not sure what that means.");
        }
    }
}
