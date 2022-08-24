import java.util.Arrays;
import java.util.List;

// import java.util.*;

public class Task {
    protected String description;
    protected boolean isDone;
    protected char taskType;
    protected String dateTime = null;
    protected String dueDescription; // Stores either 'by' or 'at'

    public Task(String description) throws Exception{
        try {
            this.description = description.split(" ", 2)[1];
            this.isDone = false;
            this.taskType = description.split(" ", 2)[0].toUpperCase().charAt(0);
    
            if (this.taskType == 'D') {
                parseDateTime("/by");
            } else if (this.taskType == 'E') {
                parseDateTime("/at");
            } else if (this.taskType == 'T') {
                this.dueDescription = "";
            } else {
                throw new Exception("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (Exception e) {
            throw new Exception("☹ OOPS!!! The description of a todo cannot be empty.");
        }

    }

    public Task(char taskType, boolean isDone, String description) {
        this.description = description;
        this.taskType = taskType;
        this.isDone = isDone;
    }

    public Task(char taskType, boolean isDone, String description, String dueDescription, String dateTime) {
        this.description = description;
        this.taskType = taskType;
        this.isDone = isDone;
        this.dueDescription = dueDescription;
        this.dateTime = dateTime;
    }

    private void parseDateTime(String delimiter){
        String[] splitText = this.description.split(delimiter);
        this.dateTime = splitText[1];
        this.description = splitText[0];
        this.dueDescription = delimiter.substring(1); // Extracts the 'by' or 'at'
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void unmark() {
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
    }

    public String getTaskString() {
        List<String> attributes = Arrays.asList(Character.toString(this.taskType), this.getStatusIcon(), this.description, this.dueDescription, this.dateTime);
        return String.join(" | ", attributes);
    }

    @Override
    public String toString(){
        if (this.taskType == 'T') {
            return "[" + this.taskType+ "]" + "[" + getStatusIcon() + "] " + this.description;
        } else {
            return "[" + this.taskType+ "]" + "[" + getStatusIcon() + "] " + this.description + "(" + this.dueDescription + ":"
            + this.dateTime + ")";
        }

    }
}
