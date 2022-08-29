import java.util.ArrayList;

public class TaskList {

    private ArrayList<ListObject> tasksList;
    private static int numberOfTodos;
    private static int numberOfDeadlines;
    private static int numberOfEvents;

    public TaskList() {
        this.tasksList = new ArrayList<>();
    }

    public void addTask(ListObject task) {
        this.tasksList.add(task);
    }

    public int getListLength() {
        return this.tasksList.size();
    }

    public void printList() {
        for (int i = 0; i < this.tasksList.size(); i++) {
            System.out.println(i + ". " + tasksList.get(i).toString());
        }
    }

}