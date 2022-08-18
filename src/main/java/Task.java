public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static void checkDescription(String[] splitInput) throws DukeException {
        if (splitInput.length == 1)
            throw new DukeException("Please enter the description when creating new task!");
    }

    public static Task createTask(String input) throws DukeException {
        String[] splitInput = input.split(" ", 2);

        switch (Command.valueOf(splitInput[0])) {
            case todo:
                checkDescription(splitInput);
                return Todo.createTodo(splitInput[1]);
            case deadline:
                checkDescription(splitInput);
                return Deadline.createDeadline(splitInput[1]);
            case event:
                checkDescription(splitInput);
                return Event.createEvent(splitInput[1]);
            default:
                throw new DukeException("I'm sorry but I don't know what that means.");
        }
    }

    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + this);
    }

    public void markAsNotDone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t" + this);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
