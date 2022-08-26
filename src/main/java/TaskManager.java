import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
public class TaskManager {
    private final List<Task> taskList;
    private static final String DATE_FORMAT = "dd/MM/yyyy,HHmm";
    private final DateTimeFormatter dateTimeFormatter;
    TaskManager() {
        this.taskList = new ArrayList<>();
        this.dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
    }

    public String getDateFormat() {
        return DATE_FORMAT;
    }

    public String list() {
        if (taskList.size() == 0) {
            return "\tYou have no tasks in your list.\n";
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\tI have your list of tasks displayed below:\n");
            for (int i = 0; i < this.taskList.size(); i++) {
                stringBuilder.append("\t" + (i + 1) + ") " + taskList.get(i) + "\n");
            }
            return stringBuilder.toString();
        }
    }
    public String addTask(Task task) throws DateTimeParseException {
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

    public String delete(int itemNumber) {
        StringBuilder stringBuilder = new StringBuilder();
        if (itemNumber > 0 && itemNumber <= this.taskList.size()) {
            stringBuilder.append("The following item has been removed.\n");
            stringBuilder.append(this.taskList.remove(itemNumber - 1).toString() + "\n");
            stringBuilder.append("You have " + (this.taskList.size()) + " item(s) remaining.\n");
        } else {
            return "\tThere is no such task!!\n";
        }
        return stringBuilder.toString();
    }
}