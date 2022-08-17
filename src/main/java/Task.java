public abstract class Task {
    protected String task;
    protected boolean isComplete = false;

    /**
     * Factory method used to create a new Task
     *
     * @param userCommand  the command entered by the user to be parsed by the method
     * @return a Task obj, either a Todo, Deadline or Event
     */
    public static Task createTask(String userCommand) {
        String task;
        String date;
        String[] cmdArray = userCommand.split(" ", 2);
        String cmd = cmdArray[0];

        switch (cmd) {
            case "todo":
                task = cmdArray[1];
                return new Todo(task);
            case "deadline":
                task = cmdArray[1].substring(0, cmdArray[1].indexOf("/by"));
                date = cmdArray[1].substring(cmdArray[1].indexOf("/by") + 1);
                return new Deadline(task, date);
            case "event":
                task = cmdArray[1].substring(0, cmdArray[1].indexOf("/at"));
                date = cmdArray[1].substring(cmdArray[1].indexOf("/at") + 1);
                return new Event(task, date);
            default:
                return new Todo("");
        }
    }

    /**
     * Method used to mark this task as complete
     */
    public void markAsDone() {
        this.isComplete = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.toString());
    }

    /**
     * Method used to mark this task as incomplete
     */
    public void unmark() {
        this.isComplete = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.toString());
    }

    /**
     * To String method that returns the task in string form to the user
     *
     * @return  the task in string format
     */
    @Override
    public String toString() {
        String checkBox = this.isComplete ? "[X] " : "[ ] ";
        return checkBox + this.task;
    }
}
