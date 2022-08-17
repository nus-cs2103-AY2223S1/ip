public abstract class Task {
    protected String task;
    protected boolean isComplete = false;
    protected static int taskCount = 0;

    /**
     * Factory method used to create a new Task
     *
     * @param userCommand  the command entered by the user to be parsed by the method
     * @return a Task obj, either a Todo, Deadline or Event
     */
    public static Task createTask(String userCommand) throws Exception {
        String task;
        String date;
        String[] cmdArray = userCommand.split(" ", 2);
        String cmd = cmdArray[0];
        Task newTask;

        switch (cmd) {
            case "todo":
                task = cmdArray[1].trim();
                newTask = new Todo(task);
                break;
            case "deadline":
                task = cmdArray[1].substring(0, cmdArray[1].indexOf("/by")).trim();
                date = cmdArray[1].substring(cmdArray[1].indexOf("/by") + 4).trim();
                newTask = new Deadline(task, date);
                break;
            case "event":
                task = cmdArray[1].substring(0, cmdArray[1].indexOf("/at")).trim();
                date = cmdArray[1].substring(cmdArray[1].indexOf("/at") + 4).trim();
                newTask = new Event(task, date);
                break;
            default:
                throw new Exception("Command Unrecognised");
        }
        System.out.println("\nGot it. I've added this task:");
        System.out.println(newTask);
        System.out.println("Now you have " + Task.taskCount + " task(s) in the list.");
        return newTask;
    }

    /**
     * Method used to mark this task as complete
     */
    public void markAsDone() {
        this.isComplete = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this);
    }

    /**
     * Method used to mark this task as incomplete
     */
    public void unmark() {
        this.isComplete = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this);
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
