package duke.util;

import duke.exception.DukeException;
import duke.exception.FileParseException;
import duke.task.Task;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> store;

    public TaskList(ArrayList<String> fileInput) {
        this.store = new ArrayList<>();
        Parser p = new Parser(this);
        if (!fileInput.isEmpty()) {
            for (String line : fileInput) {
                try {
                    p.parseInput(line, true);
                } catch (FileParseException e) {
                    Ui.corruptedLine(e);
                } catch (DukeException e) {
                    Ui.unknownError();
                }
            }
        }
    }

    public TaskList() {
        this.store = new ArrayList<>();
    }

    public ArrayList<Task> getArray() {
        return this.store;
    }

    public Task markAsDone(int i) {
        Task task = this.store.get(i);
        task.isDone = true;
        return task;
    }

    public Task markAsNotDone(int i) {
        Task task = this.store.get(i);
        task.isDone = false;
        return task;
    }

    public Task delete(int i) {
        return this.store.remove(i);
    }

    public void add(Task task) {
        this.store.add(task);
    }

    public int getSize() {
        return this.store.size();
    }

    public ArrayList<Task> searchFor(String keyword) {
        ArrayList<Task> output = new ArrayList<>();
        for (Task t : this.store) {
            if (t.getDescription().contains(keyword)) {
                output.add(t);
            }
        }
        return output;
    }

    public Task lastTaskAdded() {
        return this.store.get(this.store.size() - 1);
    }

}
