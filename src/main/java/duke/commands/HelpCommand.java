package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

public class HelpCommand extends Command {

    private final String HELP_MESSAGE = "Given below are the commands that can be " +
            "executed using duke:\n"
            + "1. \"list\": It list all the tasks currently stored in duke.\n"
            + "2. \"todo <description>\": Adds a Todo task.\n"
            + "3. \"deadline <description> /by <dd/mm/YYYY HHmm>\":Adds a new Deadline task.\n"
            + "4. \"event <description> /at <dd/mm/YYYY HHmm>\":Adds a new Event task.\n"
            + "5. \"mark <task number>\": Marks the test as being done.\n"
            + "6. \"unmark <task number>\": Marks the test as being not done.\n"
            + "7. \"delete <task number>\": Deletes the given task.\n"
            + "8. \"find <keyword(s)>\": Finds tasks in the list matching the keyword(s).\n"
            + "9. \"help\": list all the commands in the application";

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.displayMessage(HELP_MESSAGE);
    }

}