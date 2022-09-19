package command;

import exception.MeowerException;
import exception.InvalidDateException;
import exception.MissingArgumentException;
import meower.Storage;
import meower.TaskList;
import meower.Ui;
import task.Deadline;
import task.Task;

/**
 * Represents a "deadline" command from the user that will schedule a new Deadline task in the chatbot.
 */

public class DeadlineCommand extends Command {

    private String description;
    private String date;

    public DeadlineCommand(String description, String date) {
        super();
        this.description = description;
        this.date = date;
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
     *  in this case adds the deadline task described by the user into the tasklist
     * @param tasks tasklist from Meower chatbot
     * @param ui ui from Meower chatbot
     * @param storage storage from Meower chatbot
     * @throws MeowerException
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MeowerException {
        try {
            Task newDeadline = this.getTask();
            tasks.add(newDeadline);
            return ui.add(newDeadline);
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
            return new Deadline(description, date);
        } catch (MissingArgumentException e) {
            throw new MeowerException(e.getLocalizedMessage());
        } catch (InvalidDateException e) {
            throw new MeowerException(e.getLocalizedMessage());
        }
    }
    
}
