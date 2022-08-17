import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void showList() {
        this.drawLine();
        this.indentMessage("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            this.indentMessage((i + 1) + "." + tasks.get(i).toString());
        }
        this.drawLine();
    }

    public void markAsDone(int index) {
        if (index < 1 || index > this.tasks.size()) {
            this.showTaskNotFoundMessage();
        }
        Task task = this.tasks.get(index - 1);
        task.markAsDone();
        this.drawLine();
        this.indentMessage("Nice! I've marked this task as done:");
        this.indentMessage("  " + task);
        this.drawLine();
    }

    public void markAsNotDone(int index) {
        if (index < 1 || index > this.tasks.size()) {
            this.showTaskNotFoundMessage();
        }
        Task task = this.tasks.get(index - 1);
        task.markAsNotDone();
        this.drawLine();
        this.indentMessage("OK, I've marked this task as not done yet:");
        this.indentMessage("  " + task);
        this.drawLine();
    }

    public void showTaskNotFoundMessage() {
        this.drawLine();
        this.indentMessage("Oops! Looks like your number is out of range");
        this.indentMessage("You can only mark/unmark a task between 0 to " + this.tasks.size());
        this.drawLine();
    }
    public void indentMessage(String msg) {
        Duke.indentMessage(msg);
    }

    public void drawLine() {
        Duke.drawLine();
    }

    public void addToList(String msg) {
        Task task = new Task(msg);
        this.tasks.add(task);

        this.drawLine();
        this.indentMessage("Task succesfully added!");
        this.indentMessage("  " + task);
        this.drawLine();
    }

}
