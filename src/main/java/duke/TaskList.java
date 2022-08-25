package duke;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    protected TaskList(ArrayList<Task> taskList) {
        tasks = taskList;
    }

    protected int size() {
        return tasks.size();
    }

    protected ArrayList<Task> getAll() {
        return tasks;
    }

    protected Task get(int index) throws DukeIndexErrorException {
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeIndexErrorException(tasks.size());
        }
    }

    protected void add(Task newTask) {
        tasks.add(newTask);
    }

    protected Task remove(int index) throws DukeIndexErrorException {
        try {
            return tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeIndexErrorException(tasks.size());
        }
    }
}
