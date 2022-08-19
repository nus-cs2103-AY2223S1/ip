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

    public void printList() {
        System.out.println("Here are the tasks in your list:\n");
        int ListLength = taskList.size();
        for (int i = 0; i < ListLength; i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
    }


}
