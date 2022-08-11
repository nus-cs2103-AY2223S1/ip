import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final List<Task> taskList;

    Storage() {
        this.taskList = new ArrayList<>();
    }

    String handleTaskOutput(Task task, int id) {
        return String.format("%d. %s %s", id, task.getMarkedStatus(), task.description);
    }

    void addTaskToList(Task task) {
        try {
            this.taskList.add(task);
            String taskAddedOutput = String.format("added: %s", task.description);
            System.out.println(taskAddedOutput);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void listTasks() {
        if (taskList.isEmpty()) {
            System.out.println("List is empty!");
        }
        String toPrint = "";

        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            String toConcat = handleTaskOutput(task, i+1);
            toPrint = String.format("%s\n%s", toPrint, toConcat);
        }

        System.out.println(toPrint);
    }

    void markTaskAsDone(int id) {
        try {
            if (id <= 0 || id > taskList.size()) {
                throw new Exception("No such index in the list, please try again.");
            }
            Task targetTask = taskList.get(id-1);
            targetTask.markAsDone();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void markTaskAsUnDone(int id) {
        try {
            if (id <= 0 || id > taskList.size()) {
                throw new Exception("No such index in the list, please try again.");
            }
            Task targetTask = taskList.get(id-1);
            targetTask.markAsUnDone();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
