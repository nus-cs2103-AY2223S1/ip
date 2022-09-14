package duke;

import java.util.ArrayList;

/**
 * Encapsulates A TaskList that stores the list of tasks given by the user.
 */
public class TaskList {
    private ArrayList<Task> arrayList;

    /**
     * Creates a TaskList object.
     * @param arrayList Array of Tasks that is already given.
     */
    public TaskList(ArrayList<Task> arrayList) {
        this.arrayList = arrayList;
    }

    public TaskList() {
        this.arrayList = new ArrayList<>();
    }

    public Task getTask(int index) {
        return this.arrayList.get(index);
    }

    public int size() {
        return this.arrayList.size();
    }

    /**
     * Adds a new Task object into the array of Tasks.
     * @param task Task to be added into the array.
     */
    public String addTask(Task task) {
        this.arrayList.add(task);
        return String.format("Got it. I've added this task:\n\t%s\n"
                        + "Now you have %d task%s in the list.\n", task, this.arrayList.size(),
                this.arrayList.size() == 1 ? "" : "s");
    }

    /**
     * Deletes an existing Task object from the array of Tasks.
     * @param taskIndex The index of the Task to be deleted.
     */
    public String deleteTask(int taskIndex) {
        Task temp = this.arrayList.remove(taskIndex - 1);
        return String.format(
                "Noted. I've removed this task:\n\t%s\nNow you have %d task%s in the list.",
                temp, this.arrayList.size(), this.arrayList.size() == 1 ? "" : "s");
    }

    /**
     * String representation of the list of tasks currently.
     * @return String representation of the list of tasks currently.
     */
    public String listTasks() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:");

        for (int i = 0; i < this.arrayList.size(); i++) {
            Task currTask = this.arrayList.get(i);
            sb.append(String.format("\n\t%d. %s", i + 1, currTask));
        }
        return sb.toString();
    }

    /**
     * Finds the tasks given by a keyword inputted by the user.
     * @param keyword Keyword used to search for tasks.
     */
    public String findKeyword(String keyword) {
        ArrayList<Integer> tempArray = new ArrayList<>();
        for (int i = 0; i < this.arrayList.size(); i++) {
            if (isFound(keyword, i)) {
                tempArray.add(i);
            }
        }
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:");
        for (int j = 0; j < tempArray.size(); j++) {
            int tempIndex = tempArray.get(j);
            Task foundTask = this.arrayList.get(tempIndex);
            sb.append(String.format("\t%d. %s", j + 1, foundTask));
        }
        return sb.toString();
    }

    public boolean isFound(String keyword, int i) {
        return this.arrayList.get(i).description.toLowerCase()
                .contains(keyword.toLowerCase());
    }
}
