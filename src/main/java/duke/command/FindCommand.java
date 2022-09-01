package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A command that is used to find Tasks that contain the keyword.
 *
 * @author Lee Ian Ee
 * @version CS2103T AY22/23 Sem 1
 */

public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor for FindCommand.
     * @param keyword Keyword that the Tasks to be searched for have to contain.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Returns the toString of the TaskList containing the list of tasks that contain the keyword.
     * @param list TaskList containing the list of tasks.
     * @param storage Storage that loads and saves to the save file.
     * @return toString of keyword TaskList.
     */
    @Override
    public String execute(TaskList list, Storage storage) {
        return Ui.printList(list.findTask(this.keyword));
    }
}
