package command;

import exception.MeowerException;
import exception.InvalidDateException;
import exception.MissingArgumentException;
import meower.Storage;
import meower.TaskList;
import meower.Ui;
import task.Event;
import task.Task;

public class EventCommand extends Command {

    private String description;
    private String duration;

    public EventCommand(String description, String duration) {
        super();
        this.description = description;
        this.duration = duration;
    }
    
    /** 
     * Executes the functionality of the command, in the tasklist, UI and storage that are taken in as arguments, 
     * in this case adds the event task described by the user into the tasklist
     * @param tasks tasklist from Meower chatbot
     * @param ui ui from Meower chatbot
     * @param storage storage from Meower chatbot
     * @throws MeowerException Main Meower chatbot Exception
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MeowerException {
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
    public Task getTask() throws MeowerException {
        try {
            return new Event(description, duration);
        } catch (MissingArgumentException e) {
            throw new MeowerException(e.getLocalizedMessage());
        } catch (InvalidDateException e) {
            throw new MeowerException(e.getLocalizedMessage());
        }
    }
}
