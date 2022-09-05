package commands;

import duke.*;

/**
 * Command that updates a Task.
 */
public class UpdateTaskCommand extends Command {
    private Task task;
    private Ui ui;
    private String update;

    /**
     * Returns a new UpdateTaskCommand.
     * @param task Task to be updated.
     * @param ui User Interface to print a message to the user.
     * @param update Update to be performed.
     */
    public UpdateTaskCommand(Task task, Ui ui, String update) {
        this.task = task;
        this.ui = ui;
        this.update = update;
    }

    /**
     * Executes the command, and returns a String
     * describing the execution of this Command.
     * @return String describing newly updated Task.
     * @throws DukeException if input format is incorrect.
     */
    public String execute() throws DukeException {
        if (this.task instanceof ToDo) {
            ToDo t = (ToDo) task;
            t.setDescription(update);
        } else if (this.task instanceof Deadline) {
            Deadline d = (Deadline) task;
            if (this.update.startsWith("date")) {
                String[] info = update.split("date ");
                d.setDeadline(info[1]);
            } else {
                d.setDescription(update);
            }
        } else if (this.task instanceof Event) {
            Event e = (Event) task;
            if (this.update.startsWith("time")) {
                String[] info = update.split("time ");
                e.setTime(info[1]);
            } else {
                e.setDescription(update);
            }
        } else {
            throw new DukeException(
                    "ToDo update format: update[number] Do homework\n"
                            + "Event update format: update [number] Meetup/update [number] time 9:00pm\n"
                            + "Deadline update format: update [number] Buy food/update [number] date yyyy-mm-dd");
        }
        return ui.showTask(this.task);
    }
}
