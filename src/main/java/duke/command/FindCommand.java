package duke.command;

import duke.*;

import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command {

    String name;

    public FindCommand(String name) {
        this.name = name;
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        List<Integer> indexList = new ArrayList<>();
        for (int i = 0; i < taskList.size() ; i++ ) {
            if (taskList.getTask(i+1).hasName(this.name)) {
                indexList.add(i+1);
            }
        }
        return ui.showFindResult(indexList,taskList);
    }

}
