public class Task {
    protected final String taskText;
    private boolean completed;

    public Task(String taskText) {
        this.taskText = taskText;
        this.completed = false;
    }
    
    public void toggleComplete(Boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.completed ? "X" : " ", this.taskText);
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
