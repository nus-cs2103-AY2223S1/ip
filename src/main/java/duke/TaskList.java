package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void delete(int taskNumber, Storage fo) {
        if (taskNumber < 0 || taskNumber >= count()) {
            System.out.println("\tInvalid Task Number!");
        } else {
            Task deletedTask = tasks.remove(taskNumber);
            fo.writeAllTasksToFile(this);
            System.out.println("\tNoted. I have removed the following task:");
            System.out.println("\t\t" + deletedTask);
            System.out.println(getCountInWords());
        }
    }

    public int count() {
        return tasks.size();
    }
    public Task get(int taskNumber) {
        return tasks.get(taskNumber);
    }

    public void markAsDone(int taskNumber, Storage fo) {
        if (taskNumber < 0 || taskNumber >= count()) {
            System.out.println("\tInvalid Task Number!");
        } else {
            get(taskNumber).markAsDone();
            fo.writeAllTasksToFile(this);
            System.out.println("\tAwesome! I have marked this task as done:");
            System.out.println("\t\t" + get(taskNumber));
        }
    }

    public void markAsNotDone(int taskNumber, Storage fo) {
        if (taskNumber < 0 || taskNumber >= count()) {
            System.out.println("\tInvalid Task Number!");
        } else {
            get(taskNumber).markAsNotDone();
            fo.writeAllTasksToFile(this);
            System.out.println("\tAwesome! I have unmarked this task to be not completed:");
            System.out.println("\t\t" + get(taskNumber));
        }
    }
    public void add(Task task, Storage fo) {
        tasks.add(task);
        System.out.println(String.format("\tGotcha. I have added this task:"));

        fo.addTaskToFile(task);
        System.out.println("\t\t" + task); // exploiting polymorphism
        System.out.println(getCountInWords());
    }

    public void listTasks() {
        for (int i = 0; i < count(); i++) {
            System.out.println(String.format("\t%d. %s", i + 1, tasks.get(i)));
        }
    }
    public String getCountInWords() {
        return String.format("\tNow you have %d task%s in the list",
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
    public void find(String keyword) {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                sb.append("\t\t" + task.toString() + "\n");
;            }
        }
        if (sb.toString().equals("")) {
            System.out.println("\tSorry, I could not find any tasks with your keyword :(");
        } else {
            System.out.println("\tHere are your results: ");
            System.out.print(sb);
        }
    }
}
