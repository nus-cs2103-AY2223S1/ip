package alpha.command;

import alpha.AlphaException;
import alpha.FileOperations;
import alpha.TaskList;
import alpha.Ui;

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

    @Override
    public String execute(TaskList taskList, Ui uI, FileOperations fileOperations) throws AlphaException {
        taskList.addTagToTask(taskNumber, tag);
        fileOperations.rewriteFile(taskList);
        return uI.generateCommandExecutionMessage(this, null, taskNumber);
    }
}
