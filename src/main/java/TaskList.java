import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public int getSize() {
        return this.taskList.size();
    }

    public String markTask(int index) {
        this.taskList.get(index - 1).markDone();
        return this.taskList.get(index - 1).toString();
    }

    public String unMarkTask(int index) {
        this.taskList.get(index - 1).markUndone();
        return this.taskList.get(index - 1).toString();
    }

    public String addToDo(String description) {
        ToDo todo = new ToDo(description);
        taskList.add(todo);
        return todo.toString();
    }

    public String addDeadline(String description, String returnBy) {
        Deadline deadline = new Deadline(description, returnBy);
        taskList.add(deadline);
        return deadline.toString();
    }

    public String addEvent(String description, String at) {
        Event event = new Event(description, at);
        taskList.add(event);
        return event.toString();
    }

    public String deleteTask(int index) {
        Task toDelete = taskList.remove(index - 1);
        return toDelete.toString();
    }

    @Override
    public String toString() {
        if (this.taskList.size() == 0) {
            return "There are no tasks in your list";
        } else {
            StringBuilder str = new StringBuilder(String.format("There %s %d task(s) in your list:",
                    this.taskList.size() > 1 ? "are" : "is", this.taskList.size()));
            for (int i = 0; i < this.taskList.size(); i++) {
                str.append(String.format("%n%d. %s", i, taskList.get(i)));
            }

            return str.toString();
        }
    }


}
