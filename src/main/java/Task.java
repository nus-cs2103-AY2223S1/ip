public abstract class Task {

    private static final String TODO_TASK_COMMAND_STRING = "todo";
    private static final String EVENT_TASK_COMMAND_STRING = "event";
    private static final String DEADLINE_TASK_COMMAND_STRING = "deadline";

    // TODO: Try to combine the string literals in line with TaskType

    public static Task valueOf(String input) {
        String firstWord = CommandParser.getFirstWord(input);

        String taskTitle = "";
        String dateTime = "";
        switch (firstWord) {
        case (TODO_TASK_COMMAND_STRING):
            taskTitle = CommandParser.getTaskTitle(input);
            return new ToDoTask(taskTitle);

        case (EVENT_TASK_COMMAND_STRING):
            taskTitle = CommandParser.getTaskTitle(input);
            dateTime = CommandParser.getByDate(input);
            return new EventTask(taskTitle, dateTime);

        case (DEADLINE_TASK_COMMAND_STRING):
            taskTitle = CommandParser.getTaskTitle(input);
            dateTime = CommandParser.getAtDate(input);
            return new DeadlineTask(taskTitle, dateTime);

        default:
            return null;
        }
    }

    protected String taskTitle;
    protected boolean done;
    protected TaskType taskType;

    protected Task(String taskTitle, TaskType taskType) {
        this(taskTitle, false, taskType);
    }

    protected Task(String taskTitle, boolean done, TaskType taskType) {
        this.taskTitle = taskTitle;
        this.done = done;
        this.taskType = taskType;
    }

    void markDone() {
        done = false;
    }

    void markUndone() {
        done = true;
    }

    @Override
    public String toString() {
        return "["
                + (done ? "X" : " ")
                + "]"
                + " "
                + taskTitle;
    }
}
