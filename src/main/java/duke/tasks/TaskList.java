package duke.tasks;

import duke.exceptions.DukeException;
import duke.exceptions.DukeOutOfRangeException;
import duke.parser.Parser;
import duke.storage.Storage;

import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> tasks;
    protected Storage storage;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<? extends String> data) {
        this.tasks = new ArrayList<>();
        try {
            for (String sentence : data) {
                this.tasks.add(Parser.parseTask(sentence));
            }
        } catch (DukeException e) {
            this.tasks = new ArrayList<>();
        }
    }

    public String showList() {
        String str = "";
        for (int i = 0; i < tasks.size() - 1; i++) {
            str += ((i + 1) + "." + tasks.get(i) + "\n");
        }
        if (tasks.size() != 0) {
            str += (tasks.size() + "." + tasks.get(tasks.size() - 1));
        }
        return str;
    }

    public void markAsDone(int index) throws DukeException {
        if (index < 1 || index > this.tasks.size()) {
            throw new DukeOutOfRangeException(this.tasks.size());
        }
        Task task = this.tasks.get(index - 1);
        task.markAsDone();
    }

    public void markAsNotDone(int index) throws DukeException {
        if (index < 1 || index > this.tasks.size()) {
            throw new DukeOutOfRangeException(this.tasks.size());
        }
        Task task = this.tasks.get(index - 1);
        task.markAsNotDone();
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public void deleteTask(int index) throws DukeException {
        if (index < 1 || index > this.tasks.size()) {
            throw new DukeOutOfRangeException(this.tasks.size());
        }
        Task task = this.tasks.get(index - 1);
        this.tasks.remove(index - 1);
    }

    public String showNumberOfTasks() {
        return "\nNow you have " + this.tasks.size() + " duke.tasks in the list.";
    }

    public String getTask(int index) throws DukeException {
        if (index < 1 || index > this.tasks.size()) {
            throw new DukeOutOfRangeException(this.tasks.size());
        }
        return this.tasks.get(index - 1).toString();
    }

    public ArrayList<Task> toArrayList() {
        return tasks;
    }
}
