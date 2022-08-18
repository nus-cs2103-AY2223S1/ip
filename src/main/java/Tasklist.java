import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Tasklist {
    private ArrayList<Task> tasks;

    public Tasklist() {
        tasks = new ArrayList<Task>();
    }

    void mark(Scanner words) {
        try {
            int i = words.nextInt() - 1;
            tasks.get(i).mark();
        } catch (NoSuchElementException e) {
            System.out.println("Please specify which task to mark!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("There is no task with that index!");
        }
    }

    void unmark(Scanner words) {
        try {
            int i = words.nextInt() - 1;
            tasks.get(i).unmark();
        } catch (NoSuchElementException e) {
            System.out.println("Please specify which task to unmark!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("There is no task with that index!");
        }
    }

    void add(Task task) {
        try {
            tasks.add(task);
        } catch (Exception e) {
            //TODO: handle exception
        }
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
