import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskArrayList;
    private int index;

    public TaskList() {
        this.taskArrayList = new ArrayList<>(100);
        this.index = 0;
    }

    public TaskList(ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
        this.index = taskArrayList.size();
    }

    private String taskString() {
        if (this.index <= 1) {
            return " task ";
        } else {
            return " tasks ";
        }
    }

    public void printList() {
        ArrayList<Task> list = this.taskArrayList;
        list.forEach(t -> System.out.println(list.indexOf(t) + 1 + ". " + list.get(list.indexOf(t)).toString()));
    }

    public void add(Task t) {
        this.taskArrayList.add(t);
        this.index++;
    }

    public void delete(int i) {
        this.index--;
        this.taskArrayList.remove(i - 1);
    }

    public void mark(int i) {
        ArrayList<Task> list = this.taskArrayList;
        Task t = list.get(i - 1);
        t.mark();
    }

    public void unmark(int i) {
        ArrayList<Task> list = this.taskArrayList;
        Task t = list.get(i - 1);
        t.unmark();
    }

    public ArrayList<Task> getTaskArrayList() {
        return this.taskArrayList;
    }
}
