
public class Task {
    private final String taskText;
    private boolean completed;
    private int taskNumber;
    private static int count = 1;

    public Task(String taskText) {
        this.taskText = taskText;
        this.completed = false;
        this.taskNumber = count++;
    }
    
    public void toggleComplete(Boolean completed) {
        this.completed = completed;
    }

    public String getTask() {
        return this.taskText;
    }

    public boolean getCompleted() {
        return this.completed;
    }

    public int getTaskNumber() {
        return this.taskNumber;
    }

    @Override
    public String toString() {
        return String.format("     %d.[%s] %s\n", 
        this.taskNumber, this.completed ? "X" : " ", this.taskText);
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
        if (this.taskText.equals(temp.taskText)) {
            return true;
        }
        return false;
    }
}
