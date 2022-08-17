public class List {
    private Task[] data;
    private int position;

    public List(int size) {
        this.data = new Task[size];
    }

    public void addTask(Task task) {
        data[position] = task;
        position++;
        System.out.println("Got it. I've added this task: \n" +
                " " + task + "\n" +
                "Now you have " + position + " tasks in the list. \n");
    }

    public void markTask (int pos, boolean isDone) {
        Task task = data[pos];
        task.mark(isDone);
        System.out.println(
                ( isDone ? "Nice! I've marked this task as done:\n " : "OK, I've marked this task as not done yet:\n ")
                        + task + "\n"
        );
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < position; i++) {
            result.append(i + 1 + "." + data[i] + "\n");
        }
        return result.toString();
    }
}
