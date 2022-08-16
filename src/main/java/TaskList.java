import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public void addItem(Task item) {
        taskList.add(item);
        System.out.println("OK, I've added the following task:\n  " + item);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public void markItem(int index) throws DukeException {
        try {
            taskList.get(index).setDone(true);
            System.out.println("Nice! I've marked this task as done:\n  " + taskList.get(index));
        } catch (IndexOutOfBoundsException exception) {
            throw new DukeException("☹ OOPS!!! No such task exists :(");
        }
    }

    public void unmarkItem(int index) throws DukeException {
        try {
            taskList.get(index).setDone(false);
            System.out.println("OK, I've marked this task as not done yet:\n  " + taskList.get(index));
        } catch (IndexOutOfBoundsException exception) {
            throw new DukeException("☹ OOPS!!! No such task exists :(");
        }
    }

    public void deleteItem(int index) throws DukeException {
        try {
            System.out.println("Noted. I've removed this task:\n  " + taskList.get(index));
            taskList.remove(index);
        } catch (IndexOutOfBoundsException exception) {
            throw new DukeException("☹ OOPS!!! No such task exists :(");
        }
    }

    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i += 1) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
    }
}
