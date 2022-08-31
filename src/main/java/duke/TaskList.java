package duke;

import duke.task.Task;

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

    public Task getTaskAtIndex(int index) throws DukeException {
        if (index <= 0 || index > getTaskListSize()) {
            throw new DukeException("☹ OOPS!!! The task index is out of range");
        }
        return this.taskList.get(index - 1);
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public Task deleteTask(int index) throws DukeException{
        if (index <= 0 || index > getTaskListSize()) {
            throw new DukeException("☹ OOPS!!! The task index is out of range");
        }
        index = index - 1;
        Task task = this.taskList.remove(index);
        return task;
    }

    public Task updateTaskStatus(int index, boolean isMark) throws DukeException {
        if (index <= 0 || index > getTaskListSize()) {
            throw new DukeException("☹ OOPS!!! The task index is out of range");
        }
        index = index - 1;
        Task task = this.taskList.get(index);
        if (isMark) {
            task.setTaskStatus(true);
        } else {
            task.setTaskStatus(false);
        }
        this.taskList.set(index, task);
        return task;
    }

    public String printTaskList() {
        String message = "";
        for (int i = 0; i < taskList.size(); i ++) {
            Task task = taskList.get(i);
            message += (i+ 1) + "." + task.toString() + "\n";
        }
        return message;
    }


}
