package alpha.command;

import alpha.AlphaException;
import alpha.FileOperations;
import alpha.TaskList;
import alpha.Ui;
import alpha.task.Deadline;
import alpha.task.Event;
import alpha.task.Task;

/**
 * Adds a new task to the task list.
 */
public class Add extends Command {

    /** Task to be added to tbe list */
    protected Task task;

    /**
     * Constructor that initialises the global variables.
     *
     * @param task To initialise the task to tbe added to the task list.
     */
    public Add(Task task) {
        this.task = task;
    }

    /**
     * {@inheritDoc}
     *
     * Adds task to the taskList and writes it to the file.
     * Returns a message to indicate successful addition of the task.
     * @return Message to display successful completion of task.
     */
    @Override
    public String execute(TaskList taskList, Ui uI, FileOperations fileOperations) throws AlphaException {
        taskList.addToTaskList(task);
        String textToAppend;
        if (this.task instanceof Event) {
            Event event = (Event) task;
            textToAppend = "[" + event.getTaskType() + "] [" + event.getTag() + "] [" + event.getStatus() + "] "
                    + event.getDescription() + " (on " + event.getDate() + ")\n";
        } else if (this.task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            textToAppend = "[" + deadline.getTaskType() + "] [" + deadline.getTag() + "] [" + deadline.getStatus()
                    + "] " + deadline.getDescription() + " (by " + deadline.getDeadline() + ")\n";
        } else {
            textToAppend = "[" + task.getTaskType() + "] [" + task.getTag() + "] ["
                    + task.getStatus() + "] " + task.getDescription() + "\n";
        }
        fileOperations.writeToFile(textToAppend);
        return uI.generateCommandExecutionMessage(this, taskList, task, 0);
    }

    /**
     * {@inheritDoc}
     *
     * Checks the equality of two objects.
     * Returns true if both objects are instances of Add class and add tasks with the same attributes.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Add) {
            Add a = (Add) obj;
            return (a.task.equals(this.task));
        }
        return false;
    }
}
