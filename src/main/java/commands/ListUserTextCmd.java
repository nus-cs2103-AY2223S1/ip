package commands;

import java.util.ArrayList;
import java.util.List;

public class ListUserTextCmd extends Command {
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.notifyUser("Here are your current tasks:");
        List<String> taskPrint = new ArrayList<>();
        tasks.fillTaskPrint(taskPrint);
        for (String task : taskPrint) {
            ui.notifyUser(task);
        }
    }
}
