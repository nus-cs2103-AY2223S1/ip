package command;

import meower.Storage;
import meower.TaskList;
import meower.Ui;
import task.Task;

public class FindCommand extends Command{
    
    private String keyword;
    
    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }
    
    /** 
     *  Executes the functionality of the command, in the tasklist, UI and storage that are taken in as arguments, 
     * in this case it lists all tasks in the tasklist with the given keyword
     * @param tasks tasklist from Meower chatbot
     * @param ui ui from Meower chatbot
     * @param storage storage from Meower chatbot
     * @return String
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.list(tasks.search(keyword), true);
    }

    
    /** 
     * Returns the task that will be generated from the command, returns an empty task if no task is to be generated
     * @return Task
     */
    @Override
    public Task getTask() {
        return Task.empty();
    }
}
