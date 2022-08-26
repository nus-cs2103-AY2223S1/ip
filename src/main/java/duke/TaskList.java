package duke;

import java.util.ArrayList;

public class TaskList{

    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public void add(Task task) {
        this.getList().add(task);
    }

    public Task get(int index) {
        return this.getList().get(index);
    }

    public void delete(TaskList list, Task task, int index) {
        list.getList().remove(index);
        System.out.println(Ui.DELETE_HEADER + task.toString()
                + "Now you have " + Duke.count + " tasks in the list." + "\n" + Duke.LINE);
    }

    public void list() {
        System.out.println(Ui.TASK_LIST_HEADER);
        for (int i = 0, j = 1; i < Duke.count; i++, j++) {
            System.out.print(j + ". ");
            list.get(i).list();
        }
        System.out.println(Duke.LINE + "\n");
    }

    public void mark(TaskList list, int index) {
        System.out.println(Ui.MARK_HEADER + "[X] " + list.getList().get(index-1).description());
        list.getList().get(index-1).setStatus("[X]");
    }

    public void unmark(TaskList list, int index) {
        System.out.println(Ui.UNMARK_HEADER + "[ ] " + list.getList().get(index-1).description());
        list.getList().get(index-1).setStatus("[ ]");
    }

    public void find(TaskList list, String keyword) {
        System.out.println(Ui.FIND_HEADER);
        Task task = null;
        int find = 1;
        for (int i = 0; i < Duke.count; i++) {
            task = list.get(i);
            String finding = task.getName();
            if (finding.contains(keyword)) {
                System.out.println(find++ +"." + task.toString());
            }
        }
        System.out.println(Duke.LINE);
    }
}
