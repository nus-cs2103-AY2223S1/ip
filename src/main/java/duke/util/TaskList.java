package duke.util;

import duke.Duke;
import duke.exception.*;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    public static final String TAB = Duke.TAB;

    private static final String MARK_DONE_OUTPUT_STRING = Duke.MARK_DONE_OUTPUT_STRING;
    private static final String MARK_DONE_ERROR_STRING = Duke.MARK_DONE_ERROR_STRING;
    private static final String MARK_UNDONE_OUTPUT_STRING = Duke.MARK_UNDONE_OUTPUT_STRING;
    private static final String MARK_UNDONE_ERROR_STRING = Duke.MARK_UNDONE_ERROR_STRING;
    private static final String GENERAL_ERROR_STRING = Duke.GENERAL_ERROR_STRING;
    private static final String DELETE_ERROR_STRING = Duke.DELETE_ERROR_STRING;
    private static final String DELETE_OUTPUT_STRING = Duke.DELETE_OUTPUT_STRING;

    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public String getListInfo() {
        int len = tasks.size();
        if (len == 0) {
            return "The list is empty.";
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

    public String addNewTask(String input)
            throws DukeCommandFormatException, DukeTaskTitleMissingException, DukeTaskDateTimeMissingException,
            DukeDateTimeFormatException {
        Task newTask = Task.createFromCommand(input);
        if (newTask == null) {
            return GENERAL_ERROR_STRING;
        }
        tasks.add(newTask);
        return "added: " + newTask.toString();
    }

    public String markTaskDone(int index) throws DukeIndexOutOfBoundException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeIndexOutOfBoundException(MARK_DONE_ERROR_STRING);
        } else {
            Task targetTask = tasks.get(index);
            targetTask.markDone();
            return MARK_DONE_OUTPUT_STRING
                    + "\n"
                    + TAB
                    + targetTask;
        }
    }

    public String markTaskUndone(int index) throws DukeIndexOutOfBoundException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeIndexOutOfBoundException(MARK_UNDONE_ERROR_STRING);
        } else {
            Task targetTask = tasks.get(index);
            targetTask.markUndone();
            return MARK_UNDONE_OUTPUT_STRING
                    + "\n"
                    + TAB
                    + targetTask;
        }
    }

    public String deleteTask(int index) throws DukeIndexOutOfBoundException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeIndexOutOfBoundException(DELETE_ERROR_STRING);
        } else {
            Task removedTask = tasks.remove(index);
            boolean onlyOneTask = tasks.size() == 1;
            return DELETE_OUTPUT_STRING
                    + "\n"
                    + TAB
                    + removedTask
                    + "\n"
                    + TAB
                    + "There "
                    + (onlyOneTask ? "is " : "are ")
                    + tasks.size()
                    + (onlyOneTask ? " task" : " tasks")
                    + " in the list";
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
}
