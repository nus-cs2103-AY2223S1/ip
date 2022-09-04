import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {
    public TaskList(int initialCapacity) {
        super(initialCapacity);
    }

    public TaskList() {
    }

    public ArrayList<String> toStringList() {
        ArrayList<String> stringList = new ArrayList<>();
        for (int i = 0; i < this.size(); i++) {
            stringList.add(this.get(i).toString());
        }
        return stringList;
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < this.size(); i++) {
            int num = i + 1;
            str = str + num + ". " + this.get(i).toString() + "\n";
        }
        return str;
    }
}
