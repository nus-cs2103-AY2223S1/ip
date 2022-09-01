package alpha.command;

import alpha.AlphaException;
import alpha.FileOperations;
import alpha.TaskList;
import alpha.Ui;

public class Unmark extends Command {

    /** Task number of the task to be marked as done */
    private int taskNumber;

    /**
     * Constructor that initialises the global variables.
     *
     * @param taskNumber To initialise the task number of the task to be unmarked.
     */
    public Unmark(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * {@inheritDoc}
     *
     * Unmarks the task with the given task number and rewrites the file.
     * Displays a message to indicate the successful completion of unmarking the task.
     */
    @Override
    public void execute(TaskList taskList, Ui uI, FileOperations fileOperations) throws AlphaException {
        taskList.modifyTaskStatus(taskNumber, false);
        fileOperations.rewriteFile(taskList);
        uI.colouredPrint(uI.getANSI_CODE("ANSI_BLUE"), ">> " + "unmarked: Task " + this.taskNumber);
    }

    /**
     * {@inheritDoc}
     *
     * Checks the equality of two objects
     * Returns true if both objects are instance of Unmark class and unmark the task with the same task number.
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        } else if(obj instanceof Unmark) {
            Unmark um = (Unmark) obj;
            return (um.taskNumber == this.taskNumber);
        }
        return false;
    }
}
