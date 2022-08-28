public class Task {
    protected final String task;
    protected final String taskType;
    private boolean completed;

    public static Task of (String taskString) throws DukeException {
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
            throw new DukeException("Sorry, I don't know how to perform your request!! :C");
        }
    }

    private static String[] isValidTask(String[] textArray, String taskType) throws DukeException {
        if (textArray.length <= 1) {
            throw new DukeException(String.format("Oops! Your %s cannot be empty! :-(", taskType));
        }

        String[] taskArray = {};

<<<<<<< HEAD
        switch(TaskType.valueOf(taskType)) {
            case event:
            taskArray = textArray[1].split("/at ", 2);
=======
        switch(taskType) {
            case "event":
            taskArray = textArray[1].split("/at", 2);
>>>>>>> parent of 1e6b918 (Change bot name, add save and load data functions)
            if (taskArray.length <= 1) {
                throw new DukeException("Rejected! Add event again with the format <event> /at <event time> !! :-)");
            }
            break;
            
<<<<<<< HEAD
            case deadline:
            taskArray = textArray[1].split("/by ", 2);
=======
            case "deadline":
            taskArray = textArray[1].split("/by", 2);
>>>>>>> parent of 1e6b918 (Change bot name, add save and load data functions)
            if (taskArray.length <= 1) {
                throw new DukeException("Use the format <deadline> /by <deadline time> to create a deadline!! :D");
            }
            break;
            
            case "todo":
            String[] temp = {textArray[1]};
            taskArray = temp;
            break;

            default:
            throw new DukeException("This is an invalid task type!! D-:");
        }

        return taskArray;
    }

    protected Task(String task, String taskType) {
        this.task = task;
        this.taskType = taskType;
        this.completed = false;
    }
    
<<<<<<< HEAD
    public void toggleComplete(boolean isCompleted) {
        this.isCompleted = isCompleted;
=======
    public void toggleComplete(Boolean completed) {
        this.completed = completed;
>>>>>>> parent of 1e6b918 (Change bot name, add save and load data functions)
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.completed ? "X" : " ", this.task);
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
