package Duke;

import java.util.ArrayList;

/**
 * Class that contains the task list e.g., it has operations to add/delete tasks in the list
 */

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public int getTaskListSize() {
        return this.tasks.size();
    }

    public void addTask(Task task) throws DukeException {
        if (task instanceof Todo) {
            Todo todo = (Todo) task;
            this.tasks.add(todo);
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            this.tasks.add(deadline);
        } else if (task instanceof Event) {
            Event event = (Event) task;
            this.tasks.add(event);
        } else {
            throw new DukeException("Invalid task encountered !!");
        }
    }

    @Override
    public String toString() {
        String list = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            if (i == tasks.size() - 1) {
                list = list + " " + (i + 1) + ": " + tasks.get(i).toString();
                break;
            }
            list = list + " " + (i + 1) + ": " + tasks.get(i).toString() + "\n";
        }
        String listMessage = " Here are the tasks in your list:\n" + list;
        return listMessage;
    }
}
