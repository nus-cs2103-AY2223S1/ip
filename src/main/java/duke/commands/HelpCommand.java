package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

public class HelpCommand implements Command {
    private final String[] COMMAND_LIST = {"bye","list","todo","deadline","event","expense","find","mark","unmark","delete","help"};
    private String helpString;

    /**
     * Default constructor of the help command.
     * If all is provided, returns a list of available commands.
     *
     * @param helpString Command to provide help with.
     */
    public HelpCommand(String helpString) {
        this.helpString = helpString;
    }
    @Override
    public boolean isBye() {
        return false;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        String output;
        switch(helpString) {
        case("all"):
            output = "Here is a list of all available commands.\n" +
                    "Type help <command_name> to find out more.\n" +
            "------------------------------------------------------------";
            for (String command : COMMAND_LIST) {
                output += command + " ";
            }
            return output;
        case("bye"):
            output = "Exits the programme in 3 seconds\n";
            output += "Format: bye";
            return output;
        case("list"):
            output = "Displays all tasks\n";
            output += "Format: list";
            return output;
        case("todo"):
            output = "Adds a new todo task\n";
            output += "Format: todo <name>";
            return output;
        case("deadline"):
            output = "Adds a new deadline\n";
            output += "Format: deadline <name> /by <yyyy-MM-dd HHmm>";
            return output;
        case("event"):
            output = "Adds a new event\n";
            output += "Format: event <name> /at <location>";
            return output;
        case("expense"):
            output = "Adds a new expense\n";
            output += "Format: expense <name> /amt <INTEGER>";
            return output;
        case("find"):
            output = "Find the tasks with the given substring\n";
            output += "Format: find <substring_to_search>";
            return output;
        case("mark"):
            output = "Mark the tasks at the specified indexes\n";
            output += "Format: mark <index_1> <index_2> ...";
            return output;
        case("unmark"):
            output = "Unmarks the tasks at the specified indexes\n";
            output += "Format: unmark <index_1> <index_2> ...";
            return output;
        case("delete"):
            output = "Deletes the tasks at the specified indexes\n";
            output += "Format: delete <index_1> <index_2> ...";
            return output;
        case("help"):
            output = "Displays the format of the specified command\n";
            output += "Format: help <OPTIONAL:command_name>";
            return output;
        default:
            throw new DukeException("That command you asked for help cannot be found!");
        }
    }
}
