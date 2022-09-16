package alpha.command;

import alpha.AlphaException;
import alpha.FileOperations;
import alpha.TaskList;
import alpha.Ui;
import alpha.task.Task;

/**
 * Tags a task in the task list.
 */
public class Tag extends Command {

    /** Task number of the task to be tagged */
    private int taskNumber;

    /** Tag to be added to the task */
    private String tag;

    /**
     * Constructor to initialise the instance variables.
     * @param taskNumber To initialise the task number of the task to be tagged.
     * @param tag To initialise the tag to be added to the task.
     */
    public Tag(int taskNumber, String tag) {
        this.taskNumber = taskNumber;
        this.tag = tag;
    }

    /**
     * {@inheritDoc}
     *
     * Tags the task with the given task number with the input tag and rewrites the file.
     * Returns a message to indicate the successful completion of tagging the task.
     * @return A message to indicate the completion of task.
     */
    @Override
    public String execute(TaskList taskList, Ui uI, FileOperations fileOperations) throws AlphaException {
        Task t = taskList.addTagToTask(taskNumber, tag);
        fileOperations.rewriteFile(taskList);
        return uI.generateCommandExecutionMessage(this, taskList, t, taskNumber);
    }

    /**
     * {@inheritDoc}
     *
     * Checks the equality of two objects
     * Returns true if both objects are instance of Tag class and tags the task with the same task number.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Tag) {
            Tag t = (Tag) obj;
            return (t.taskNumber == this.taskNumber);
        }
        return false;
    }
}
