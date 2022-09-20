package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public String delete(int taskNumber, Storage fo) {
        if (taskNumber < 0 || taskNumber >= count()) {
            return "Invalid Task Number!";
        } else {
            Task deletedTask = tasks.remove(taskNumber);
            fo.writeAllTasksToFile(this);
            StringBuilder sb = new StringBuilder();
            sb.append("Noted. I have removed the following task:\n");
            sb.append("\t" + deletedTask + "\n");
            sb.append(getCountInWords());
            return sb.toString();
        }
    }

    public int count() {
        return tasks.size();
    }

    public Task get(int taskNumber) {
        return tasks.get(taskNumber);
    }

    public String markAsDone(int taskNumber, Storage fo) {
        if (taskNumber < 0 || taskNumber >= count()) {
            return "Invalid Task Number!";
        } else {
            get(taskNumber).markAsDone();
            fo.writeAllTasksToFile(this);
            StringBuilder sb = new StringBuilder();
            sb.append("Awesome! I have marked this task as done:");
            sb.append(get(taskNumber));
            return sb.toString();
        }
    }

    public String markAsNotDone(int taskNumber, Storage fo) {
        if (taskNumber < 0 || taskNumber >= count()) {
            return "Invalid Task Number!";
        } else {
            get(taskNumber).markAsNotDone();
            fo.writeAllTasksToFile(this);
            StringBuilder sb = new StringBuilder();
            sb.append("Awesome! I have unmarked this task to be not completed:\n");
            sb.append("\t" + get(taskNumber));
            return sb.toString();
        }
    }

    public String add(Task task, Storage fo) {
        if (isDuplicate(task)) {
            return "You already have this task in your list :)";
        }
        tasks.add(task);
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Gotcha. I have added this task:\n"));

        fo.addTaskToFile(task);
        sb.append("\t" + task + "\n"); // exploiting polymorphism
        sb.append(getCountInWords());
        return sb.toString();
    }

    public String listTasks() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are your tasks:\n");
        for (int i = 0; i < count(); i++) {
            sb.append(String.format("\t%d. %s\n", i + 1, tasks.get(i)));
        }
        return sb.toString();
    }

    public String getCountInWords() {
        return String.format("Now you have %d task%s in the list",
                count(), count() > 1 ? "s" : "");
    }

    public void addTasks(ArrayList<Task> tasks) {
        this.tasks.addAll(tasks);
    }

    /**
     * Lists all tasks in which keyword occurs in the description.
     *
     * @param keyword The keyword to search in the task description.
     */
    public String find(String keyword) {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                sb.append("\t" + task.toString() + "\n");
                ;
            }
        }
        if (sb.toString().equals("")) {
            return "Sorry, I could not find any tasks with your keyword :(";
        } else {
            sb.insert(0, "Here are your results: \n");
            return sb.toString();
        }
    }

    /**
     * Checks whether a duplicate task already exists in the tasks list.
     * Two tasks are said to be identical if they have the same string representation.
     *
     * @param task
     * @return
     */
    public boolean isDuplicate(Task task) {
        for (Task temp : tasks) {
            if (task.toString().equals(temp.toString())) {
                return true;
            }
        }
        return false;
    }
}
