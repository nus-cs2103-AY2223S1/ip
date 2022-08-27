package Duke;
import java.util.ArrayList;

public class Commands {
    private TaskList taskList;
    private Ui ui;

    public Commands() {
        this.ui = new Ui();
        this.taskList = new TaskList();
    }

    public void printList() {
        ui.listTask(taskList.listTasks());
    }

    public void markDone(String input) {
        System.out.println("Nice! I have marked this task as done:");
        int j = Integer.parseInt(input.substring(5)) - 1;
        Task task = taskList.get(j);
        task.markAsDone();
        System.out.println(task.toString());
    }

    public void unmark(String input) {
        System.out.println("This task is marked as not done:");
        int j = Integer.parseInt(input.substring(7)) - 1;
        Task task = taskList.get(j);
        task.markAsNotDone();
        System.out.println(task.toString());
    }

    public void todo(String input) {
        System.out.println("Got it, this task is added in your list:");
        Task todo = new Todo(input.substring(5));
        taskList.add(todo);
        System.out.println(todo.toString());
        ui.printSummary(taskList.size());
    }

    public void deadline(String input) {
        System.out.println("Got it, this task is added in your list:");
        String[] parts = input.split(" ");
        String date = parts[parts.length-2];
        String time = parts[parts.length-1];
        Task dl = new Deadline(input.substring(9, input.indexOf("/") - 1), date, time);
        taskList.add(dl);
        System.out.println(dl.toString());
        ui.printSummary(taskList.size());
    }

    public void event(String input) {
        System.out.println("Got it, this task is added in your list:");
        String[] parts = input.split(" ");
        String at = parts[parts.length-3];
        String date = parts[parts.length-2];
        String time = parts[parts.length-1];
        Task event = new Event(input.substring(6, input.indexOf("/") - 1), at, date, time);
        taskList.add(event);
        System.out.println(event.toString());
        ui.printSummary(taskList.size());
    }

    public void delete(String input) {
        System.out.println("Noted. I've removed this task:");
        int index = Integer.parseInt(input.substring(7)) - 1;
        Task task = taskList.get(index);
        System.out.println(task);
        taskList.remove(index);
        ui.printSummary(taskList.size());
    }

    public void search(String input) {
        ArrayList<Task> matched = new ArrayList<>();
        for(Task t: taskList.listTasks()) {
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
