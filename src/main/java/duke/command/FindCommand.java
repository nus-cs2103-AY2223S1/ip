/**
 * Project Duke CS2103
 * Done by Hong Jin.
 */
package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * public class FindCommand to search for relevant command based on given keywords.
 */
public class FindCommand extends Command {
    private String[] keywords;

    public FindCommand(String... keywords) {
        super();
        this.keywords = keywords;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printMessage(tasks.findTask(this.keywords));
    }
}
