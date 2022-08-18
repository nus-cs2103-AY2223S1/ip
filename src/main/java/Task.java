public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static Task createTask(String input) throws DukeException {
        switch (input.split(" ")[0]) {
            case "todo":
                return Todo.createTodo(input.split(" ", 2)[1]);
            case "deadline":
                return Deadline.createDeadline(input.split(" ", 2)[1]);
            case "event":
                return Event.createEvent(input.split(" ", 2)[1]);
            default:
                throw new DukeException("Please enter a valid command!");
        }
    }

    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.toString());
    }

    public void markAsNotDone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
