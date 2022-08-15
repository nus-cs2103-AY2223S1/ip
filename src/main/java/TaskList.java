import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public String addTask(Task task) {
        this.list.add(task);
        StringBuilder stringBuilder = new StringBuilder("     Got it. I've added this task:\n        ");
        stringBuilder.append(task);
        stringBuilder.append(String.format("\n     Now you have %d tasks in the list.", this.list.size()));
        return stringBuilder.toString();
    }

    public String removeTask(int index) {
        Task task = this.list.remove(index);
        StringBuilder stringBuilder = new StringBuilder("     Noted. I've removed this task:\n        ");
        stringBuilder.append(task);
        stringBuilder.append(String.format("\n     Now you have %d tasks in the list.", this.list.size()));
        return stringBuilder.toString();
    }

    public String markDone(int index) {
        this.list.get(index).markDone();
        return this.list.get(index).toString();
    }

    public String unmarkDone(int index) {
        this.list.get(index).markNotDone();
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
