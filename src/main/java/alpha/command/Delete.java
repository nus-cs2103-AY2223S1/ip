package alpha.command;

import alpha.AlphaException;
import alpha.FileOperations;
import alpha.TaskList;
import alpha.Ui;

public class Delete extends Command {

    /** The task number of the task to be deleted */
    private int taskNumber;

    /**
     * Constructor to initialise the global variables.
     *
     * @param taskNumber To initialise the task number of the task to be deleted.
     */
    public Delete(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * {@inheritDoc}
     *
     * Deletes the task with the given task number from the task list and rewrites the file.
     * Displays a message to indicate successful deletion of the task.
     */
    @Override
    public void execute(TaskList taskList, Ui uI, FileOperations fileOperations) throws AlphaException {
        taskList.deleteTask(taskNumber);
        fileOperations.rewriteFile(taskList);
        uI.colouredPrint(uI.getANSI_CODE("ANSI_BLUE"), ">> " + "deleted: Task " + this.taskNumber);
    }

    /**
     * {@inheritDoc}
     *
     * Checks the equality of two objects.
     * Returns true if both objects are instances of Delete class and delete tasks with the same task number.
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        } else if(obj instanceof Delete) {
            Delete d = (Delete) obj;
            return (d.taskNumber == this.taskNumber);
        }
        return false;
    }
}
