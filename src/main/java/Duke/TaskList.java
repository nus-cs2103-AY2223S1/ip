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

    public String find(String keyword) {
        int counter = 1;
        String list = "";
        for (Task task : tasks) {
            if (task.description.contains(keyword)) {
                list = list + " " + counter + ": " + task.toString() + "\n";
                counter += 1;
            }
        }
        String findMessage = " Here are the matching tasks in your list:\n" + list;
        return findMessage.trim();
    }

    @Override
    public String toString() {
        String list = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            list = list + " " + (i + 1) + ": " + tasks.get(i).toString() + "\n";
        }
        String listMessage = " Here are the tasks in your list:\n" + list;
        return listMessage.trim();
    }
}
