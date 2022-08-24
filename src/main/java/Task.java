public class Task {
    protected String description;
    protected boolean isDone;
    public Type taskType;

    public enum Type {
        TODO, DEADLINE, EVENT
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static void makeTask(String description, String time, Type type) {
        switch (type) {
            case TODO:
                Sally.list.add(new ToDo(description));
                break;
            case DEADLINE:
                Sally.list.add(new Deadline(description, time));
                break;
            case EVENT:
                Sally.list.add(new Event(description, time));
                break;
        }
        String newTask = Sally.list.get(Sally.list.size() - 1).toString();
        int totalTasks = Sally.list.size();
        Sally.printBorder();
        System.out.println("Got it. I've added this task:\n    " + newTask + "\nto your to-do list!");
        if (totalTasks == 1) {
            System.out.println("Now you have 1 task in the list.\n");
        } else {
            System.out.println("Now you have " + totalTasks + " tasks in the list.\n");
        }
        Sally.printBorder();
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
