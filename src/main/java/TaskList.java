import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public boolean addTask(Task task){
        return this.list.add(task);
    }

    public String markDone(int index) {
        this.list.get(index).markDone();
        return this.list.get(index).toString();
    }

    public String unmarkDone(int index) {
        this.list.get(index).unmarkDone();
        return this.list.get(index).toString();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("     Here are the tasks in your list:\n");
        for (int i = 0; i < this.list.size(); i++) {
            stringBuilder.append(String.format("     %d.%s", i + 1, this.list.get(i).toString()));
            if (i < this.list.size() - 1) {
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }
}
