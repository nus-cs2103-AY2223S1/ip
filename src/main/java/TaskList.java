import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> list = new ArrayList<>();

    public void add(String str) {
        this.list.add(new Task(str));
    }

    @Override
    public String toString() {
        String res = TextFormatter.formatLine("Here are the tasks in your list:");
        for (int i = 0; i < this.list.size(); i++) {
            String nextListItem = String.format("%d.%s", i + 1, this.list.get(i));
            res += TextFormatter.formatLine(nextListItem);
        }
        return res;
    }

    public String markItem(int index) {
        return this.list.get(index - 1).setIsMarked(true);
    }

    public String unmarkItem(int index) {
        return this.list.get(index - 1).setIsMarked(false);
    }
}

