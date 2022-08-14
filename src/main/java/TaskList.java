import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {
    @Override
    public Task get(int entry) {
        if (this.inRange(entry)) {
            return super.get(entry - 1);
        }
        return null;
    }

    public void add(String task) {
        this.add(new Task(task));
    }

    public boolean inRange(int entry) {
        return entry > 0 && entry <= this.size();
    }

    public void markTask(int entry) {
        if (this.inRange(entry)) {
            this.get(entry).mark();
        }
    }

    public void unmarkTask(int entry) {
        if (this.inRange(entry)) {
            this.get(entry).unmark();
        }
    }

    @Override
    public String toString() {
        String result = "";

        for (int entry = 1; entry < this.size() + 1; entry++) {
            result += "\t" + entry + "." + this.get(entry).toString() + "\n";
        }

        return result;
    }
}
