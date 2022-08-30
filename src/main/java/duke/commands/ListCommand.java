package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * This class encapsulates an Exit Command
 */
public class ListCommand extends Command {

    private String date;

    /**
     * Constructs a new list command
     * @param info Essential information for the output list
     */
    public ListCommand(String ...info) {
        super();
        if (info.length == 1) {
            this.date = null;
        } else {
            this.date = info[1];
        }
    }

    /**
     * Checks if the command is an Exit Command
     * @return True if it is an Exit Command
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the Command
     * @param ui Prints the messages based on the type of Command
     * @param storage Saves the modified list of tasks
     * @param taskList List of tasks
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        String list;
        if (date == null) {
            list = taskList.list();
        } else {
            list = taskList.getTasks(date);
        }
        ui.printList(list);
    }
}
