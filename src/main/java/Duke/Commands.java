package Duke;
import java.util.ArrayList;

public class Commands {
    private String input;
    private TaskList taskList;
    private Ui ui;
    private ArrayList<Task> list;

    public Commands(String in) {
        this.input = in;
        this.ui = new Ui();
        this.taskList = new TaskList();
        this.list = taskList.listTasks();
    }

    public void printList() {
        ui.listTask(list);
    }

    public void markDone(String input) {
        System.out.println("Nice! I have marked this task as done:");
        int j = Integer.parseInt(input.substring(5)) - 1;
        Task task = list.get(j);
        task.markAsDone();
        System.out.println(task.toString());
    }

    public void unmark(String input) {
        System.out.println("This task is marked as not done:");
        int j = Integer.parseInt(input.substring(7)) - 1;
        Task task = list.get(j);
        task.markAsNotDone();
        System.out.println(task.toString());
    }

    public void todo(String input) {
        System.out.println("Got it, this task is added in your list:");
        Task todo = new Todo(input.substring(5));
        list.add(todo);
        System.out.println(todo.toString());
        ui.printSummary(list);
    }

    public void deadline(String input) {
        System.out.println("Got it, this task is added in your list:");
        String[] parts = input.split(" ");
        String date = parts[parts.length-2];
        String time = parts[parts.length-1];
        Task dl = new Deadline(input.substring(9, input.indexOf("/") - 1), date, time);
        list.add(dl);
        System.out.println(dl.toString());
        ui.printSummary(list);
    }

    public void event(String input) {
        System.out.println("Got it, this task is added in your list:");
        String[] parts = input.split(" ");
        String at = parts[parts.length-3];
        String date = parts[parts.length-2];
        String time = parts[parts.length-1];
        Task event = new Event(input.substring(6, input.indexOf("/") - 1), at, date, time);
        list.add(event);
        System.out.println(event.toString());
        ui.printSummary(list);
    }

    public void delete(String input) {
        System.out.println("Noted. I've removed this task:");
        int index = Integer.parseInt(input.substring(7)) - 1;
        Task task = list.get(index);
        System.out.println(task);
        list.remove(index);
        ui.printSummary(list);
    }

    public void search(String input) {
        ArrayList<Task> matched = new ArrayList<>();
        for(Task t: list) {
            String str = t.toString();
            if(str.contains(input)) {
                matched.add(t);
            }
        }
        if (matched.isEmpty()) {
            ui.notFound();
        } else {
            ui.printMatchedTasks(matched);
        }
    }
}
