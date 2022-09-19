package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/** A class that represents a command to view the current tasks in the to-do list */
public class ViewCommand extends Command {

    private String keyword;
    private String userInput;

     public ViewCommand(String keyword, String userInput) {
         this.keyword = keyword;
         this.userInput = userInput;
     }

    /**
     * Executes the listing of all current tasks in the to-do list, after receiving the appropriate input from the user.
     *
     * @param tasks The TaskList object that is keeping track of all the current tasks.
     * @param ui The UI object that displays messages to the user.
     * @param storage The storage used to save the task to file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
         if (this.keyword.equals("list")) {
             return ui.listTasks(tasks);
         } else if (this.keyword.equals("find")) {
             String wordToSearch = this.userInput.split(" ", 2)[1];
             return tasks.findTasks(wordToSearch, ui);
         }
         return "oops, I am unable to execute your command";
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
