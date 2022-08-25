import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui = new Ui();

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public void add(Task task) {
        this.tasks.add(task);
        System.out.println("Got it, I've added this task:");
        System.out.println("  " + task.toString());
    }

    public void delete(int index) {
        Task deletedTask = this.tasks.get(index);
        this.tasks.remove(index);
        ui.printMessage("Noted. I've removed this task:");
        ui.printMessage("  " + deletedTask);
    }

    public void markCompleted(int index) {
        tasks.get(index).markCompleted();
        ui.printMessage("Nice! I've marked this task as done:");
        ui.printMessage("  " + tasks.get(index));
    }

    public void markUncompleted(int index) {
        tasks.get(index).markUncompleted();
        ui.printMessage("Alright! I've marked this task as undone:");
        ui.printMessage("  " + tasks.get(index));
    }

    public String toString() {
        String returnString = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            returnString += (i + 1) + "." + tasks.get(i)
                    + ((i != this.tasks.size() - 1) ? "\n" : "");
        }
        return returnString;
    }
}
