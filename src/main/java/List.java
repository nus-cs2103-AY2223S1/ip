import java.util.ArrayList;

public class List {
    private ArrayList<Task> data;

    public List(int size) {
        this.data = new ArrayList<>();
    }

    public void addTask(Task task) {
        data.add(task);
        System.out.println("Got it. I've added this task:\n" +
                " " + task + "\n" +
                "Now you have " + data.size() + " tasks in the list.\n");
    }

    public void markTask (int pos, boolean isDone) {
        Task task = data.get(pos);
        task.mark(isDone);
        System.out.println(
                ( isDone ? "Nice! I've marked this task as done:\n " : "OK, I've marked this task as not done yet:\n ")
                        + task + "\n"
        );
    }
    
    public void deleteTask(int pos) {
        Task task = data.get(pos);
        data.remove(pos);
        System.out.println("Noted. I've removed this task:\n " + 
                task + "\n" +
                "Now you have " + data.size() + " tasks in the list.\n");
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < data.size(); i++) {
            result.append(i + 1).append(".").append(data.get(i)).append("\n");
        }
        return result.toString();
    }
}
