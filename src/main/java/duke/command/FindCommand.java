package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {

    private String description;

    /**
     * Constructor for {@code FindCommand}
     * @param description the searching criteria
     */
    public FindCommand(String description) {
        this.description = description;
    }

    /**
     * To execute the {@code FindCommand}
     * @param tasks the current {@code TaskList}
     * @param ui the current {@code Ui}
     * @param storage the current {@code Storage}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.findTask(tasks, description);
    }
}
