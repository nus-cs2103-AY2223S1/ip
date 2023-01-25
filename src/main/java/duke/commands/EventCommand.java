package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.task.Event;
import duke.ui.Ui;

/**
 * Command to create an event task.
 */
public class EventCommand extends Command {

    /**
     * Executes the command.
     *
     * @param taskList
     * @param ui User Interface of the to-do-list.
     * @param storage Storage option.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {

        StringBuilder sb = new StringBuilder();

        String[] splitInput = ui.userString().split(" ");
        sb.append("Got it. I've added this task\n");
        StringBuilder event = new StringBuilder();

        for (int i = 1; i < splitInput.length; ++i) {
            if (splitInput[i].equals("/at")) {
                break;
            }
            sb.append(" " + splitInput[i]);
            event.append(" " + splitInput[i]);
        }

        String dateAndTimeString = ui.userString().split("/at")[1].trim();
        Event eventTask = new Event(event.toString(), dateAndTimeString);
        taskList.add(eventTask);
        sb.append(String.format("\t %s\n", eventTask));
        sb.append(String.format("Now you have %d tasks in the list.\n", taskList.size()));
        storage.save(taskList);
        return sb.toString();
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
