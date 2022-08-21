package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents an Find Command
 */
public class FindCommand extends Command {
    private String text;

    /**
     * Creates a Delete Command object
     */
    public FindCommand(String text){
        this.text = text;
    }


    /**
     * Prints out a list of tasks that
     * contains the text String
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println(taskList.findTask(text));
    }
}
