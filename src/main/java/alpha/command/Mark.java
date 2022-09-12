package alpha.command;

import alpha.AlphaException;
import alpha.FileOperations;
import alpha.TaskList;
import alpha.Ui;

/**
 * Marks task of the input task number.
 */
public class Mark extends Command {

    /** Task number of the task to be marked as done */
    private final int taskNumber;

    /**
     * Constructor that initialises the global variables.
     *
     * @param taskNumber To initialise the task number of the task to be marked as done.
     */
    public Mark(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * {@inheritDoc}
     *
     * Marks the task with the given task number as done and rewrites the file.
     * Returns a message to indicate the successful completion of marking the task.
     * @return A message to indicate the completion of task.
     */
    @Override
    public String execute(TaskList taskList, Ui uI, FileOperations fileOperations) throws AlphaException {
        taskList.modifyTaskStatus(taskNumber, true);
        fileOperations.rewriteFile(taskList);
        return uI.returnText(">> " + "marked task: " + this.taskNumber);
    }

    /**
     * {@inheritDoc}
     *
     * Checks the equality of two objects
     * Returns true if both objects are instance of Mark class and mark the task with the same task number.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Mark) {
            Mark m = (Mark) obj;
            return (m.taskNumber == this.taskNumber);
        }
        return false;
    }
}
