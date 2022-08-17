package commandhandler;

import data.TaskList;
import data.tasks.TaskEvent;
import util.CommandUtils;

import java.util.List;

public class CommandEventHandler extends CommandHandler {

    CommandEventHandler(TaskList taskList) {
        super(taskList);
    }

    @Override
    public boolean validateCommand(List<String> commandTokens) {
        // 1. Contains the timing marker
        // 2. There is a data specified after the timing marker
        // 3. Timing marker is not right after command as it implies no task description
        int timingMarkerIdx = commandTokens.indexOf(TaskEvent.timingMarker);
        return commandTokens.size() > 1 && timingMarkerIdx != -1
            && timingMarkerIdx != commandTokens.size() - 1 && timingMarkerIdx != 1;
    }

    @Override
    public List<String> run(List<String> commandTokens) {
        int timingMarkerIdx = commandTokens.indexOf(TaskEvent.timingMarker);
        String eventDesc = gatherCommandTokens(commandTokens, 1, timingMarkerIdx, " ");
        String timing = gatherCommandTokens(commandTokens, timingMarkerIdx + 1,
            commandTokens.size(), " ");

        TaskEvent eventTask = new TaskEvent(eventDesc, timing);
        taskList.addTask(eventTask);

        return CommandUtils.generateAddTaskResponse(eventTask, taskList.size());
    }
}
