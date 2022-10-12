package duke;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.task.TaskList;

public class Duke {

    private static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String GREETING = "Hello! I'm Duke!\n";

    private boolean hasTasksEnd = false;
    private TaskList tasks;

    /**
     * Initialises Duke class with empty {@code TaskList}.
     */
    public Duke() {
        try {
            tasks = Storage.load();
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) throws DukeException {
        Command command = Parser.parseCommand(input);
        if (command instanceof ByeCommand) {
            hasTasksEnd = true;
        }
        return command.execute(tasks);
    }

    public String getWelcomeMessage() {
        return String.format("%s%sYou have %s. What can I do for you?", LOGO, GREETING, tasks.lengthString());
    }

    public boolean hasTasksEnd() {
        return hasTasksEnd;
    }
}
