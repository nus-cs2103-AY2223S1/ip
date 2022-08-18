import java.util.ArrayList;
import java.util.Scanner;

public class Tasklist {
    private ArrayList<Task> tasks;

    public Tasklist() {
        tasks = new ArrayList<Task>();
    }

    void mark(Scanner words) throws MissingOptions, InvalidIndex {
        if (words.hasNextInt()) {
            int i = words.nextInt();
            if (i <= tasks.size() && i >= 1) {
                tasks.get(i - 1).mark();
            } else {
                throw new InvalidIndex(i);
            }
        } else {
            throw new MissingOptions("mark");
        }
    }

    void unmark(Scanner words) throws MissingOptions, InvalidIndex {
        if (words.hasNextInt()) {
            int i = words.nextInt();
            if (i <= tasks.size() && i >= 1) {
                tasks.get(i - 1).unmark();
            } else {
                throw new InvalidIndex(i);
            }
        } else {
            throw new MissingOptions("unmark");
        }
    }

    void add(Task task) {
        tasks.add(task);
    }

    void show() {
        int i = 1;
        System.out.println("---");
        for (Task task: tasks) {
            System.out.println(i++ + ". " + task);
        }
        System.out.println("---");
    }
}
