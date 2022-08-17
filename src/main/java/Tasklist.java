import java.util.ArrayList;

public class Tasklist {
    private ArrayList<Task> tasks;

    public Tasklist() {
        tasks = new ArrayList<Task>();
    }

    void mark(int i) {
        tasks.get(i - 1).mark();
    }

    void unmark(int i) {
        tasks.get(i - 1).unmark();
    }

    void add(String s) {
        tasks.add(new Task(s));
    }

    void show() {
        int i = 1;
        for (Task task: tasks) {
            System.out.println(i++ + ". " + task);
        }
    }
}
