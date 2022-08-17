import exceptions.DukeException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

public class TaskList {
    private final Task[] taskList;
    private int size;

    public TaskList() {
        this.taskList = new Task[100];
        this.size = 0;
    }

    public String getRemainingTasks() {
        return String.format("Now you have %d tasks in the list.", size);
    }

    public String addToDo(String taskName, String flag, String additionalValue) throws DukeException {
        Task task = new ToDo(taskName);
        task.checkCommandValidity(taskName, flag, additionalValue);
        taskList[this.size] = task;
        this.size++;
        return "Got it. I've added this task:\n\t" + task + "\n" + getRemainingTasks();
    }

    public String addEvent(String taskName, String flag, String additionalValue) throws DukeException {
        Task task = new Event(taskName, additionalValue);
        task.checkCommandValidity(taskName, flag, additionalValue);
        taskList[this.size] = task;
        this.size++;
        return "Got it. I've added this task:\n\t" + task + "\n" + getRemainingTasks();
    }

    public String addDeadline(String taskName, String flag, String additionalValue) throws DukeException {
        Task task = new Deadline(taskName, additionalValue);
        task.checkCommandValidity(taskName, flag, additionalValue);
        taskList[this.size] = task;
        this.size++;
        return "Got it. I've added this task:\n\t" + task + "\n" + getRemainingTasks();
    }

    public String markTask(String value, boolean isCompleted) throws DukeException {
        try {
            int taskNumber = Integer.parseInt(value);
            Task task = taskList[taskNumber - 1];
            task.mark(isCompleted);
            String message = isCompleted
                    ? "Nice! I've marked this task as done:\n\t"
                    : "Ok, I've marked this task as not done yet:\n\t";
            return message + task;
        } catch (NumberFormatException e) {
            throw new DukeException("Please input a number.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(String.format("Please input a valid number! There are %d tasks remaining.", size));
        }
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= this.taskList.length; i++) {
            Task task = this.taskList[i-1];
            if (task == null) {
                break;
            }
            String numberedTask = i + "." + task + "\n";
            result.append(numberedTask);
        }
        return result.toString();
    }
}
