package duke.command;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {
    private String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    /**
     * Executes the command to find tasks containing the given keyword.
     * Then it prints the matching tasks as a list.
     *
     * @param storage  Storage handling the file IO.
     * @param taskList A list of tasks.
     * @param ui       A ui to handle printing output.
     * @return notification that the work has been done
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws DukeException {
        assert storage != null;
        assert taskList != null;
        assert ui != null;

        return taskList.find(keyword);
    }
}