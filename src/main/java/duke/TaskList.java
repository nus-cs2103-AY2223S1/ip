package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;
    public TaskList() {
        this.list = new ArrayList<>();
    }

    public ArrayList<Task> getList() {
        return list;
    }

    public void add(Task t) {
        this.getList().add(t);
    }

    public Task get(int index) {
        return this.getList().get(index);
    }

    public void delete(TaskList l, Task b, int index) {
        l.getList().remove(index);
            System.out.println(Ui.DELETE_HEADER + b.toString() +
                    "Now you have " + Duke.count + " tasks in the list." + "\n" + Duke.line);
    }

    public void list() {
        System.out.println(Ui.TASK_LIST_HEADER);

        for (int i = 0, j = 1; i < Duke.count; i++, j++) {
            System.out.print(j + ". ");
            list.get(i).list();
        }

        System.out.println(Duke.line + "\n");
    }

    public void mark(TaskList t, int index) {
        System.out.println(Ui.MARK_HEADER + "[X] " + t.getList().get(index-1).description());
        t.getList().get(index-1).setStatus("[X]");
    }

    public void unmark(TaskList t, int index) {
        System.out.println(Ui.UNMARK_HEADER + "[ ] " + t.getList().get(index-1).description());
        t.getList().get(index-1).setStatus("[ ]");
    }
}
