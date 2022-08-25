import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks = new ArrayList<>();
    private static final String list_message = "Here are the tasks in your list:";
    private static final String add_message = "Got it. I've added this task:";
    private static final String delete_message = "Noted. I've removed this task:";

    TaskList() {

    }
    TaskList (String data) {
        if (!data.equals("")) {
            String[] tasksArray = data.split("\n");
            for (String task : tasksArray) {
                this.tasks.add(stringToTask(task));
            }
        }
    }
    private static Task stringToTask(String input) {
        String [] taskDetails = input.split(",");
        String taskDescription = taskDetails[2];
        boolean completed;
        if (taskDetails[1].equals("1")) {
            completed = true;
        } else {
            completed = false;
        }
        if (taskDetails.length == 4) {
            String time = taskDetails[3];
            LocalDate date = LocalDate.parse(time, DateTimeFormatter.ofPattern("MMM dd yyyy"));
            if (taskDetails[0].equals("D")) {
                return new Deadline(taskDescription, completed, date);
            } else {
                return new Event(taskDescription, completed, date);
            }
        }
        return new Todo(taskDescription, completed);
    }

    private static String taskToString(Task task) {
        String taskDescription = task.description();
        String completed = (task.status()) ? "1" : "0";
        String type = task.toString().substring(1,2);
        String [] splitTime = task.toString().split(":");
        if (splitTime.length == 2) {
            String time = splitTime[1];
            return String.join(",", type, completed, taskDescription, time.substring(1,time.length()-1));
        }
        return String.join(",", type, completed, taskDescription);
    }

    protected void add(Task task) {
        this.tasks.add(task);
        System.out.println(add_message);
        System.out.println(task.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    protected Task del(int ind) {
        Task deleting_task = this.tasks.get(ind);
        this.tasks.remove(ind);
        System.out.println(delete_message);
        System.out.println(deleting_task.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        return deleting_task;
    }

    protected void display() {
        System.out.println(list_message);
        for (int i = 1; i <= this.tasks.size(); i++) {
            System.out.println(i + ". " + this.tasks.get(i - 1).toString());
        }
    }

    protected Task get(int i) {
       return this.tasks.get(i);
    }

    public String toString() {
        String data = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            if (i != 0) {
                data = data + System.lineSeparator();
            }
            data = data + taskToString(this.tasks.get(i));
        }
        return data;
    }
}
