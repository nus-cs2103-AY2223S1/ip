package duke;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
    public int size() {
        return taskList.size();
    }
    public Task getTask(int index) {
        return taskList.get(index);
    }
    public void addTask(Task task) {
        taskList.add(task);
    }
    public Task deleteTask(int num) throws DukeException {
        if (num > taskList.size() || num <= 0) {
            throw new DukeException("");
        }
        return taskList.remove(num - 1);
    }

    public void mark(int number) throws DukeException {
        if (number > taskList.size() || number <= 0) {
            throw new DukeException("");
        }
        taskList.get(number - 1).setDone();
    }

    public void unMark(int number) throws DukeException {
        if (number > taskList.size() || number <= 0) {
            throw new DukeException("");
        }
        taskList.get(number - 1).setUnDone();
    }
    public String taskListToText() {
        StringBuilder lines = new StringBuilder();
        for (Task task : taskList) {
            lines.append(task.toLine()).append("\n");
        }
        return lines.toString();
    }

    /**
     * Returns a list of tasks that contains the keyword.
     * @param keyWord Keyword that is being searched for.
     * @return A taskList containing tasks that contains the keyword.
     * @throws DukeException If the new taskList is empty.
     */
    public TaskList find(String keyWord) throws DukeException {
        ArrayList<Task> containingKeyWordList = new ArrayList<>();
        for (Task t : taskList) {
            if (t.contains(keyWord)) {
                containingKeyWordList.add(t);
            }
        }
        if (containingKeyWordList.isEmpty()) {
            throw new DukeException("");
        }
        return new TaskList(containingKeyWordList);
    }
}
