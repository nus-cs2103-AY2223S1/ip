package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * Represents a command that is used to find a task in the tasklist and print out
 * the task that was found.
 */
public class FindCommand extends Command {
    private String input;

    /**
     * Constructor for the FindCommand
     *
     * @param input
     */
    public FindCommand(String input) {
        int taskIndex = 5;
        this.input = input.substring(taskIndex);
    }

    /**
     * Returns a string after method is used to find a task of a given name.
     *
     * @param taskList
     * @param archiveTaskList
     * @param storage
     * @param archiveStorage
     * @param ui
     */
    @Override
    public String execute(TaskList taskList, TaskList archiveTaskList, Storage storage,
                          Storage archiveStorage, Ui ui) {
        String output = "";
        for (int i = 0; i < taskList.length(); i++) {
            String taskString = taskList.getTask(i).toString();
            if (taskString.contains(input)) {
                output += taskString + "\n";
            }
        }
        if (output == "") {
            return "Sorry! I couldnt find anything :(";
        } else {
            output += "I found these/this!";
            return output;
        }
    }
}
