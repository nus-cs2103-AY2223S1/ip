import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;
    private Ui ui;

    public TaskList() {
        this.taskList = new ArrayList<>();
        this.ui = new Ui();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
        this.ui = new Ui();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public int getTaskListSize() {
        return this.taskList.size();
    }

    public Task getTaskAtIndex(int index) throws DukeException{
        if (index <= 0 || index > getTaskListSize()) {
            throw new DukeException("☹ OOPS!!! The task index is out of range");
        }
        return this.taskList.get(index - 1);
    }

    public void addTask(Task task) {
        this.taskList.add(task);
        String message = "";
        message += "Got it. I've added this task:\n";
        message += "\t" + task.toString() + "\n";
        message += "Now you have " + this.taskList.size() + " tasks in the list.\n";
        ui.printMessage(message);
    }

    public void deleteTask(int index) throws DukeException{
        if (index <= 0 || index > getTaskListSize()) {
            throw new DukeException("☹ OOPS!!! The task index is out of range");
        }
        index = index - 1;
        Task task = this.taskList.remove(index);
        String message = "";
        message += "Noted. I've removed this task:\n";
        message += "\t" + task.toString() + "\n";
        message += "Now you have " + taskList.size() + " tasks in the list.\n";
        ui.printMessage(message);
    }

    public void updateTaskStatus(int index, boolean isMark) throws DukeException{
        if (index <= 0 || index > getTaskListSize()) {
            throw new DukeException("☹ OOPS!!! The task index is out of range");
        }
        String message = "";
        index = index - 1;
        Task task = this.taskList.get(index);
        if (isMark) {
            message += "Nice! I've marked this task as done:\n";
            task.setTaskStatus(true);
        } else {
            message += "OK, I've marked this task as not done yet:\n";
            task.setTaskStatus(false);
        }
        this.taskList.set(index, task);
        message += task.toString() + "\n";
        ui.printMessage(message);
    }

    public void printTaskList() {
        String message = "";
        message += "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i ++) {
            Task task = taskList.get(i);
            message += (i+ 1) + "." + task.toString() + "\n";
        }
        ui.printMessage(message);
    }


}
