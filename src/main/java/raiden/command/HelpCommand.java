package raiden.command;

import raiden.RaidenException;

/**
 * Represents the command to show all the available commands.
 */
public class HelpCommand extends Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute() throws RaidenException {
        StringBuilder sb = new StringBuilder();
        sb.append("Here is a list of commands Raiden knows:\n");
        sb.append("1. list\n- View all the tasks in your list.\n");
        sb.append("2. find <keyword>\n"
                + "- Search for tasks in your list that contain the keyword or phrase provided.\n");
        sb.append("3. todo <description>\n"
                + "- Add a ToDo task with the given description.\n");
        sb.append("4. deadline <description> /by <date/time>\n"
                + "- Add a Deadline task with the given description and date/time.\n");
        sb.append("- <date/time> is written in DD-MM-YYYY HH:mm format, where the time (HH:mm) is optional.\n");
        sb.append("5. event <description> /at <date/time>\n"
                + "- Add an Event task with the given description and date/time.\n");
        sb.append("- <date/time> is written in DD-MM-YYYY HH:mm format, where the time (HH:mm) is optional.\n");
        sb.append("6. mark <task_id>\n- Mark the task with the given id in your list as done.\n");
        sb.append("7. unmark <task_id>\n- Mark the task with the given id in your list as not done.\n");
        sb.append("8. delete <task_id>\n- Remove the task with the given id from your list.\n");
        sb.append("9. bye\n- Exit Raiden bot.\n");
        sb.append("10. editD <task_id> <new_description>\n- Edit the description of the task with the given id in "
                + "your list\n");
        sb.append("11. editT <task_id> <new_date/time>\n- Edit the date/time of the task with the given id in "
                + "your list");
        return sb.toString();
    }

    /**
     * Checks if the command given refers to help.
     *
     * @param s The command word (if any) from the user's input.
     * @return true if the command for help is valid, false otherwise.
     */
    public static boolean isCommand(String s) {
        return s.equals("help");
    }
}
