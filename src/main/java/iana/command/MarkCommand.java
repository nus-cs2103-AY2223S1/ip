package iana.command;

import iana.tasks.TaskList;
import iana.ui.Ui;

/**
 * Command that marks task as completed.
 */
public class MarkCommand extends Command {
    public String taskNum;
    
    /**
     * Constructor for MarkCommand class.
     * 
     * @param taskNum task number to be marked as completed.
     */
    public MarkCommand(String taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Runs command to mark task.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        try {
            int taskNumber = Integer.parseInt(this.taskNum) - 1;
            tasks.mark(taskNumber);
            String markedMsg = "Nice! I've marked this task as done:\n";
            return ui.say(String.format("%s\t   %s", markedMsg, tasks.printTaskString(taskNumber)));
        } catch (ArrayIndexOutOfBoundsException e) {
            return ui.say(e.getMessage());
        }
    }

    /**
     * Returns false as command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}