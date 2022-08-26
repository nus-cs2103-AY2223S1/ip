package brain;

import java.util.ArrayList;

import task.Task;

public class Brain {
    private ArrayList<Task> taskList;

    public Brain(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public Brain() {
        this.taskList = new ArrayList<Task>();
    }

    public Task get(int index) {
        return taskList.get(index);
    }

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

    public String migrateBrainToTxt() {
        ArrayList<String> txtList = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            txtList.add(task.toTxt());
        }

        return String.join("\n", txtList);
    }

    public int size() {
        return taskList.size();
    }

    public void add(Task task) {
        taskList.add(task);
    }

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
