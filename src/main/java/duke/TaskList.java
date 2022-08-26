package duke;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void deleteTask(int taskNumber) {
        Task t = tasks.get(taskNumber);
        tasks.remove(taskNumber);
        System.out.println("     Ok! I have removed the following task!:\n"
                + "       " + t.toString() + "\n"
                + "     You now have a total of " + tasks.size() + " tasks!");
    }

    public void addTask(Task t) {
        tasks.add(t);
        System.out.println("     Ok! I have added the following "
                + ((t instanceof Todo)
                        ? "Todo "
                        : (t instanceof Event)
                        ? "Event "
                        : "Deadline ")
                + "task!:\n"
                + "       " + t.toString() + "\n"
                + "     You now have a total of " + tasks.size() + " tasks!");
    }

    public static void main(String[] args) {
        TaskList tl = new TaskList();
        Task t = new Todo("read books");
        tl.addTask((t));
        tl.addTask(new Deadline("return books", "2022-10-28 18:00"));
        tl.addTask(new Event("attend wedding", "2022-12-20"));
    }
}
