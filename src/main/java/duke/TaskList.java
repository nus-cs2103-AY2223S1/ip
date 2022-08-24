package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    List<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    TaskList(List<Task> tasks) throws DukeException {

        this.tasks = tasks;
        if (tasks == null) {
            throw new DukeException("task passed to tasklist is null");
        }
    }

    void add(Task task) {
        this.tasks.add(task);
    }

    Task remove(int i) {
        return this.tasks.remove(i);
    }

    void mark(int i) {
        this.tasks.get(i).mark();
    }

    void unmark(int i) {
        this.tasks.get(i).unMark();
    }

    String find(String word) {
        String filtered = tasks.stream()
                .filter(task ->
                        task.description
                                .toLowerCase()
                                .contains(word.toLowerCase()))
                .map(task -> task.toString())
                .collect(Collectors.joining("\n"));
        return filtered;
    }

    public String toString() {
        String out = "";
        for (Task t : tasks) {
            out += t.toFileString() + "\n";
        }
        return out;
    }
}
