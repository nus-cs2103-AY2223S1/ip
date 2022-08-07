import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final List<String> taskList;

    Storage() {
        this.taskList = new ArrayList<>();
    }

    void addTaskToList(String task) {
        try {
            this.taskList.add(task);
            String taskAddedOutput = String.format("added: %s", task);
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
            String toConcat = String.format("%d. %s\n", i+1, taskList.get(i));
            toPrint = String.format("%s%s", toPrint, toConcat);
        }

        System.out.println(toPrint);
    }
}
