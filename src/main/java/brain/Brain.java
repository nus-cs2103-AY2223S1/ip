package brain;

import java.util.ArrayList;

import task.Task;

/**
 * Represents a space to store {@link Task}s.
 */
public class Brain {
    private ArrayList<Task> taskList;

    /**
     * Brain constructor with the specified <code>taskList</code>.
     *
     * @param taskList An {@link ArrayList} of {@link Task}s
     */
    public Brain(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Brain constructor with the empty <code>taskList</code>
     */
    public Brain() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Returns a {@link Task} in the specified index of <code>taskList</code>.
     *
     * @param index index of the {@link Task} in <code>taskList</code> to return
     * @return {@link Task}
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Adds a task to <code>taskList</code> and returns the {@link String} representation
     * of the task.
     *
     * @param task {@link Task} object to add to <code>taskList</code>
     * @return {@link String} representation of the task
     */
    public String add(Task task) {
        taskList.add(task);

        return task.toString();
    }

    /**
     * Returns the size of <code>taskList</code>.
     *
     * @return {@code int}
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Removes a {@link Task} in the specified index of <code>taskList</code> and
     * returns the {@link String} representation of the task.
     *
     * @param index index of the {@link Task} in <code>taskList</code> to remove
     * @return {@link String} representation of the task
     */
    public String remove(int index) {
        Task task = taskList.get(index);
        taskList.remove(index);

        return task.toString();
    }

    /**
     * Returns a {@link String} of {@link Task}s inside <code>brain</code>.
     *
     * @return {@link String}
     */
    public String show() {
        ArrayList<String> result = new ArrayList<>();
        result.add("Abrakadabraaa! Here's what's inside Tob Tob's Brain:");
        result.add(toString());

        return String.join("\n", result);
    }

    /**
     * Returns a text representation of {@link Task}s in <code>taskList</code>.
     *
     * @return {@link String}
     */
    public String migrateBrainToTxt() {
        ArrayList<String> txtList = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            txtList.add(task.toTxt());
        }

        return String.join("\n", txtList);
    }

    /**
     * Returns a {@link Brain} instance with the matching {@link Task}s
     *
     * @param keyword a {@link String} that wants to be searched
     * @return {@link Brain}
     */
    public Brain findInTask(String keyword) {
        ArrayList<Task> tempTaskList = new ArrayList<>();
        for (Task task: taskList) {
            if (task.hasKeyword(keyword)) {
                tempTaskList.add(task);
            }
        }
        return new Brain(tempTaskList);
    }

    /**
     * Returns a {@link String} representation of a <code>brain</code>.
     *
     * @return {@link String}
     */
    @Override
    public String toString() {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            result.add(String.format("%s.%s", i + 1, task.toString()));
        }

        return String.join("\n", result);
    }
}
