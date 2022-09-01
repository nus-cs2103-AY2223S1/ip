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

    public FindCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String output = "";
        for (int i = 0; i < taskList.length(); i++) {
            String taskString = taskList.getTask(i).toString();
            if (taskString.contains(input)) {
                ui.repeater(i + 1 + ". " + taskString);
                output += i + 1 + ". " + taskString + "\n";
            }
        }
        output += "I found these/this!";
        return output;
    }
}
