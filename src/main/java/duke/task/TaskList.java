package duke.task;

import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
    }

    public TaskList(List<Task> Tasks) {
        this.tasks = Tasks;
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public Task remove(int i) {
        return tasks.remove(i);
    }

    public int size() {
        return tasks.size();
    }

    public String getString() {
        String newText = "";
        for (int i = 0; i < tasks.size(); i++) {
            String s = tasks.get(i).getStringToSave();
            if (i == 0) {
                newText += s;
            } else {
                newText += '\n' + s;
            }
        }
        return newText;
    }
}
