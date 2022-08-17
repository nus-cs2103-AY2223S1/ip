import java.util.ArrayList;
import java.util.List;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    private static int count = 0;
    private static List<Task> list= new ArrayList<Task>();

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static void mark(int index) {
        Task task = list.get(index - 1);
        task.setDone(true);
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t" + task);
    }

    public static void unmark(int index) {
        Task task = list.get(index - 1);
        task.setDone(false);
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t\t" + task);
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public String getDescription() {
        return this.description;
    }

    public void add() {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + this);
        list.add(this);
        count++;
        printTaskCount();
    }

    public static void delete(int index) {
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t\t" + list.get(index - 1));
        list.remove(index - 1);
        count--;
        printTaskCount();
    }

    public static void printTaskCount() {
        if (count == 1) {
            System.out.println("\tNow you have 1 task in the list.");
        } else {
            System.out.println("\tNow you have " + (count) + " tasks in the list.");
        }
    }

    public static void printList() {
        if (count == 0) {
            System.out.println("\tList is empty!");
        } else {
            System.out.println("\tHere are the tasks in your list:");
            for (int i = 0; i < count; i++) {
                System.out.print("\t\t");
                System.out.print(i + 1);
                System.out.print("." + list.get(i) + "\n");
            }
        }
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + this.getDescription();
    }
}
