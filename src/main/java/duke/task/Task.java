package duke.task;

import java.time.format.DateTimeFormatter;

public abstract class Task {

    protected static final String OUTPUT_DATE_TIME_FORMAT = "yyyy/MM/dd T HH:mm:ss";
    protected static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern(OUTPUT_DATE_TIME_FORMAT);
    private static final String FILE_WRITING_DELIMITER = "|";


    /**
     *
     *
     *
     *
     *@param
     *@param
     *@param
     *@param
     *@param
     *@return
     *@throws
     */

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

    public void markDone() {
        done = true;
    }

    public void markUndone() {
        done = false;
    }

    protected String getStringRepresentation(String label, String displayedText) {
        return "["
                + (done ? "X" : " ")
                + "] "
                + "["
                + label
                + "] "
                + displayedText;
    }

    public abstract String getFileRepresentation();

    protected String getFileRepresentation(String label) {
        return getFileRepresentation(label, null);
    }

    /**
     *
     *
     *
     *
     *@param
     *@param
     *@param
     *@param
     *@param
     *@return
     *@throws
     */

    protected String getFileRepresentation(String label, String dateTime) {
        String output = label
                + " "
                + FILE_WRITING_DELIMITER
                + " "
                + (done ? "1" : "0")
                + " "
                + FILE_WRITING_DELIMITER
                + " "
                + taskTitle;
        if (dateTime != null) {
            output += " "
                    + FILE_WRITING_DELIMITER
                    + " "
                    + dateTime;
        }
        return output;
    }

    public boolean contains(String keyword) {
        return taskTitle.contains(keyword);
    }
}
