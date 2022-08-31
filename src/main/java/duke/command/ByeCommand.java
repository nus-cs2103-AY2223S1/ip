package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command for exiting the chatbot.
 */
public class ByeCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    /**
     * Save the current list of tasks into a text file for future reference and
     * display a goodbye message.
     * @param tasks The TaskList object of the chatbot.
     * @param storage The storage object of the chatbot.
     * @return The goodbye message.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        storage.saveTaskToFile(tasks.getListOfTasks());
        return "Bye. Hope to see you again soon!";
    }

}
