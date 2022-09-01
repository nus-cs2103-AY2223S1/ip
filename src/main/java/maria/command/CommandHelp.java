package maria.command;

import maria.TaskManager;

public class CommandHelp extends Command {

    /**
     * Executes the command.
     * @param taskManager The overall-in-charge for all task related affairs
     * @return The program will exit before returning anything
     */
    @Override
    public String execute(TaskManager taskManager) {
        
        return "List of commands:\n"
                + "Type 'todo <name> <done (true/false)>' to create a todo.\n"
                + "Type 'deadline <name> <done (true/false)> <deadline (YYYY-MM-DD)>' to create a task with deadline.\n"
                + "Type 'event <name> <done (true/false)> <start (YYYY-MM-DD)> <end (YYYY-MM-DD)>'\n"
                + "to create an event with a start and end time.\n"
                + "Type 'list' to list out all your tasks.\n"
                + "Type 'mark <index>' to complete a task.\n"
                + "Type 'unmark <index>' to un-complete a task.\n"
                + "Type 'delete <index>' to remove a task.\n"
                + "Type 'find <search_string>' to find a task.\n"
                + "Type 'bye' to finish the conversation.";

    }

}
