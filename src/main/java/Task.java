public class Task {
    protected final String task;
    protected final String taskType;
    private boolean isCompleted;

    public static Task of (String taskString) throws IanaException {
        String[] textArr = taskString.split(" ", 2);
        String startText = textArr[0];

        String[] taskArray;

        switch(startText) {
            case "todo":
            taskArray = isValidTask(textArr, "todo");
            return new Todo(taskArray[0]);
                
            case "event":
            taskArray = isValidTask(textArr, "event");
            return new Event(taskArray[0], taskArray[1]);

            case "deadline":
            taskArray = isValidTask(textArr, "deadline");
            return new Deadline(taskArray[0], taskArray[1]);

            default:
            throw new IanaException("Sorry, I don't know how to perform your request!! :C");
        }
    }

    private static String[] isValidTask(String[] textArray, String taskType) throws IanaException {
        if (textArray.length <= 1) {
            throw new IanaException(String.format("Oops! Your %s cannot be empty! :-(", taskType));
        }

        String[] taskArray = {};

        switch(TaskType.valueOf(taskType)) {
            case event:
            taskArray = textArray[1].split("/at ", 2);
            if (taskArray.length <= 1) {
                throw new IanaException("Rejected! Add event again with the format <event> /at <event time> !! :-)");
            }
            break;
            
            case deadline:
            taskArray = textArray[1].split("/by ", 2);
            if (taskArray.length <= 1) {
                throw new IanaException("Use the format <deadline> /by <deadline time> to create a deadline!! :D");
            }
            break;
            
            case todo:
            String[] temp = {textArray[1]};
            taskArray = temp;
            break;

            default:
            throw new IanaException("This is an invalid task type!! D-:");
        }

        return taskArray;
    }

    protected Task(String task, String taskType) {
        this.task = task;
        this.taskType = taskType;
        this.isCompleted = false;
    }
    
    public void toggleComplete(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.isCompleted ? "X" : " ", this.task);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (! (obj instanceof Task)) {
            return false;
        }
        Task temp = (Task) obj;
        if (this.task.equals(temp.task)) {
            return true;
        }
        return false;
    }
}
