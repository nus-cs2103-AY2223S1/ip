package command;

import exception.MeowerException;
import exception.MissingArgumentException;
import meower.Storage;
import meower.TaskList;
import meower.Ui;
import task.Task;
import task.ToDo;

public class TodoCommand extends Command{

    private String description;
    
    public TodoCommand(String description) {
        super();
        this.description = description;
    }

    
    /** 
     * Returns description inputted by the user
     * @return String
     */
    public String getDescription() {
        return this.description;
    }
  
    /** 
     * Executes the functionality of the command, in the tasklist, UI and storage that are taken in as arguments, 
     * in this case adds the Todo Task defined by the user into the tasklist
     * @param tasks tasklist from Meower chatbot
     * @param ui ui from Meower chatbot
     * @param storage storage from Meower chatbot
     * @throws MeowerException Main Meower chatbot Exception
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MeowerException{
        try {
            Task newEvent = this.getTask();
            tasks.add(newEvent);
            return ui.add(newEvent);
        } catch (MeowerException e) {
            throw e;
        }
    }

    
    /** 
     * Returns the task that will be generated from the command, returns an empty task if no task is to be generated
     * @return Task
     * @throws MeowerException Main Meower chatbot Exception
     */
    @Override
    public Task getTask() throws MeowerException{
        try {
            return new ToDo(description);
        } catch (MissingArgumentException e) {
            throw new MeowerException(e.getLocalizedMessage());
        }
    }

}
