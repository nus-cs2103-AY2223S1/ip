package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ViewCommand extends Command {

    private String keyword;
    private String userInput;

     public ViewCommand(String keyword, String userInput) {
         this.keyword = keyword;
         this.userInput = userInput;
     }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
         if (this.keyword.equals("list")) {
             ui.listTasks(tasks);
         } else if (this.keyword.equals("find")) {
             String wordToSearch = this.userInput.split(" ", 2)[1];
             tasks.findTasks(wordToSearch, ui);
         }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
