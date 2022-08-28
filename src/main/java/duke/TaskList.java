package duke;

import duke.exception.DukeIndexErrorException;
import duke.task.Task;

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

    protected ArrayList<Task> find(String[] keywords) {
        ArrayList<Task> results = new ArrayList<>();
        for (Task task : tasks) {
            if (containsAllKeywords(task, keywords)) {
                results.add(task);
            }
        }
        return results;
    }

    private boolean containsAllKeywords(Task task, String[] keywords) {
        String description = task.getDescription();
        for (String keyword : keywords)
            if (!description.contains(keyword)) {
                return false;
            }
        return true;
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
