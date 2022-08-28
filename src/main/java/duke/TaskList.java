package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> list = new ArrayList<>();

    public TaskList(List<Task> list) {
        this.list = list;
    }

    public List<Task> getList() {
        return list;
    }

    public void deleteTask(int number) {
        Task task = this.list.get(number - 1);
        this.list.remove(task);
    }

    public void addTask(Task task) {
        this.list.add(task);
    }

    public int getListSize() {
        return this.list.size();
    }

    public String getAllTask() {
        String text = "";
        for (int i = 0; i < list.size(); i++) {

            if(i == list.size() - 1) {
                text += i + 1 + ". " + list.get(i);
            } else {
                text += i + 1 + ". " + list.get(i) +"\n     ";
            }
        }
        return text;
    }

    public Task getTask(int number) {
        return this.list.get(number - 1);
    }
}
