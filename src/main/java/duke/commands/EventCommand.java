package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.task.Event;
import duke.ui.Ui;
public class EventCommand extends Command{

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String[] splitInput = ui.userString().split(" ");
        System.out.println("Got it. I've added this task");
        StringBuilder event = new StringBuilder();

        for (int i = 1; i < splitInput.length; ++i) {
            if (splitInput[i].equals("/at")) break;
            event.append(" " + splitInput[i]);
        }

        Event eventTask = new Event(event.toString(), ui.userString().split("/at")[1].trim());
        taskList.add(eventTask);
        System.out.printf("\t %s\n", eventTask);
        System.out.printf("Now you have %d tasks in the list.\n", taskList.size());
        storage.save(taskList);
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
