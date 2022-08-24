package duke.command;

import static duke.common.Messages.MESSAGE_FOUND_TASK;

import duke.storage.TaskRecords;
import duke.ui.BotUI;
import duke.task.Task;


public class FindCommand extends Command{
    private final String details;

    public FindCommand(String command, String details) {
        super(command);
        this.details = details;
    }

    @Override
    public void execute(TaskRecords taskList, BotUI ui) {
        StringBuilder foundTasks = new StringBuilder(MESSAGE_FOUND_TASK + "\n");
        boolean found = false;
        for(Task t : taskList.getList()) {
            if (t.getDetail().contains(this.details)) {
                foundTasks.append(t).append("\n");
                found = true;
            }
        }
        if (found) {
            System.out.println(ui.botSpeak(foundTasks.toString()));
        } else {
            System.out.println(ui.botSpeak("Nothing found!"));
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
