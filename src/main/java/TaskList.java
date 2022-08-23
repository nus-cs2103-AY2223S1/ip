import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public void addTodo(String s, Duke d) {
        Todo t = new Todo(s);
        tasks.add(t);
        System.out.println("Successfully added: " + t);
        System.out.println("You have " + tasks.size() + " tasks in the list now");
    }

    public void addDeadline(String s, LocalDate d, Duke duke) {
        Deadline deadline = new Deadline(s, d);
        tasks.add(deadline);
        System.out.println("Successfully added: " + deadline);
        System.out.println("You have " + tasks.size() + " tasks in the list now");
    }

    public void addDeadline(String s, String d, Duke duke) {
        Deadline deadline = new Deadline(s, d);
        tasks.add(deadline);
        System.out.println("Successfully added: " + deadline);
        System.out.println("You have " + tasks.size() + " tasks in the list now");
    }

    public void addEvent(String s, LocalDate time, Duke d) {
        Event event = new Event(s, time);
        tasks.add(event);
        System.out.println("Successfully added: " + event);
        System.out.println("You have " + tasks.size() + " tasks in the list now");
    }

    public void addEvent(String s, String time, Duke d) {
        Event event = new Event(s, time);
        tasks.add(event);
        System.out.println("Successfully added: " + event);
        System.out.println("You have " + tasks.size() + " tasks in the list now");
    }

    public void getList() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        System.out.println("There are " + tasks.size() + " tasks in the list.");
    }

    public void deleteTask(int index, Duke d) {
        try {
            if (index >= tasks.size() || index < 0) {
                System.out.println("I cannot delete a task that does not exist!");
            } else {
                Task t = tasks.get(index);
                tasks.remove(t);
                System.out.println("Successfully deleted: " + t);
                System.out.println("You have " + tasks.size() + " tasks in the list now");
            }
        } catch (NumberFormatException e) {
            System.out.println("I cannot delete a task that does not exist!");
        }
    }

    public void markTask(int i) {
        try {
            Task t = this.tasks.get(i);
            t.markTask();
        } catch (IndexOutOfBoundsException e) {
            System.err.println("I cannot mark a task that does not exist!");
        }
    }

    public void unmarkTask(int i) {
        try {
            Task t = this.tasks.get(i);
            t.unmarkTask();
        } catch (IndexOutOfBoundsException e) {
            System.err.println("I cannot mark a task that does not exist!");
        }
    }
}
