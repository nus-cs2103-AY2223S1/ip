package duke.tasks;

import duke.DukeException;
import utils.Constants;
import utils.DukeUtils;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private final List<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    private Task getTask(int index) {
        return tasks.get(index);
    }

    public void printTasks() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format("  %d.%s\n\t", i + 1, getTask(i)));
        }
        DukeUtils.printMessages(Constants.MSG_TASK_LIST, sb.toString().stripTrailing());
    }

    public void addTask(String inputCommand, String inputDesc) {
        Task task = TaskFactory.createTask(inputCommand, inputDesc);
        tasks.add(task);
        DukeUtils.printMessages(
                Constants.MSG_TASK_ADDED,
                "  " + task,
                String.format(Constants.MSG_TASK_NUMBER, tasks.size()));
    }

    public void deleteTask(String inputDesc) throws DukeException {
        int index = checkIndex(inputDesc);
        Task task = tasks.get(index);
        tasks.remove(index);
        DukeUtils.printMessages(
                Constants.MSG_TASK_DELETED,
                "  " + task,
                String.format(Constants.MSG_TASK_NUMBER, tasks.size()));
    }

    public void markTask(String inputDesc) throws DukeException {
        updateTaskStatus(inputDesc, true);
    }

    public void unmarkTask(String inputDesc) throws DukeException {
        updateTaskStatus(inputDesc, false);
    }

    private void updateTaskStatus(String inputDesc, boolean isDone) throws DukeException {
        int index = checkIndex(inputDesc);
        tasks.get(index).setDone(isDone);
        DukeUtils.printMessages(
                String.format(Constants.MSG_TASK_UPDATE_STATUS, isDone ? "done" : "not done"),
                "  " + getTask(index));
    }

    private int checkIndex(String inputDesc) throws DukeException {
        int taskNumber = Integer.parseInt(inputDesc);
        int actualListIndex = taskNumber - 1;
        if (actualListIndex >= tasks.size() || actualListIndex < 0) {
            throw new DukeException(Constants.ERROR_INVALID_NUMBER);
        }
        return actualListIndex;
    }

}
