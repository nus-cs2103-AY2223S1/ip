package duke.command;

import duke.data.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents an incorrect command. Upon execution, produces some feedback to the user.
 */
public class IncorrectCommand extends Command {

    public final String feedbackToUser;

    public IncorrectCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }
    
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        System.out.println(feedbackToUser);    
    }
}
