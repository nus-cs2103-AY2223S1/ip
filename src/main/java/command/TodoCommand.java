package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;
import exception.MissingArgumentException;
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
     * Checks if command will cause chatbot to end
     * @return boolean
     */
    @Override
    public boolean isEnd() {
        return false;
    }

    
    /** 
     * Executes the functionality of the command, in the tasklist, UI and storage that are taken in as arguments, 
     * in this case adds the Todo Task defined by the user into the tasklist
     * @param tasks
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        try {
            Task newEvent = this.getTask();
            tasks.add(newEvent);
            return ui.add(newEvent);
        } catch (DukeException e) {
            throw e;
        }
    }

    
    /** 
     * Returns the task that will be generated from the command, returns an empty task if no task is to be generated
     * @return Task
     * @throws DukeException
     */
    @Override
    public Task getTask() throws DukeException{
        try {
            return new ToDo(description);
        } catch (MissingArgumentException e) {
            throw new DukeException(e.getLocalizedMessage());
        }
    }

}
