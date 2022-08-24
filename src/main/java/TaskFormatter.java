/**
 * Extracts the information from the file into
 * the Task class.
 */
public class TaskFormatter {
    private static final String DELIMITER = " # ";

    private String taskType;
    private String statusIcon;
    private String description;
    private String timing;

    public TaskFormatter(String task) {
        //Parse the task from the text format.
        String[] parsedInfo = task.split(DELIMITER);
        if (parsedInfo.length >= 3) {
            //correct amount of information.
            this.taskType = parsedInfo[0];
            this.statusIcon = parsedInfo[1];
            this.description = parsedInfo[2];
        }
        if (parsedInfo.length == 4) {
            this.timing = parsedInfo[3];
        } else {
            this.timing = "";
        }
    }

    public Task getTask() throws TumuException {
        //taskType can only be E, D, T
        switch (taskType) {
        case "E":
            Task event = new Event(description, timing);
            markTask(event);
            return event;
        case "D":
            Task deadline = new Deadline(description, timing);
            markTask(deadline);
            return deadline;
        case "T":
            Task todo = new Todo(description);
            markTask(todo);
            return todo;
        default:
            throw new GeneralException("Task type is not found!");
        }
    }

    private void markTask(Task task) {
        /**
         * Marks task if there is an appropriate status icon.
         */

        if (statusIcon == "X") {
            task.markDone();
        } else {
            task.unmarkDone();
        }
    }
}
