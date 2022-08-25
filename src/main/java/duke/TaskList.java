package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<String> tasks) {
        this.taskList = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i ++) {
            taskList.add(Task.loadStringToTask(tasks.get(i)));
        }
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

    public ArrayList<String> getTaskList() {
        ArrayList<String> taskListInString = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i ++) {
            taskListInString.add(taskList.get(i).saveStringFormat());
        }
        return taskListInString;
    }

    public String addDeadline(String description, LocalDate returnBy) {
        Deadline deadline = new Deadline(description, returnBy);
        taskList.add(deadline);
        return deadline.toString();
    }

    public String addEvent(String description, LocalDate at) {
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
            StringBuilder str = new StringBuilder(String.format("There %s %d duke task(s) in your list:"
                    ,this.taskList.size() > 1 ? "are" : "is", this.taskList.size()));
            for (int i = 1; i <= this.taskList.size(); i++) {
                str.append(String.format("%n%d. %s", i, taskList.get(i - 1)));
            }

            return str.toString();
        }
    }


}
