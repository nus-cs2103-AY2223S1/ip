package duke;

import duke.exception.DukeException;
import duke.exception.InvalidInput;
import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> data;

    public TaskList() {
        this.data = new ArrayList<>();
    }


    public int size() {
        return data.size();
    }

    public Task get(int index) {
        return data.get(index);
    }

    public void add(Task task) throws DukeException {
        data.add(task);
    }

    public void remove(int index) throws DukeException {
        data.remove(index);
    }

    // checks if number input is valid and returns the index of the task
    public int stringIndexToInt(String res) throws InvalidInput {
        if (!res.matches("[0-9]+")) throw new InvalidInput("Input is not a number");
        int target_index = Integer.parseInt(res) - 1;
        if (target_index < 0 || target_index >= data.size()) throw new InvalidInput("Please input a correct number");
        return target_index;
    }

    // marks task as complete or not complete
    public Task setTaskCompletion(String indexString, boolean isComplete) throws DukeException {
        Task task = data.get(stringIndexToInt(indexString));
        if (isComplete) task.markDone();
        else task.markNotDone();
        return task;
    }

    // delete a specified task
    public Task deleteTask(String indexString) throws DukeException {
        int target_index = stringIndexToInt(indexString);
        Task task = data.get(target_index);
        data.remove(target_index);
        return task;
    }

    public ArrayList<? super Task> find(String query) {
        ArrayList<? super Task> res = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            String taskDescription = data.get(i).getDescription();
            if (taskDescription.contains(query)) {
                res.add(data.get(i));
            }
        }
        return res;
    }

    @Override
    public String toString() {
        if (data.size() == 0) return "Nothing here...";
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < data.size(); i++) {
            Task task = data.get(i);
            output.append(i+1).append(". ").append(task).append("\n");
        }
        return output.substring(0, output.length()-1);
    }

}
