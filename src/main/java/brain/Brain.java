package brain;

import java.util.ArrayList;

import task.Task;

/**
 * Represents a space to store <code>Task</code>s.
 */
public class Brain {
    private ArrayList<Task> taskList;

    /**
     * Brain constructor with the specified <code>taskList</code>.
     *
     * @param taskList An <code>ArrayList</code> of <code>Task</code>s
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
     * Returns a <code>Task</code> in the specified index of <code>taskList</code>.
     *
     * @param index index of the <code>Task</code> in <code>taskList</code> to return
     * @return {@link Task}
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Returns a <code>String</code> of <code>Task</code>s inside <code>brain</code>.
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
     * Returns a {@link String} representation of a brain
     *
     * @return String
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

    /**
     * Returns a text representation of <code>Task</code>s in <code>taskList</code>.
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
     * Returns the size of <code>taskList</code>.
     *
     * @return {@link int}
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Returns an Arraylist of Tasks inside the file in the specified path.
     *
     * @param task <code>Task</code> object to add in <code>taskList</code>
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Removes a <code>Task</code> in the specified index of <code>taskList</code>.
     *
     * @param index index of the <code>Task</code> in <code>taskList</code> to remove
     */
    public void remove(int index) {
        taskList.remove(index);
    }

    /**
     * Returns a {@link Brain} instance with the matching {@link Task}s
     *
     * @param keyword a {@link String} that wants to be searched
     * @return Brain
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
}
