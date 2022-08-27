package duke;

import java.util.ArrayList;
import static java.util.stream.Collectors.joining;

public class TaskList extends ArrayList<Task> {
    public TaskList(ArrayList<Task> taskList) {
        super(taskList);
    }

    public String toSaveData() {
        return this.stream().map(Task::toSaveData).collect(joining("\n"));
    }

    public ArrayList<Task> find(String toFind) {
        return this.stream().filter(task -> task.descriptionContains(toFind)).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }
}
