package commandhandler;

import data.TaskList;
import data.tasks.TaskDeadline;
import java.util.List;
import util.CommandUtils;

public class CommandDeadlineHandler extends CommandHandler {

    CommandDeadlineHandler(TaskList taskList) {
        super(taskList);
    }

    @Override
    public boolean validateCommand(List<String> commandTokens) {
        // 1. Contains the deadline marker
        // 2. There is a data specified after the deadline marker
        // 3. Deadline marker is not right after command as it implies no task description
        int deadlineMarkerIdx = commandTokens.indexOf(TaskDeadline.deadlineMarker);
        return commandTokens.size() > 1 && deadlineMarkerIdx != -1
            && deadlineMarkerIdx != commandTokens.size() - 1 && deadlineMarkerIdx != 1;
    }

    @Override
    public List<String> run(List<String> commandTokens) {
        int deadlineMarkerIdx = commandTokens.indexOf(TaskDeadline.deadlineMarker);
        String deadlineDesc = gatherCommandTokens(commandTokens, 1, deadlineMarkerIdx, " ");
        String deadline = gatherCommandTokens(commandTokens, deadlineMarkerIdx + 1,
            commandTokens.size(), " ");

        TaskDeadline deadlineTask = new TaskDeadline(deadlineDesc, deadline);
        taskList.addTask(deadlineTask);

        return CommandUtils.generateAddTaskResponse(deadlineTask, taskList.size());
    }
}
