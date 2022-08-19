// import java.util.*;

public class Task {
    protected String description;
    protected boolean isDone;
    protected char taskType;
    protected String dateTime;
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
