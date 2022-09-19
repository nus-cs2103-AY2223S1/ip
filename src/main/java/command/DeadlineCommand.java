package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;
import exception.InvalidDateException;
import exception.MissingArgumentException;
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
     * Checks if command will cause chatbot to end.
     * @return boolean
     */
    @Override
    public boolean isEnd() {
        return false;
    }

    
    /** 
     * Executes the functionality of the command, in the tasklist, UI and storage that are taken in as arguments,
     *  in this case adds the deadline task described by the user into the tasklist
     * @param tasks
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task newDeadline = this.getTask();
            tasks.add(newDeadline);
            return ui.add(newDeadline);
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
    public Task getTask() throws DukeException {
        try {
            return new Deadline(description, date);
        } catch (MissingArgumentException e) {
            throw new DukeException(e.getLocalizedMessage());
        } catch (InvalidDateException e) {
            throw new DukeException(e.getLocalizedMessage());
        }
    }
    
}
