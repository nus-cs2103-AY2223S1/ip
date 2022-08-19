package duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.DukeException;

public class TaskList {
    private static String NO_SUCH_INDEX = "No such index in the list, please try again.";
    private static String NO_TASKS_LEFT = "List is empty, 0 items left !";
    private final List<Task> taskList;


    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    private String handleTaskOutput(Task task, int id) {
        return String.format("%d. %s", id, task.toString());
    }

    public List<Task> getTaskList() {
        return this.taskList;
    }

    private String getItemsLeft() {
        if (taskList.isEmpty()) {
            return NO_TASKS_LEFT;
        } else {
            return String.format("Now you have %d tasks in the list", taskList.size());
        }
    }

    public TaskList findMatchingTasks(String query) {
        List<Task> result = this.taskList.stream().filter(item -> item.getDescription().contains(query))
                .collect(Collectors.toList());
        TaskList filteredTasks = new TaskList(result);

        return filteredTasks;
    }

    public void removeTaskFromList(int id) {
        try {
            if (id <= 0 || id > taskList.size()) {
                throw new DukeException(NO_SUCH_INDEX);
            }

            Task taskToRemove = this.taskList.get(id - 1);
            this.taskList.remove(id - 1);
            String taskRemovedOutput = String.format("Noted. I've removed this task:\n %s\n%s\n",
                    taskToRemove.toString(), getItemsLeft());
            System.out.println(taskRemovedOutput);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addTaskToList(Task task) {
        try {
            this.taskList.add(task);
            String taskAddedOutput = String.format("Got it. I've added this task:\n  %s\n%s\n",
                    task.toString(), getItemsLeft());
            System.out.println(taskAddedOutput);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void listTasks() {
        if (taskList.isEmpty()) {
            System.out.println(NO_TASKS_LEFT);
            return;
        }
        String toPrint = "";

        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            String toConcat = handleTaskOutput(task, i + 1);
            toPrint = String.format("%s\n%s", toPrint, toConcat);
        }

        System.out.println(toPrint.substring(1) + "\n");
    }

    public void markTaskAsDone(int id) {
        try {
            if (id <= 0 || id > taskList.size()) {
                throw new DukeException(NO_SUCH_INDEX);
            }
            Task targetTask = taskList.get(id - 1);
            targetTask.markAsDone(false);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void markTaskAsUnDone(int id) {
        try {
            if (id <= 0 || id > taskList.size()) {
                throw new DukeException(NO_SUCH_INDEX);
            }
            Task targetTask = taskList.get(id - 1);
            targetTask.markAsUnDone();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String getAddedTaskOutput() {
        Task lastAddedTask = new Task("", TaskType.TASK);
        if (!taskList.isEmpty()) {
            lastAddedTask = taskList.get(taskList.size() - 1);
            return String.format("Got it. I've added this task:\n  %s\n%s\n",
                    lastAddedTask.toString(), getItemsLeft());
        }
        return NO_TASKS_LEFT;
    }
}
