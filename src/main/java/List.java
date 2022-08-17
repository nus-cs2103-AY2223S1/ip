public class List {
    private Task[] data;
    private int position;

    public List(int size) {
        this.data = new Task[size];
    }

    public void addTask(Task task) {
        data[position] = task;
        position++;
    }

    public void markTask (int pos, boolean isDone) {
        Task task = data[pos];
        task.mark(isDone);
        System.out.println(
                ( isDone ? "Nice! I've marked this task as done:\n " : "OK, I've marked this task as not done yet:\n ")
                        + task
        );
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < position; i++) {
            result.append(i + 1 + ". " + data[i]);
        }
        return result.toString();
    }
}
