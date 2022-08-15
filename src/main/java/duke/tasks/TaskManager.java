package duke.tasks;

import duke.DukeException;
import utils.Constants;
import utils.DukeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TaskManager {

    private final List<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void printTasks() {
        DukeUtils.printLine();
        DukeUtils.printWithIndent(Constants.MSG_TASK_LIST);
        IntStream.range(0, tasks.size())
                .forEach(i ->
                        DukeUtils.printWithIndent(String.format("%d.%s", i + 1, tasks.get(i))));
    }

    public void addTask(String inputCommand, String inputDesc) {
        Task task = TaskFactory.createTask(inputCommand, inputDesc);
        tasks.add(task);
        DukeUtils.printMessages(
                Constants.MSG_TASK_ADDED,
                "  " + task,
                String.format(Constants.MSG_TASK_NUMBER, tasks.size()));
    }

    public void markTask(String inputDesc) throws DukeException {
        int index = Integer.parseInt(inputDesc) - 1;
        tasks.get(index).setDone(true);
        DukeUtils.printMessages(Constants.MSG_TASK_MARK, "  " + tasks.get(index));
    }

    public void unmarkTask(String inputDesc) throws DukeException {
        int index = Integer.parseInt(inputDesc) - 1;
        if (index > tasks.size() - 1 || index < 0) {
            throw new DukeException(Constants.ERROR_INVALID_NUMBER);
        }

        tasks.get(index).setDone(false);
        DukeUtils.printMessages(Constants.MSG_TASK_UNMARK, "  " + tasks.get(index));
    }

    public void deleteTask(String inputDesc) throws DukeException {
        int index = Integer.parseInt(inputDesc) - 1;
        if (index > tasks.size() - 1 || index < 0) {
            throw new DukeException(Constants.ERROR_INVALID_NUMBER);
        }

        tasks.remove(index);
        DukeUtils.printMessages(
                Constants.MSG_TASK_DELETED,
                "  " + tasks.get(index),
                String.format(Constants.MSG_TASK_NUMBER, tasks.size()));
    }

}
