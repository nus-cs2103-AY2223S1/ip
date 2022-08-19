import java.util.List;
import java.util.ArrayList;
public class TaskManager {
    private final List<Task> taskList;
    TaskManager() {
        this.taskList = new ArrayList<>();
    }

    public String list() {
        if (taskList.size() == 0) {
            return "\tYou have no tasks in your list."
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\tI have your list of tasks displayed below:\n");
            for (int i = 0; i < this.taskList.size(); i++) {
                stringBuilder.append("\t" + (i + 1) + ") " + taskList.get(i) + "\n");
            }
            return stringBuilder.toString();
        }
    }
    public String addTask(Task task) {
        this.taskList.add(task);
        return ("\t> Added: " + task.getName() + "\n");
    }

    public String mark(int itemNumber) {
        if (itemNumber > 0 && itemNumber <= this.taskList.size()) {
            if (this.taskList.get(itemNumber - 1).getStatus()) {
                return "\tThe task is already marked you dummy.\n";
            } else {
                this.taskList.get(itemNumber - 1).setStatus(true);
                return "\tI've marked this task as done. Good Job!\n";
            }
        } else {
            return "\tThere is no such task!!\n";
        }
    }

    public String unmark(int itemNumber) {
        if (itemNumber > 0 && itemNumber <= this.taskList.size()) {
            if (!(this.taskList.get(itemNumber - 1).getStatus())) {
                return "\tThe task is still not done you idiot.\n";
            } else {
                this.taskList.get(itemNumber - 1).setStatus(false);
                return "\tThe task has been unmarked.\n";
            }
        } else {
            return "\tThere is no such task!!\n";
        }
    }
}