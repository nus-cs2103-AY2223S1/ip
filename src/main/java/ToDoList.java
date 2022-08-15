import java.util.ArrayList;

public class ToDoList {
    private ArrayList<Task> list;

    public ToDoList() {
        this.list = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.list.add(task);
        System.out.format("added: %s\n", task.toString());
    }

    public void print() {
        for (int i = 0; i < list.size(); i++) {
            System.out.format("%d. %s\n", i + 1, list.get(i).toString());
        }
    }

}
