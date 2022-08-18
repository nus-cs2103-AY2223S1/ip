import java.util.ArrayList;

public class Storage {
    private ArrayList<Task> storage = new ArrayList<>();

    public void printStorage() {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < storage.size(); i++) {
            Task currTask = this.storage.get(i);
            System.out.printf((i + 1) + ".[%s] %s\n", currTask.getStatusIcon(), currTask);
        }
    }

    public void markDone(int i) {
        storage.get(i).isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.printf("[%s] %s\n",storage.get(i).getStatusIcon(),storage.get(i));
    }
    public void addTask(Task t) {
        this.storage.add(t);
        System.out.println("added:" + t);
    }
}
