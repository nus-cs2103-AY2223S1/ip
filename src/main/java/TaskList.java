import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> arrayList;

    public TaskList(ArrayList<Task> arrayList) {
        this.arrayList = arrayList;
    }

    public Task getTask(int index) {
        return this.arrayList.get(index);
    }

    public int size() {
        return this.arrayList.size();
    }

    public void addTask(Task task) {
        this.arrayList.add(task);
        System.out.println(String.format("Got it. I've added this task:\n\t%s\n" +
                        "Now you have %d task%s in the list.\n", task, this.arrayList.size(),
                this.arrayList.size() == 1 ? "" : "s"));
    }

    public void deleteTask(int taskIndex) {
        Task temp = this.arrayList.remove(taskIndex - 1);
        System.out.println(String.format(
                "Noted. I've removed this task:\n\t%s\nNow you have %d task%s in the list.",
                temp, this.arrayList.size(), this.arrayList.size() == 1 ? "" : "s"));
    }

    public void listTasks() {
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < this.arrayList.size(); i++) {
            Task currTask = this.arrayList.get(i);
            System.out.println(String.format("    %d. %s", i + 1, currTask));
        }
    }
}
