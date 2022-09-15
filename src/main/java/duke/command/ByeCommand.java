package duke.command;

import duke.TaskList;
import duke.Ui;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui) {
        System.out.println("Bye. Hope to see you again soon!");
        setExit();
    }
}
