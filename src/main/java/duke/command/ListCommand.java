package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * This command lists out all the tasks in the user's TaskList when executed.
 */
public class ListCommand extends Command {

    /** The keyword to run ListCommand. */
    public static final String COMMAND_NAME = "list";

    /**
     * Sole constructor of ListCommand.
     */
    public ListCommand() {

    }

    /**
     * Displays all tasks in the specified TaskList parameter to
     * the specified Ui parameter.
     *
     * @param tasks the specified TaskList object.
     * @param ui the specified Ui object.
     * @param storage the specified Storage object.
     */
    @Override
    public void exec(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder reply = new StringBuilder();
        String[] stringifiedTaskList = tasks.toStringList();
        reply.append("Here are your tasks that I have recorded:");
        if (stringifiedTaskList.length == 0) {
            reply.append("\nCongratulations, you don't need to do anything right now!");
        }
        for (int i = 0; i < stringifiedTaskList.length; i++) {
            reply.append(String.format("\n%02d. %s", i + 1, stringifiedTaskList[i]));
        }
        ui.showReply(reply.toString());
    }

    /**
     * Returns false as ListCommand is not a terminating Command.
     *
     * @return false.
     */
    @Override
    public boolean isTerminator() {
        return false;
    }
}
