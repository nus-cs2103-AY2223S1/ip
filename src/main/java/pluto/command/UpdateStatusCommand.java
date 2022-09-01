package pluto.command;

import pluto.PlutoException;
import pluto.Storage;
import pluto.TaskList;
import pluto.Ui;

import java.io.IOException;

public class UpdateStatusCommand extends Command {

    /** Index of the task whose status needs to be updated */
    private int idx;
    /** Status of the updated task */
    private boolean isDone;

    /**
     * Constructor that initializes global variables.
     * @param idx Index of task to update status.
     * @param isDone Status of the task.
     */
    public UpdateStatusCommand(int idx, boolean isDone) {
        this.idx = idx;
        this.isDone = isDone;
    }

    /**
     * {@inheritDoc}
     *
     * Changes the status of a task, updates the local file,
     * and displays an appropriate message.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PlutoException {
        tasks.markTask(idx, isDone);
        try {
            storage.rewriteFile(tasks);
            StringBuilder markMessage = new StringBuilder();
            if (isDone) {
                markMessage.append("\tNice! I've marked this task as done:\n");
            } else {
                markMessage.append("\tOK, I've marked this task as not done yet:\n");
            }
            markMessage.append("\t\t" + tasks.getTask(idx).toString());
            ui.print(markMessage);
        } catch (IOException e) {
            tasks.markTask(idx, !isDone);
            throw new PlutoException("\tOOPS!!! Couldn't update task status. Try again!");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof UpdateStatusCommand) {
            UpdateStatusCommand other = (UpdateStatusCommand) o;
            return this.idx == other.idx && this.isDone == other.isDone;
        }
        return false;
    }

}
