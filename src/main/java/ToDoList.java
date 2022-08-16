import java.util.ArrayList;

public class ToDoList {
    private ArrayList<Task> list;
    private int numOfTasks = 0;

    public ToDoList() {
        this.list = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.list.add(task);
        this.numOfTasks += 1;
        System.out.format("added: %s\n", task.toString());
        if (this.numOfTasks == 1) {
            System.out.format("Now you have %d task in the list.\n", this.numOfTasks);
        } else {
            System.out.format("Now you have %d tasks in the list.\n", this.numOfTasks);
        }
    }

    public void print() {
        System.out.println("These are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.format("%d. %s\n", i + 1, list.get(i).toString());
        }
    }

    public void mark(int i) {
        Task t = list.get(i - 1);
        t.markDone();
        System.out.println("Nice! I've marked this task as done:\n" + t);
    }

    public void unMark(int i) {
        Task t = list.get(i - 1);
        t.markUndone();
        System.out.println("OK, I've marked this task as not done yet:\n" + t);
    }
}
