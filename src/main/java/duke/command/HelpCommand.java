package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * A class that encapsulates the help command.
 */
public class HelpCommand extends Command {
    /**
     * Handles the execution behaviour of the associated command.
     *
     * @param tasks   The current list of tasks.
     * @param storage The storage of data.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        String help = "help - shows all commands\n";
        String list = "list - lists tasks\n";
        String mark = "mark <index> - mark task as complete\n";
        String unmark = "unmark <index> - mark task as incomplete\n";
        String todo = "todo <description> - creates a todo task\n";
        String event = "event <description> /at <date> - creates an event task\n";
        String deadline = "deadline <description> /by <date> - creates a deadline task\n";
        String find = "find <query> - finds task with query\n";
        String delete = "delete <index> - deletes a task\n";
        String bye = "bye - exits the program\n";
        return "List of commands:\n" + help + list + mark + unmark + todo
                + event + deadline + find + delete + bye;
    }

    /**
     * Returns the command type.
     *
     * @return The command type.
     */
    @Override
    public String getCommand() {
        return "help";
    }

    /**
     * Returns the string representation of the command.
     *
     * @return The string representation of the command.
     */
    @Override
    public String toString() {
        return "help";
    }
}
