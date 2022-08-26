package duke.util;

import duke.Duke;
import duke.exception.*;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private static final String TAB = Duke.TAB;
    private static final String EMPTY_LIST_MESSAGE = "The list is empty.";


    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public String getListInfo() {
        int len = tasks.size();
        if (len == 0) {
            return EMPTY_LIST_MESSAGE;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            stringBuilder
                    .append(i + 1)
                    .append(". ")
                    .append(tasks.get(i));
            if (i < len - 1) {
                stringBuilder.append('\n' + TAB);
            }
        }
        return stringBuilder.toString();
    }

    public String addNewTask(Task newTask)
            throws DukeCommandFormatException, DukeTaskTitleMissingException, DukeTaskDateTimeMissingException,
            DukeDateTimeFormatException {
        tasks.add(newTask);
        return "added: " + newTask.toString();
    }

    public String markTaskDone(int index) throws DukeIndexOutOfBoundException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeIndexOutOfBoundException("");
        } else {
            Task targetTask = tasks.get(index);
            targetTask.markDone();
            return targetTask.toString();
        }
    }

    public String markTaskUndone(int index) throws DukeIndexOutOfBoundException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeIndexOutOfBoundException("");
        } else {
            Task targetTask = tasks.get(index);
            targetTask.markUndone();
            return targetTask.toString();
        }
    }

    public String deleteTask(int index) throws DukeIndexOutOfBoundException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeIndexOutOfBoundException("");
        } else {
            Task removedTask = tasks.remove(index);
            return removedTask.toString();
        }
    }

    public String getFileStream() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            stringBuilder
                    .append(tasks.get(i).getFileRepresentation())
                    .append('\n');
        }
        return stringBuilder.toString();
    }

    public String find(String keyword) {
        int len = tasks.size();

        if (len == 0) {
            return "The list is empty.";
        }

        StringBuilder stringBuilder = new StringBuilder();
        int displayIndex = 1;

        for (int i = 0; i < len; i++) {
            Task curr = tasks.get(i);
            if (curr.contains(keyword)) {
                stringBuilder
                        .append('\n' + TAB)
                        .append(displayIndex++)
                        .append(". ")
                        .append(curr);
            }
        }
        return stringBuilder.toString();
    }

    public boolean hasOnlyOneTask() {
        return tasks.size() == 1;
    }

    public int size() {
        return tasks.size();
    }
}
