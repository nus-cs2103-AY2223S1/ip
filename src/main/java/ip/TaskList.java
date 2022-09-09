package ip;

import java.util.ArrayList;
import java.util.List;

class TaskList {
    private final List<Task> taskList;

    TaskList() {
        this(new ArrayList<Task>());
    }

    TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    protected List<Task> getTaskList() {
        return this.taskList;
    }

    protected boolean addTask(Task newTask) throws DukeException {
        return taskList.add(newTask);
    }

    protected String markTask(int index) throws DukeException {
        try {
            Task task = taskList.get(index);
            task.markAsDone();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(String.format("There is no task with index %d", index));
        }
    }

    protected void unmarkTask(int index) throws DukeException {
        try {
            Task task = taskList.get(index);
            task.markAsUndone();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(String.format("There is no task with index %d", index));
        }
    }

    protected void deleteTask(int index) {
        index = index.strip();
        int i = Integer.parseInt(index);
        i--;
        Task task = taskList.remove(i);
        String singulStr = "Now you have 1 task in the list";
        if (taskList.size() == 1) {
            prettyPrint(String.format("%s\n%s   %s\n%s %s",
                    DELETE_MESSAGE, TAB, task.toString(), TAB, singulStr));
        } else {
            prettyPrint(String.format("%s\n%s   %s\n%s Now you have %d tasks in the list.",
                    DELETE_MESSAGE, TAB, task.toString(), TAB, taskList.size()));
        }
    }
}
