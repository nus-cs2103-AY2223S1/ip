import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> lst;

    public TaskList() {
        this.lst = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> lst) {
        this.lst = lst;
    }

    public ArrayList<Task> getTaskArr() {
        return this.lst;
    }

    public void showList() {
        if (!lst.isEmpty()) {
            Ui.showLine();
            System.out.println("\tHere are the tasks in your list:");
            for (int i = 0; i < lst.size(); i++) {
                System.out.println("\t"+ (i+1) + "." + lst.get(i));
            }
            Ui.showLine();
        } else { // list is empty
            Ui.show("Task list is empty!");
        }
    }

    public boolean isEmpty() {
        return lst.isEmpty();
    }

    public Task get(int index) {
        return lst.get(index);
    }

    public void add(Task t) {
        lst.add(t);
    }

    public int size() {
        return lst.size();
    }

    public void remove(int index) {
        lst.remove(index);
    }

}
