package duke.command;

import duke.TaskList;
import duke.exception.DukeException;
import duke.task.Task;

import static duke.exception.ErrorMessage.*;

/**
 * Represents a TagCommand to add tag/ delete tag in a task. This class implements Command Interface.
 */
public class TagCommand implements Command {
    private TaskList tasks;
    private String commandType;
    private String info;

    /**
     * This method is a TagCommand constructor.
     * A TagCommand consists of a TaskList, a commandType (tag/ untag), an info String containing task index and tag.
     * @param tasks a list of tasks.
     * @param commandType commandType (tag/ untag).
     * @param info info String.
     */
    public TagCommand(TaskList tasks, String commandType, String info) {
        this.tasks = tasks;
        this.commandType = commandType;
        this.info = info;
    }

    /**
     * Checks the validity of tag command, splits info into a string containing task index and tag.
     *
     * @throws DukeException if TagCommand not valid.
     * @return a string containing task index and tag.
     */
    public String splitInfo() throws DukeException {
        if (!info.contains("/with") || info.equals("/with")) { //check command format
            throw new DukeException(INVALID_TAG_COMMAND, "");
        } else if (info.split("/with").length == 1 && info.substring(0,5).equals("/with")) {
            throw new DukeException(INVALID_TAG, "");
        } else if (info.split("/with").length == 1) {
            throw new DukeException(MISSING_INDEX, "");
        }
        String index = info.split("/with")[0].trim();
        String tag = info.split("/with")[1].trim();
        if (!index.matches("[0-9]+") || index.equals("")) { //check missing/invalid tag parameter
            throw new DukeException(MISSING_INDEX, "");
        } else if (!tag.matches("[a-zA-Z]+") || tag.equals("")) {
            throw new DukeException(INVALID_TAG, "");
        } else if (Integer.parseInt(index) > tasks.getSize() || Integer.parseInt(index) < 1) {
            throw new DukeException(INVALID_INDEX, "");
        }
        return String.format("%s %s", index, tag);
    }

    /**
     * Executes tag command to add/delete tag to the indexed task in the TaskList.
     * Returns a string containing commandType and task index (1-indexed).
     * Returned value will be used to call chatbot response message.
     * If tag number exceeds 3/ tag already exists/ tag to be deleted does not exist, the command is not executed.
     *
     * @throws DukeException if tag number exceeds 3/ tag already exists/ tag to be deleted does not exist.
     * @return a string containing commandType and task index.
     */
    public String execute() throws DukeException {
        String index = this.splitInfo().split(" ")[0];
        String tag = this.splitInfo().split(" ")[1].trim();
        Task task = tasks.getTask(Integer.parseInt(index) - 1);
        if (commandType.equals("tag") && task.noOfTags() >= 3) {
            throw new DukeException(EXCEEDING_TAG_NUMBER, "");
        } else if (commandType.equals("tag") && task.containsTag(tag)) {
            throw new DukeException(TAG_ALREADY_IN_TASK, tag);
        } else if (commandType.equals("untag") && !task.containsTag(tag)) {
            throw new DukeException(TAG_NOT_IN_TASK, tag);
        } else if (commandType.equals("tag") && task.noOfTags() < 3) {
            task.addTag(tag);
            return String.format("tag %s %s", index, tag);
        } else {
            task.deleteTag(tag);
            return String.format("untag %s %s", index, tag);
        }

    }
}

