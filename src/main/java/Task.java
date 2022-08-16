public abstract class Task {
    protected String description;
    protected boolean isDone;
    private static int count = 0;
    private static Task[] list = new Task[100];

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static void mark(int index) {
        Task task = list[index - 1];
        task.setDone(true);
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t" + task);
    }

    public static void unmark(int index) {
        Task task = list[index - 1];
        task.setDone(false);
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t\t" + task);
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public String getDescription() {
        return this.description;
    }

    public void add() {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + this);
        if (count == 0) {
            System.out.println("\tNow you have 1 task in the list.");
        } else {
            System.out.println("\tNow you have " + (count + 1) + " tasks in the list.");
        }
        list[count] = this;
        count++;
    }

    public static void printList() {
        if (count == 0) {
            System.out.println("List is empty!");
        } else {
            for (int i = 0; i < count; i++) {
                System.out.print("\t");
                System.out.print(i + 1);
                System.out.print(". " + list[i] + "\n");
            }
        }
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + this.getDescription();
    }
}
