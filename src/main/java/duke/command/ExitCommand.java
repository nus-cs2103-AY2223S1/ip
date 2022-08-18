package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class ExitCommand extends Command {
    public static final String COMMAND_ID = "BYE";
    private static String EXIT_MSG = "Bye. Hope to see you again soon!";

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println(EXIT_MSG);
    }
}
