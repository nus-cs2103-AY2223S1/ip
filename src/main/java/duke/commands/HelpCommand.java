package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;

public class HelpCommand extends Command {

    /**
     * Returns a String response from Duke after parsing and executing a bye command.
     * @param taskList List of tasks passed in to duke
     * @param storage Storage object to handle loading / saving from and to files
     * @return string response from Duke
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return "Duke is a command-line interface (CLI) task management application.\n"
                + "Here's a list of commands you can execute, and what they do!\n"
                + "==================================\n"
                + "1. list - Views all of your current tasks in a list format.\n"
                + "2. find [keyword] - Searches for all tasks that contain any keyword in given keywords.\n"
                + "3. todo [task description] - Creates a new todo task.\n"
                + "4. deadline [task description] /by [DD-MM-YYYY HH:MM] - Creates a new deadline task.\n"
                + "5. event [task description] /by [DD-MM-YYYY HH:MM] - Creates a new event.\n"
                + "6. mark [task number] - Marks a task as complete.\n"
                + "7. unmark [task number] - Marks a task as incomplete.\n"
                + "8. delete [task number] - Deletes a task.\n"
                + "9. reminders - Displays all uncompleted tasks that have a deadline within the next 3 days.\n"
                + "10. help - Displays Duke's help dialog.\n"
                + "11. bye - Exits Duke.\n"
                + "==================================";
    }
}
