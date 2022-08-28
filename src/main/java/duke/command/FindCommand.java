package duke.command;

import duke.*;

public class FindCommand extends Command {
    private String desc;

    public FindCommand(String desc) {
        this.desc = desc;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        UI.find(taskList, this.desc);
    }

}
