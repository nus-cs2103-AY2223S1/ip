package duke.command;

import java.util.ArrayList;

import duke.TaskList;
import duke.Ui;
import duke.storage.Storage;
import duke.task.IndexedTask;
import duke.task.Task;

/**
 * This class represents the find command that allows the user
 * to find tasks based on keyword.
 */
public class FindCommand extends Command {
    
    /** The keyword used to find the tasks. */
    private String keyword;

    /** The list of tasks with description containing the keyword. */
    private ArrayList<IndexedTask> foundTasks;

    /** Constructs the find command. */
    public FindCommand(String keyword) {
        this.keyword = keyword;
        this.foundTasks = new ArrayList<>();
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        this.foundTasks = tasks.findTasksContainingKeyword(this.keyword);
        return ui.indentResponse(this.toString());
    }

    @Override
    public String toString() {
        if (this.foundTasks.size() == 0) {
            return "Well, I can't find any task that matches your keyword :(";
        }
        String result = "Here is what I have found...";
        for (IndexedTask t : this.foundTasks) {
            result += String.format("\n%s",t.toString());
        }
        return result;
    }

}
