import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void showList() {
        drawLine();
        indentMessage("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            indentMessage((i + 1) + "." + tasks.get(i).toString());
        }
        drawLine();
    }

    public void indentMessage(String msg) {
        Duke.indentMessage(msg);
    }

    public void drawLine() {
        Duke.drawLine();
    }

}
