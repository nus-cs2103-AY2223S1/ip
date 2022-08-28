package duke.command;

import java.util.ArrayList;
import java.util.stream.Collectors;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * A Command for finding Task objects containing a certain string.
 */
public class FindCommand extends Command {
    private String search;

    /**
     * Constructs a FindCommand object which displays Task objects containing the search string when executed.
     * @param search
     */
    public FindCommand(String search) {
        this.search = search;
    }

    /**
     * Finds all the relevant Task objects with search string specified in constructor.
     * @param taskList
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showList(new ArrayList<>(taskList
                .getTaskList()
                .stream()
                .filter(x -> x.toString().contains(this.search))
                .collect(Collectors.toList())));
    }

    /**
     * Whether the FindCommand object should end the Duke object.
     * It should not.
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
