import javax.swing.plaf.synth.SynthTextAreaUI;
import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
       this.taskList = new ArrayList<>();
    }

    public void addTask(Task t) {
        taskList.add(t);
        String reply = "Got it. I've added this task:\n" +
                        t + "\nNow you have " + this.taskList.size() + " tasks in the list.";
        System.out.println(reply);
    }

    public Task getTask(int taskNo) {
        return taskList.get(taskNo - 1);
    }

    public void markTask(int taskNo) {
        Task task = this.getTask(taskNo);
        task.mark();
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    public void unMarkTask(int taskNo) {
        Task task = this.getTask(taskNo);
        task.unMark();
        System.out.println("OK, I've marked this task as not done yet:\n" + task);
    }

    public void deleteTask(int taskNo) {
        Task t = this.getTask(taskNo);
        this.taskList.remove(taskNo - 1);
        String reply = "Noted. I've removed this task:\n" +
                        t + "\nNow you have " + this.taskList.size() + " tasks in the list.";
        System.out.println(reply);
    }

    public void printTasksOnSpecificDate(LocalDate date) {
        System.out.println("Here are the tasks on: " + date + "\n");
        int ListLength = taskList.size();
        for (int i = 0; i < ListLength; i++) {
            Task task = taskList.get(i);
            if (this.getTaskType(task) == "Deadline") {
                Deadlines d = (Deadlines) task;
                if (d.getDate().equals(date)) {
                    System.out.println(d);
                }
            } else if (this.getTaskType(taskList.get(i)) == "Event") {
                Events e = (Events) task;
                if (e.getDate().equals(date)) {
                    System.out.println(e);
                }
            }
        }
    }
    

    public String getTaskType(Task task) {
        if (task instanceof Deadlines) {
            return "Deadline";
        } else if (task instanceof Events) {
            return "Event";
        } else if (task instanceof  ToDos) {
            return "ToDo";
        }
        return "";
    }

    public void printList() {
        System.out.println("Here are the tasks in your list:\n");
        int ListLength = taskList.size();
        for (int i = 0; i < ListLength; i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
    }


}
