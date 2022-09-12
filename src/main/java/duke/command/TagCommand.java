package duke.command;

import duke.TaskList;
import duke.exception.DukeException;
import duke.task.Task;

import static duke.exception.ErrorMessage.*;

public class TagCommand implements Command {
    private TaskList tasks;
    private String taskType;
    private String info;

    public TagCommand(TaskList tasks, String taskType, String info) {
        this.tasks = tasks;
        this.taskType = taskType;
        this.info = info;
    }

    public String splitInfo() throws DukeException {
        if (!info.contains("/with")) {
            throw new DukeException(INVALID_TAG_COMMAND, "");
        } else if (info.split("/with").length == 1) {
            throw new DukeException(INVALID_TAG, "");
        }
        String index = info.split("/with")[0].trim();
        String tag = info.split("/with")[1].trim();
        if (!index.matches("[0-9]+") || index.equals("")) {
            throw new DukeException(MISSING_INDEX, "");
        } else if (!tag.matches("[a-zA-Z]+") || tag.equals("")) {
            throw new DukeException(INVALID_TAG, "");
        } else if (Integer.parseInt(index) > tasks.getSize() || Integer.parseInt(index) < 1) {
            throw new DukeException(INVALID_INDEX, "");
        }
        return String.format("%s %s", index, tag);
    }

    public String execute() throws DukeException {
        String index = this.splitInfo().split(" ")[0];
        String tag = this.splitInfo().split(" ")[1].trim();
        Task task = tasks.getTask(Integer.parseInt(index) - 1);
        if (taskType.equals("tag") && task.noOfTags() >= 3) {
            throw new DukeException(EXCEEDING_TAG_NUMBER, "");
        } else if (taskType.equals("tag") && task.containsTag(tag)) {
            throw new DukeException(TAG_ALREADY_IN_TASK, tag);
        } else if (taskType.equals("untag") && !task.containsTag(tag)) {
            throw new DukeException(TAG_NOT_IN_TASK, tag);
        } else if (taskType.equals("tag") && task.noOfTags() < 3) {
            task.addTag(tag);
            return String.format("tag %s %s", index, tag);
        } else {
            task.deleteTag(tag);
            return String.format("untag %s %s", index, tag);
        }

    }
}

