package duke.command;

import duke.exception.DukeException;
import duke.exception.MatchException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.UI;

import java.util.ArrayList;

public class FindCommand extends Command{
    private String find;

    public FindCommand(String find) {
        this.find = find;
    }

    @Override
    public void execute(Storage storage, UI ui, TaskList taskList) throws DukeException {
        TaskList tasks = new TaskList(new ArrayList<>());
        for(int i = 0; i < taskList.size(); i++) {
            if(taskList.getTask(i).getDescription().contains(find)) {
                tasks.add(taskList.getTask(i));
            }
        }
        if(tasks.size() == 0) {
            throw new MatchException();
        } else {
            ui.showMatchingTasks();
            taskList.read();
        }
    }
}
