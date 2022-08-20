package Duke;

import Tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> lst;

    public TaskList(ArrayList<Task> load) {
        this.lst = load;
    }

    public void addTask(Task t) {
        lst.add(t);
    }

    public Task deleteTask(int i) {
        Task removed = lst.get(i - 1);
        lst.remove(i - 1);
        return removed;
    }

    public Task markTask(int i) {
        Task t = lst.get(i - 1);
        t.setMarked();
        return t;
    }

    public Task unmarkTask(int i) {
        Task t = lst.get(i - 1);
        t.setUnmarked();
        return t;
    }

    public List<Task> findTask(String name) {
        List<Task> foundTasks = new ArrayList<>();
        for (int i = 0; i < lst.size(); i++) {
            Task t = lst.get(i);
            if (t.getTaskName().contains(name)) {
                foundTasks.add(t);
            }
        }
        return foundTasks;
    }

    public int getSize() {
        return lst.size();
    }

    public void printList() {
        System.out.println(Constants.list);
        for (int i = 0; i < lst.size(); i++) {
            System.out.println(String.format("%d.%s", i + 1, lst.get(i).toString()));
        }
    }

    public void printList(List<Task> list) {
        System.out.println(Constants.find);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(String.format("%d.%s", i + 1, list.get(i).toString()));
        }
    }

    public ArrayList<String> stringfy() {
        ArrayList<String> content = new ArrayList<>();
        for (int i = 0; i < lst.size(); i++) {
            Task t = lst.get(i);
            String type = t.toString().substring(1,2);
            if (type.equals("T")) {
                content.add(String.format("%s - %s - %s\n", type, t.getMarkedStatus(), t.getTaskName()));
            } else {
                content.add(String.format("%s - %s - %s - %s\n", type, t.getMarkedStatus(),
                        t.getTaskName(), t.getDate()));
            }
        }
        return content;
    }

}
