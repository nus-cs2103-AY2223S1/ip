package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    public int getSize() {
        return taskList.size();
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public void delete(int index) throws DukeException {
        //Index out of bounds
        if (index > taskList.size() || index < 1) {
            throw new DukeException("Index Is Not Valid");
        }
        //get the selected task
        Task task = taskList.get(index - 1);
        //remove the task
        taskList.remove(task);
        //print the response to the user
        Storage.saveTasks(this);
        Ui.printDeletedTask(task, getSize());

    }

    public void mark(int index) throws DukeException {
        //Index out of bounds
        if (index > taskList.size() || index < 1) {
            throw new DukeException("Index Is Not Valid");
        }
        //get the selected task
        Task task = taskList.get(index - 1);
        task.markAsDone();
        Storage.saveTasks(this);
        Ui.printMarkedTask(task);
    }

    public void unmark(int index) throws DukeException {
        //Index out of bounds
        if (index > taskList.size() || index < 1) {
            throw new DukeException("Index Is Not Valid");
        }
        //get the selected task
        Task task = taskList.get(index - 1);
        task.markAsUndone();
        Storage.saveTasks(this);
        Ui.printUnmarkedTask(task);
    }

    public void printSelf() {
        for (int i = 0; i < taskList.size(); ++i) {
            Task task = taskList.get(i);
            System.out.println((i + 1) + ". " + task.toString());
        }
    }

    public String toSaveFileString() {
        String tasks = "";

        for (int i = 0; i < taskList.size(); ++i) {
            tasks += taskList.get(i).toSaveFileString() + "\n";
        }

        return tasks;
    }
}
